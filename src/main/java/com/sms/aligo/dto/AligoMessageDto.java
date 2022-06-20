package com.sms.aligo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AligoMessageDto {
    private String tag;
    private String sender_name;
    private String sender_num;
    private String receiver_name;
    private String receiver_num;
    private String url;
}
