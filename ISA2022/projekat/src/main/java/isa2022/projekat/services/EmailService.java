package isa2022.projekat.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa2022.projekat.model.korisnici.Korisnik;

@Service
public class EmailService {

	@Autowired 
	private Environment env;
	@Autowired 
	private JavaMailSender javaMailSender;
	
	SimpleMailMessage mail;
	private static String host="http://localhost:8082";
	
	@Async
	public void sendActivationEmail(Korisnik k) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(k.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Aktivacioni email");
		String body = null;
		String exptime = LocalDateTime.now().toString();
		
		body = "Postovani  " + k.getIme() + " " + k.getPrezime()
		+ ",\nDa biste aktivirali svoj nalog, potrebno je da kliknete na sledeci link: "
		+ host+"/activateAccount" + "?id=" + k.getId() + "&secret="
		+ k.getLozinka() + "&exptime=" + exptime;
		
		mail.setText(body); 
		javaMailSender.send(mail);
	}
}
