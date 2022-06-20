package com.sms.aligo.service;
import java.time.LocalDateTime;
import com.sms.aligo.dto.AligoMessageDto;
import com.sms.aligo.entity.AligoMessage;
import com.sms.aligo.repository.AligoMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AligoService {
    private final AligoMessageRepository messageRepository;

    public LocalDateTime setTime(){
        LocalDateTime dateAndtime = LocalDateTime.now();
        return dateAndtime;
    }

    public void setAlimtalk (AligoMessageDto dto){
        AligoMessage entity = AligoMessage.builder()
                .state("WAIT")
                .tag(dto.getTag())
                .sender_name(dto.getSender_name())
                .sender_num(dto.getSender_num())
                .receiver_name(dto.getReceiver_name())
                .receiver_num(dto.getReceiver_num())
                .url(dto.getUrl())
                .created_at(setTime())
                .updated_on(setTime())
                .build();
        messageRepository.save(entity);
    }


//    public String setMessage(String name, String type, String link){
//        if (type.equals("invitation")){
//            Optional <AligoTemplate> tp = aligoRepository.findById(1L);
//            String template = null;
//            if (tp.isEmpty()){
//                template = "[아이쿠카] #{송신자} 님의 아이쿠카 초대\\n❤︎용돈을 주고 받는 아이쿠카 초대장❤︎이 도착했습니다.\\n(유효기간: 2주)\\n\\n[ 아이쿠카 가입하기 ]\\n#{링크}";
//            }
//            else{
//               template = tp.get().getMessage();
//            }
//            template = template.replace("#{송신자}",name);
//            template = template.replace("#{링크}",link);
//            System.out.println(template);
//            return template;
//        }
//        else if (type.equals("agreement")){
//            Optional <AligoTemplate> tp = aligoRepository.findById(2L);
//            String template = null;
//            if (tp.isEmpty()){
//                template = "[아이쿠카] #{만14세미만자녀} 님의 법정대리인(보호자) 동의 요청\\n아이쿠카는 만 14세 미만 이용자 가입에 법정대리인(보호자)의 동의가 필요합니다.\\n(유효시간 : 5분)\\n\\n[ 동의하러 가기 ]\\n#{링크}";
//            }
//            else{
//                template = tp.get().getMessage();
//            }
//            template = template.replace("#{만14세미만자녀}",name);
//            template = template.replace("#{링크}",link);
//            System.out.println(template);
//            return template;
//        }
//        return null;
//    }
//    public String setConnection(String domain, AligoTemplateDto dto) {
//        try {
//            URL url = new URL(domain);
//
//            // set message
//            String msg = setMessage(dto.getName(), dto.getType(), dto.getLink());
//
//            // set parameter
//            Map<String,Object> params = new LinkedHashMap<>();
//            params.put("apikey","aker151ukxd9a3wb7dk7ltuprci8w406");
//            params.put("userid","happytoseeyou");
//            params.put("token","a5a85ad199749aadb1693523a56a355b91192631e4e8fdcc4020855eb4e0f87281563af1ad0a5cbe64c04520a990020eb697c161117079336346dc2d14a992c7nluCzpvw0fdAr+QLy6KuceqgLmyVzYM61yFM8YhS7eGymx9KrrfGrU88CK6PhpcZvzQmmK8HYrWfkmSkpD8e8w==");
//            params.put("senderkey","ffbd40a00d729a352aa50e9d1be045630d83456d");
//
//            params.put("tpl_code",aligoRepository.findTemplateNumByType(dto.getType()));
//            params.put("sender",dto.getSender());
//            params.put("receiver_1",dto.getReceiver());
//            params.put("subject_1","아이쿠카");
//            params.put("message_1",msg);
//            params.put("testMode","Y");
//
//            StringBuilder postData = new StringBuilder();
//            for(Map.Entry<String,Object> param : params.entrySet()){
//                if(postData.length() != 0)
//                    postData.append('&');
//            postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
//            postData.append('=');
//            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
//            }
//            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//
//            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
//            http.setRequestMethod("POST");
//            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            http.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
//            http.setDoOutput(true);
//            http.getOutputStream().write(postDataBytes);
//            log.info("Post Data:{}", postData);
//            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
//            String inputLine;
//            String res = "";
//            while((inputLine=in.readLine())!=null){
//                System.out.println(inputLine);
//                res = inputLine;
//             }
//             in.close();
//            return res;
//        } catch (Exception e) {
//            System.out.println("Exception");
//        }
//        return null;
//}
//    public JsonNode getReturnNode(String res) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode node = mapper.readTree(res);
//        return node;
//    }
//    public void sendAlimtalk (AligoTemplateDto dto) throws JsonProcessingException {
//        String url = "https://kakaoapi.aligo.in/akv10/alimtalk/send/";
//        String res = setConnection(url,dto);
//        JsonNode node = getReturnNode(res);
//        if (node.get("code").toString().toString().equals("0")) {
//            String type = node.get("info").get("type").toString();
//            String current = node.get("info").get("current").toString();
//            String unit = node.get("info").get("unit").toString();
//            String total = node.get("info").get("total").toString();
//            log.info("type :{}, current :{}, unit :{}, total :{}", type, current, unit, total);
//        }
//        else {
//            log.info("Failed");
//        }
//    }


}
