package com.example.lokacijaservice.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lokacijaservice.CommunicationConfig;
import com.example.lokacijaservice.dto.Coords;
import com.example.lokacijaservice.dto.LatLng;
import com.example.lokacijaservice.dto.Lokacija;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private SchedulerConfig scheduler;
	
	@GetMapping("/getBroj/{broj}")
	public String test(int broj) {
		return "UNET BROJ: " + broj;
	}
	
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody Lokacija lokacija) {
		//saljemo na queue koji ce se zvati isa_exchange
		template.convertAndSend(CommunicationConfig.EXCHANGE, CommunicationConfig.ROUTING_KEY, lokacija);
		return "LOKACIJA PROSLEDJENA";	
	}
	
	
	
	@PostMapping("/coords")
	public void sendCoords(@RequestBody LatLng[] coords) {
		System.out.println("Stigle koordinate");
		
	}
	
	
	
	//METODA KOJA PRIMA KOORDINATE OD SAMOG VOZILA PREKO SOCKETA I PROSLEDJUJE IH NA QUEUE
	@MessageMapping("/news")
	@SendTo("/topic/news")
	public String sendCoords(@Payload LatLng lokacija) {
		System.out.println("KOORDINATE: " + lokacija.getLat() + ", " + lokacija.getLng());
		System.out.println("KOORDINATE SE SALJU NA QUEUE: " + CommunicationConfig.EXCHANGE);
		this.template.convertAndSend(CommunicationConfig.EXCHANGE, CommunicationConfig.ROUTING_KEY, lokacija);
		return "KOORDINATE: " + lokacija.getLat() + ", " + lokacija.getLng();
	}
	
	
	
//	@MessageMapping("/news")
//	@SendTo("/topic/news")
//	public String broadcastNews(@Payload LatLng message) {
//		System.out.println("KOORDINATE: " + message.getLat() + ", " + message.getLng());
//		this.scheduler.sendResponse(message);
//		return "KOORDINATE: " + message.getLat() + ", " + message.getLng();
//	}
	
//	@MessageMapping("/coords")
//	@SendTo("/topic/coords")
//	public String sendCoords(/*@Payload */String koordinate) {
//		System.out.println("PRIJEM KOORDINATA: " + koordinate);
//		
//		return new String("Koordinate prihvacene");
//	}
}
