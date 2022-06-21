package com.sms.aligo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SetTime {
    public static LocalDateTime setTime(){
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDateTime dateAndtime = nowUTC.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        //LocalDateTime dateAndtime = LocalDateTime.now();
        return dateAndtime;
    }
}
