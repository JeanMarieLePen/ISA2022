package isa2022.projekat.services;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sun.mail.util.BASE64EncoderStream;

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
	@Autowired
	private QRService qrService;
	
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
	
//	@Async
//	public void sendReservationNotificationMail(RegKorisnik rk, MedCentar mc, Termin t) {
//		SimpleMailMessage mail = new SimpleMailMessage();
////		mail.setTo(rk.getEmail());
//		mail.setTo("isa2022bot@gmail.com");
//		mail.setFrom(env.getProperty("spring.mail.username"));
//		mail.setSubject("Potvrda rezervacije");
//		String body = null;
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
//		String vreme = t.getPocetakTermina().format(formatter);
//		body = "Postovani "+ rk.getIme() + " " + rk.getPrezime() + 
//		" uspesno ste zakazali termin donacije. \n Adresa: " + mc.getAdresa() + "\n" + 
//				"Datum i vreme: " + vreme;
//		mail.setText(body);
//		javaMailSender.send(mail);
//	}
	
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
	
	@Async
	public void sendReservationNotificationMail(RegKorisnik rk, MedCentar mc, Termin t/*String to, String subject, String fileToAttach*/) 
	{
		
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	    	@Override
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	    		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("isa2022bot@gmail.com"));
	            mimeMessage.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
	            mimeMessage.setSubject("Potvrda zakazivanja termina");
	            
	            String body = null;
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
	    		String vreme = t.getPocetakTermina().format(formatter);
	    		body = "Postovani "+ rk.getIme() + " " + rk.getPrezime() + 
	    		" uspesno ste zakazali termin donacije. \nAdresa:" + mc.getAdresa() + "\n" + 
	    				"Datum i vreme: " + vreme;
	    		
	            BufferedImage temp = qrService.generateQRCodeImage(body);
	            
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(temp, "png", baos);
	            baos.flush();
	            byte[] imageBytes= baos.toByteArray();
	            baos.close();
	            ByteArrayDataSource bds = new ByteArrayDataSource(imageBytes, "image/png"); 
	            
	            MimeMultipart multipart = new MimeMultipart();
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setDataHandler(new DataHandler(bds));
	            messageBodyPart.setFileName("qr-code.png");
	            messageBodyPart.setHeader("Content-ID", "<image>");
	            multipart.addBodyPart(messageBodyPart);
	            mimeMessage.setContent(multipart);
	        }
	    };
	     
	    try {
	    	javaMailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}
	
	
//	@Async
//	public void sendReservationCancelationNotificationMail(/*String to, String subject, String fileToAttach*/) 
//	{
//		
//	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
//	    {
//	    	@Override
//	        public void prepare(MimeMessage mimeMessage) throws Exception 
//	        {
//	    		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("isa2022bot@gmail.com"));
////	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	            mimeMessage.setFrom(new InternetAddress(env.getProperty("spring.mail.username")));
////	            mimeMessage.setSubject(subject);
//	            mimeMessage.setSubject("Potvrda zakazivanja termina");
//	             
////	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//	             
////	            helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
//	             
////	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
////	            BufferedImage temp = qrService.generateQRCodeImage("TEST STRING ZA QR CODE");
////	            ByteArrayOutputStream baos = null;
////	            try {
////	            	baos = new ByteArrayOutputStream();
////	            	ImageIO.write(temp, "png", baos);
////	            }finally {
////	            	try {
////	            		baos.close();
////	            	}catch(Exception e) {
////	            		
////	            	}
////	            }
////	            
////	            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//	            BufferedImage temp = qrService.generateQRCodeImage("TEST STRING ZA QR CODE");
////	            byte[] imageBytes = ((DataBufferByte)temp.getData().getDataBuffer()).getData();
//	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	            ImageIO.write(temp, "png", baos);
//	            baos.flush();
//	            byte[] imageBytes= baos.toByteArray();
//	            baos.close();
//	            ByteArrayDataSource bds = new ByteArrayDataSource(imageBytes, "image/png"); 
//	            
////	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
////	            helper.addAttachment("identifier1234.png", bds);
//	            
//	            
//	            MimeMultipart multipart = new MimeMultipart();
//	            MimeBodyPart messageBodyPart = new MimeBodyPart();
//	            messageBodyPart.setDataHandler(new DataHandler(bds));
//	            messageBodyPart.setFileName("qr-code.png");
//	            messageBodyPart.setHeader("Content-ID", "<image>");
//	            multipart.addBodyPart(messageBodyPart);
//	            mimeMessage.setContent(multipart);
//	        }
//	    };
//	     
//	    try {
//	    	javaMailSender.send(preparator);
//	    }
//	    catch (MailException ex) {
//	        // simply log it and go on...
//	        System.err.println(ex.getMessage());
//	    }
//	}
}
