package isa2022.projekat.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import isa2022.projekat.model.data.Lokacija;
import isa2022.projekat.security.CommunicationConfig;

@Component
public class CommunicationListener {

	
	
	//METODA KOJA SLUSA NA QUEUE-u isa_queue
	@RabbitListener(queues = CommunicationConfig.QUEUE)
	public void listener(Lokacija lokacija){
		System.out.println("TRENUTNA LOKACIJA IZ LOKACIJA-SERVICE: ");
		this.sendCoordsToClient(lokacija);
	}
	
	@Autowired
	SimpMessagingTemplate template;
	//METODA KOJA KOORDINATE PRIMLJENE SA QUEUE-a PROSLEDJUJE PREKO SOCKETA DO KLIJENTA
	public void sendCoordsToClient(Lokacija coords) {
		template.convertAndSend("/topic/coords", coords);
		System.out.println("KOORDINATE PROSLEDJENJE NA PRIKAZ NARUCIOCU: " + coords.getLat() + ", " + coords.getLng());
	}
}
