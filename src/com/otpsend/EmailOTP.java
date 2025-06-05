package com.otpsend;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailOTP {

	public static void main(String[] args) {

		// Scanner object to take user input
		Scanner scanner = new Scanner(System.in);

		// Asking for recipient email from console
		System.out.print("Enter recipient email: ");
		String recipientEmail = scanner.nextLine(); // User input email
		String subject = "Your OTP Code";
		String otp = generateOTP();
		String body = "Your OTP is: " + otp;

		sendEmail(recipientEmail, subject, body);
	}

	public static String generateOTP() {
		Random random = new Random();
		int otpValue = 100000 + random.nextInt(900000); // 6-digit OTP
		return String.valueOf(otpValue);
	}

	public static void sendEmail(String recipientEmail, String subject, String body) {
	    final String senderEmail = "ggmhavale0002@gmail.com"; // Replace with your Gmail
	    final String appPassword = "ylhu bowc xawq euqy"; // Replace with your generated App Password

	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(senderEmail, appPassword);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(senderEmail));
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail)); // यहीं change जरूरी है
	        message.setSubject(subject);
	        message.setText(body);

	        Transport.send(message); // ये भेजने वाला command है
	        System.out.println("OTP sent successfully to " + recipientEmail);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("Failed to send OTP. Please check the email ID or settings.");
	    }
	}
}