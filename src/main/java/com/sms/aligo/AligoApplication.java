package com.sms.aligo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AligoApplication {
	public static void main(String[] args) {
		SpringApplication.run(AligoApplication.class, args);
	}

}
