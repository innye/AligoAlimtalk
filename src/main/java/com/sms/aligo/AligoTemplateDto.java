package com.sms.aligo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AligoTemplateDto {
    private String sender;
    private String receiver;
    private String name;
    private String type;
    private String link;
}
