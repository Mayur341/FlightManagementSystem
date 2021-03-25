package com.giczi.david.flight.service;

import java.util.Locale;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	@Value("${flight.service.host}")
	private String PATH;
	
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMeassage(String email, String firstName, String lastName, String activationCode) {
		
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			switch (getLocaleForDateFormat()) {
			case 1:
				message.setSubject("Sikeres regisztrálás");
				message.setText("Kedves " + lastName + " " + firstName + "!\n\nKöszönjük, hogy regisztráltál a Flight Service oldalára. A fiókodat az alábbi linkre kattintva aktiválhatod:\n\n"
						+ PATH + activationCode
						+ "\n\nÜdvözlettel:\nFlight Service Team");
				break;
			case 2:
				message.setSubject("Registration is success");
				message.setText("Dear " + firstName + " " + lastName + "!\n\nThank you for your registration on homepage of Flight Service. You can activate your account by clicking the link below:\n\n"
						+ PATH + activationCode
						+ "\n\nYours Sincerelly:\nFlight Service Team");
				break;

			default:
				message.setSubject("Sikeres regisztrálás");
				message.setText("Kedves " + lastName + " " + firstName + "!\n\nKöszönjük, hogy regisztráltál a Flight Service oldalára. A fiókodat az alábbi linkre kattintva aktiválhatod:\n\n"
						+ PATH + activationCode
						+ "\n\nÜdvözlettel:\nFlight Service Team");
			}
			
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Error for sending e-mail to: " + email);
		}
		
	}
	

	private int getLocaleForDateFormat() {
		
		if(LocaleContextHolder.getLocale().equals(new Locale("hu"))) {
			return 1;
		}
		else if(LocaleContextHolder.getLocale().equals(new Locale("en"))) {
			return 2;
		}
		
		return -1;
	}
	
	
}