package com.ksoe.energynet.util.tools;

import java.net.InetAddress;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class NetTools {
	
	/**
	 * 
	 * Получить IP-адрес сервера
	 * 
	 * @return IP-адрес сервера или пустую строку в случае ошибки
	 */
	public static String getInetAddress() {
		String ipAddres = "";
		try {

			InetAddress inetAddress = InetAddress.getLocalHost();
			ipAddres = inetAddress.getHostAddress();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ipAddres;
	}

	/**
	 * 
	 * Отправка текстового сообщения от анонимного электронного адреса EnergyNet
	 * 
	 * @param email адрес для отправки
	 * @param title тема сообщения
	 * @param body сообщения
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendMail(String email, String title, String body) throws AddressException, MessagingException {
		String FROM = "ENERGYNET<noreply@docflow.ksoe.com.ua>";
		String MAIL_ENCODING_UTF8 = "UTF-8";
		
		Properties mailProps = new Properties();
		mailProps.put("mail.store.protocol", "pop3");
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.pop3.host", "10.77.11.51");
		mailProps.put("mail.smtp.host", "10.77.11.51");
		
		Session session = Session.getDefaultInstance(mailProps, null);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM));
		message.setHeader("X-Priority", "1");
		message.setSubject(title, MAIL_ENCODING_UTF8);
		message.setText(body, MAIL_ENCODING_UTF8);
		
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		Transport.send(message);
	}
}
