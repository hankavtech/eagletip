package com.hankav.dao;

import java.text.ParseException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotEmailSender {
	public void sendemail(String email, String uuid) throws ParseException, AddressException {

		final String username = "info@eagletip.com";
		final String password = "Iforget14!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "eagletip.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("info@eagletip.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Password Reset");
			message.setContent(
					"<img style='width:80px;height:60px' src='https://www.eagletip.com/images/css/eagletip.png'>"
							+ "<h1>EAGLE<span style='color:orange'>TIP</span></h1>"
							+ "<p>We have a received a request to reset your password.Please click on this <a href='https://www.eagletip.com/ResetPassword?uuid="
							+ uuid + "&email=" + email + "'>link</a> in order to reset your password.</p>",
					"text/html");

			Transport.send(message);

		} catch (MessagingException e) {

		}
	}
}
