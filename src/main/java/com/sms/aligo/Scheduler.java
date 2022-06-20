package com.sms.aligo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.aligo.entity.AligoMessage;
import com.sms.aligo.entity.AligoTemplate;
import com.sms.aligo.repository.AligoMessageRepository;
import com.sms.aligo.repository.AligoTemplateRepository;
import lombok.RequiredArgsConstructor;
import  org.springframework.scheduling.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableAsync
public class Scheduler {
    private final AligoTemplateRepository aligoTemplateRepository;
    private final AligoMessageRepository aligoMessageRepository;

    private String domain = "https://kakaoapi.aligo.in/akv10/alimtalk/send/";

    /**
     * Cron 표현식을 사용한 작업 예약
     * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
     */
    @Scheduled(cron = "5 * * * * ?")
    public void sendMessage() throws JsonProcessingException {

        // set Message
        log.info("schedule tasks using cron jobs - {}");
        List <AligoMessage> am = aligoMessageRepository.findMessageByState("WAIT");
        String res = null;
        for (AligoMessage t: am) {
            if (t.getState() == "invitation"){
                res = setInvitationMessage(t);
            }
            else if (t.getState() == "agreement"){
                res = setAgreementMessage(t);
            }
            // send Message
            JsonNode node = getReturnNode(setConnection(domain,t,res));
            if (node.get("code").toString().toString().equals("0")) {
                String type = node.get("info").get("type").toString();
                String current = node.get("info").get("current").toString();
                String unit = node.get("info").get("unit").toString();
                String total = node.get("info").get("total").toString();
                log.info("type :{}, current :{}, unit :{}, total :{}", type, current, unit, total);
                aligoMessageRepository.updateMessageStateById("REQUEST",t.getId());
            }
            else {
                log.info("Failed");
            }
        }

    }

    public String setInvitationMessage(AligoMessage a){
            Optional<AligoTemplate> tm = aligoTemplateRepository.findById(1L);
            String ms = null;
            if (!tm.isEmpty()) {ms = tm.get().getMessage();}
            else { ms = "[아이쿠카] #{송신자} 님의 아이쿠카 초대\n❤︎용돈을 주고 받는 아이쿠카 초대장❤︎이 도착했습니다.\n(유효기간: 2주)\n\n[ 아이쿠카 가입하기 ]\n#{링크}";}
            ms = ms.replace("#{송신자}",a.getSender_name());
            ms = ms.replace("#{링크}",a.getUrl());
            return ms;
    }

    public String setAgreementMessage(AligoMessage a){
        Optional<AligoTemplate> tm = aligoTemplateRepository.findById(1L);
        String ms = null;
        if (!tm.isEmpty()) {ms = tm.get().getMessage();}
        else { ms = "[아이쿠카] #{만14세미만자녀} 님의 법정대리인(보호자) 동의 요청\n아이쿠카는 만 14세 미만 이용자 가입에 법정대리인(보호자)의 동의가 필요합니다.\n(유효시간 : 5분)\n\n[ 동의하러 가기 ]\n#{링크}";}
        ms = ms.replace("#{만14세미만자녀}",a.getSender_name());
        ms = ms.replace("#{링크}",a.getUrl());
        return ms;
    }

    public String setConnection(String domain, AligoMessage dto, String msg) {
        try {
            URL url = new URL(domain);
            // set parameter
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("apikey","aker151ukxd9a3wb7dk7ltuprci8w406");
            params.put("userid","happytoseeyou");
            params.put("token","a5a85ad199749aadb1693523a56a355b91192631e4e8fdcc4020855eb4e0f87281563af1ad0a5cbe64c04520a990020eb697c161117079336346dc2d14a992c7nluCzpvw0fdAr+QLy6KuceqgLmyVzYM61yFM8YhS7eGymx9KrrfGrU88CK6PhpcZvzQmmK8HYrWfkmSkpD8e8w==");
            params.put("senderkey","ffbd40a00d729a352aa50e9d1be045630d83456d");

            params.put("tpl_code",aligoTemplateRepository.findTemplateNumByType(dto.getTag()));
            params.put("sender",dto.getSender_num());
            params.put("receiver_1",dto.getReceiver_num());
            params.put("subject_1","아이쿠카");
            params.put("message_1",msg);
            params.put("testMode","Y");

            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet()){
                if(postData.length() != 0)
                    postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
            http.setDoOutput(true);
            http.getOutputStream().write(postDataBytes);
            log.info("Post Data:{}", postData);
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
            String inputLine;
            String res = "";
            while((inputLine=in.readLine())!=null){
                System.out.println(inputLine);
                res = inputLine;
             }
             in.close();
            return res;
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return null;
}
    public JsonNode getReturnNode(String res) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(res);
        return node;
    }
}
