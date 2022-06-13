package com.sms.aligo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@Service
public class aligoService {
    String data = "\"apikey\":\"aker151ukxd9a3wb7dk7ltuprci8w406\"," +
            "\"userid\":\"happytoseeyou\",\"token\":\"a5a85ad199749aadb1693523a56a355b91192631e4e8fdcc4020855eb4e0f87281563af1ad0a5cbe64c04520a990020eb697c161117079336346dc2d14a992c7nluCzpvw0fdAr+QLy6KuceqgLmyVzYM61yFM8YhS7eGymx9KrrfGrU88CK6PhpcZvzQmmK8HYrWfkmSkpD8e8w==\","
            + "\"senderkey\":\"ffbd40a00d729a352aa50e9d1be045630d83456d\"," +
            "\"tpl_code\":\"TI_8950\"," + "\"sender\":\"0220384330\"," +
            "\"receiver_1\":\"01091919263\"," + "\"subject_1\":\"아이쿠카 회원가입 안내\"," +
            "\"message_1\":\"#{셧왕뫄},#{SMS}\"," + "\"testMode\":\"Y\"," ;
    public static void setConnection(String domain) {
        log.info("START");
        try {
            log.info("CONNECTION");
            URL url = new URL(domain);

            // set parameter
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("apikey","aker151ukxd9a3wb7dk7ltuprci8w406");
            params.put("userid","happytoseeyou");
            params.put("token","a5a85ad199749aadb1693523a56a355b91192631e4e8fdcc4020855eb4e0f87281563af1ad0a5cbe64c04520a990020eb697c161117079336346dc2d14a992c7nluCzpvw0fdAr+QLy6KuceqgLmyVzYM61yFM8YhS7eGymx9KrrfGrU88CK6PhpcZvzQmmK8HYrWfkmSkpD8e8w==");
            params.put("senderkey","ffbd40a00d729a352aa50e9d1be045630d83456d");
            params.put("tpl_code","TI_8950");
            params.put("sender","0220384330");
            params.put("receiver_1","01091919263");
            params.put("subject_1","아이쿠카 회원가입 안내");
            params.put("message_1","#{셧왕뫄},#{SMS}");
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

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(), "UTF-8"));
            String inputLine;
            String res = "";
            while((inputLine=in.readLine())!=null){
                System.out.println(inputLine);
                res = inputLine;
             }
            log.info(res);
             in.close();

        } catch (Exception e) {
            System.out.println("Exception");
        }
}

    public static void sendAlimtalk () {
        log.info("ENTER");
        String url = "https://kakaoapi.aligo.in/akv10/alimtalk/send/";
        setConnection(url);
    }
}
