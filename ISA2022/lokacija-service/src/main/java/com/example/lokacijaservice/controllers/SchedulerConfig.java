package com.example.lokacijaservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.lokacijaservice.dto.LatLng;

@EnableScheduling
@Configuration
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;
	
//	@Scheduled(fixedDelay = 3000)
//	public void sendAdhocMessages() {
//		template.convertAndSend("/topic/coords", new String("ODGOVOR"));
//	}
//	
	
	public void sendResponse(LatLng coords) {
		template.convertAndSend("/topic/coords", coords);
		System.out.println("KOORDINATE PROSLEDJENJE NA PRIKAZ");
	}
}
