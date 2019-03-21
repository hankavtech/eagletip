package com.hankav.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RegistrationEmailSender {

	public void sendemail(String email, String uuid) throws Exception {

		final String username = "info@eagletip.com";
		final String password = "Iforget14!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "eagletip.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@eagletip.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Confirmation Email");
			message.setContent(
					"<img style='width:80px;height:60px' src='https://www.eagletip.com/images/css/eagletip.png'>"
							+ "<h1>EAGLE<span style='color:orange'>TIP</span></h1>"
							+ "<p>Thank you for registering with us.Please click on this <a href='https://www.eagletip.com/Confirmation?uuid="
							+ uuid + "&email=" + email + "'>link</a> in order to verify your email address.</p>"
							+ "We want to notify that you will not receive any tips by email unless and until the verification process is complete."
							+ "We look forward to have a successfull relationship with you.",
					"text/html");
			Transport.send(message);

		} catch (MessagingException e) {
			System.out.println("message not sent");
		}
	}

}
