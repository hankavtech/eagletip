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

public class PaymentEmailSender {

	public String send(String orderid, String user, String email, String product, String plan, String start, String end,
			String price) throws ParseException, AddressException {

		final String username = "payments@eagletip.com";
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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("payments@eagletip.com"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("" + product + " Order Confirmation");
			message.setContent("<table>"
					+ "<tr><td><img style='width:80px;height:60px' src='https://www.eagletip.com/images/css/eagletip.png'></td></tr>"
					+ "<tr><td><h1>EAGLE<span style='color:green'>TIP</span></h1></td></tr>" + "<tr><p>Hi " + user
					+ ", we've received your order #" + orderid + ".</p></tr>"
					+ "<tr><p>Please make sure that you have verified your email during registration if you haven't.</p></tr>"
					+ "<tr><p>You can see all your active tips in your profile page instantaneously</p></tr>"
					+ "<tr><td> <h2>Item ordered<br></h2></td</tr>"
					+ "<tr><td><img style='width:50px;height:50px' src='https://www.eagletip.com/images/tipsters/"
					+ product + ".jpg'><a href='https://www.eagletip.com/tipster/activetips?name="
					+ product.replace("+", "%20") + "'>" + product + "</a></td><td><table><tr><p>Subscription plan: "
					+ plan + " month</p></tr><tr><p>Start date:" + start + "</p></tr><tr><p>End date:" + end
					+ "</p></tr></table></td></tr>"
					+ "<tr><p>Email us <a href='mailto:info@eagletip.com?Subject=Order%20Enquiry' target='_top'>here</a> for assistance.</p></tr>"
					+ "<tr><td><p>Note:Free tipsters' subscription is valid only only the tipster is available as free and the subscription will be terminated when the tipster goes premium.</p></td></tr>"
					+ "</table>", "text/html");

			Transport.send(message);

			return "sent";

		} catch (MessagingException e) {
			return "error";
		}
	}

}
