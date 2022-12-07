package isa2022.projekat.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import isa2022.projekat.model.data.MedCentar;
import isa2022.projekat.model.data.Termin;
import isa2022.projekat.model.korisnici.Korisnik;
import isa2022.projekat.model.korisnici.RegKorisnik;

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
	
	@Async
	public void sendReservationNotificationMail(RegKorisnik rk, MedCentar mc, Termin t) {
		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(rk.getEmail());
		mail.setTo("isa2022bot@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrda rezervacije");
		String body = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
		String vreme = t.getPocetakTermina().format(formatter);
		body = "Postovani "+ rk.getIme() + " " + rk.getPrezime() + 
		" uspesno ste zakazali termin donacije. \n Adresa: " + mc.getAdresa() + "\n" + 
				"Datum i vreme: " + vreme;
		mail.setText(body);
		javaMailSender.send(mail);
	}
	
	@Async
	public void sendReservationCancelationNotificationMail(RegKorisnik rk, MedCentar mc, Termin t) {
		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(rk.getEmail());
		mail.setTo("isa2022bot@gmail.com");
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Potvrda otkazivanja termina");
		String body = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
		String vreme = t.getPocetakTermina().format(formatter);
		body = "Postovani "+ rk.getIme() + " " + rk.getPrezime() + 
		" uspesno ste otkazali termin donacije. \nAdresa:" + mc.getAdresa() + "\n" + 
				"Datum i vreme: " + vreme;
		mail.setText(body);
		javaMailSender.send(mail);
	}
}
