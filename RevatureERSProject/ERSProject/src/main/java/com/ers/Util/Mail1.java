package com.ers.Util;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail1 {

	
	public static void sendEmail(String rec) throws MessagingException
	{

		// TODO Auto-generated method stub
System.out.println("Prapring to send");

		Properties prop = new Properties();
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.auth","true");
		
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.port","587");
		
		String mtAcnt= "ExpenseReimbursementSystem@gmail.com";
		String pwd= "erspassword";
		
		Session ses= Session.getInstance(prop, new Authenticator() {
			
			@Override
			 protected PasswordAuthentication getPasswordAuthentication()
			 {	
				
				return new  PasswordAuthentication(mtAcnt, pwd);
			 }
			});
		Message msg= prepareMessage(ses,mtAcnt,rec);
		Transport.send(msg);
		System.out.println("Msge sent");
			  
	}
	
	
	
	private static Message prepareMessage(Session ses, String acnt,String rec) {
		// TODO Auto-generated method stub
		try {
			
			Message msg = new MimeMessage(ses);
			msg.setFrom(new InternetAddress(acnt));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
			msg.setSubject("ERS Login");
			msg.setText("This is to Confirm your login to ERS Services!!!");
			return msg;
		}
		catch(Exception e)
		{
			//
			e.printStackTrace();
			
		}
		return null;
	}



	public static void main(String[] args) throws MessagingException {
		
		sendEmail("divyareddy416@gmail.com");
		
	}
		
	}


