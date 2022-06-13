package com.sms.aligo.controller;
import com.sms.aligo.service.aligoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/aligo")
public class aligoController {
    @GetMapping
    public void sendAlimtalk(){
        aligoService.sendAlimtalk();
        }
}
