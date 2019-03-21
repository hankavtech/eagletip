package com.hankav.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailCon {

	public String send(String team1, String team2, String sport, String matchtime, String bookmaker, String market,
			String lines, String sublines, String odds, String units, List<String> subs, String tipster)
			throws ParseException, AddressException {
		final String username = "tip-support@eagletip.com";
		final String password = "Iforget14!";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("EEE dd HH:mm");
		Date date = sdf.parse(matchtime);
		System.out.println(date);
		String mdate = sdf1.format(date);
		List<String> subscribers = (List<String>) subs;
		if (subscribers != null) {
			InternetAddress[] recipientAddresses = new InternetAddress[subscribers.size()];
			int counter = 0;
			for (String sub : subscribers) {
				recipientAddresses[counter] = new InternetAddress(sub.trim());
				counter++;
			}

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

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("tip-support@eagletip.com"));
				message.setRecipients(Message.RecipientType.BCC, recipientAddresses);
				message.setSubject("" + team1 + " vs " + team2 + "");
				message.setContent("<table>"
						+ "<tr><td><img style='width:80px;height:60px' class='img-responsive' src='https://www.eagletip.com/images/css/eagletip.png'></td></tr>"
						+ "<tr><td><h1>EAGLE<span style='color:green'>TIP</span></h1></td></tr>"
						+ "<tr><td style='font-weight:bold'><a href='https://www.eagletip.com/tipster/activetips?name="
						+ tipster + "'>" + tipster + "</a></td></tr>"
						+ "<tr><td>Event:</td><td style='font-weight:bold'>" + team1 + " vs " + team2 + "</td></tr>"
						+ "<tr><td>Match Time:</td><td style='font-weight:bold'>" + mdate + " (GMT+0)</td></tr>"
						+ "<tr><td>Market:</td> <td style='font-weight:bold'>" + market + "</td></tr>"
						+ "<tr><td>Prediction:</td> <td style='font-weight:bold'>" + lines + " " + sublines
						+ "</td></tr>" + "<tr><td>Bookmaker:</td> <td style='font-weight:bold'>" + bookmaker
						+ "</td></tr>" + "<tr><td>Odds:</td><td style='font-weight:bold'>" + odds + "</td></td>"
						+ "<tr><td>Units:</td><td style='font-weight:bold'>" + units + "</td></tr>" + "</table>",
						"text/html");

				Transport.send(message);

				return "sent";

			} catch (MessagingException e) {
				return "error";
			}
		}
		return null;
	}
}
