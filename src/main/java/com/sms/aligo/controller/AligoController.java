package com.sms.aligo.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sms.aligo.dto.AligoMessageDto;
import com.sms.aligo.service.AligoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/aligo")
public class AligoController {
    //static 해결
    private final AligoService aligoService;
    @GetMapping()
    public void sendAlimtalk(@RequestBody AligoMessageDto dto) {
        aligoService.setAlimtalk(dto);
        }
}
