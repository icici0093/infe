package com.infe.common;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSend {
	
//	public static String host = "210.114.108.2";//smtp 서버
//	public static String subject = "";
//	public static String from = "epic@kdi.re.kr"; //보내는 메일
//	public static String fromName = "KDI";
//	public static String content = "";	
//	
//	public static String msg_default = "";
//
//	public static void send (String to,String msgSubj, String msgText) throws Exception {
//		msgText = msgText + msg_default;
//        subject = msgSubj;
//        content = msgText;
//
//        try{
//            // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
//            Properties props = new Properties();
//            props.put("mail.smtp.host",host);
//            
//            Session mailSession = Session.getInstance(props, null);           
//            Message msg = new MimeMessage(mailSession);
//
//            msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//보내는 사람 설정
//
//			InternetAddress[] address = {new InternetAddress(to)};  // 받는 사람
//			msg.setRecipients(Message.RecipientType.TO, address);//받는 사람설정
//
//            // InternetAddress[] addresscc = {new InternetAddress(from)}; //참조인
//            // msg.setRecipients(Message.RecipientType.CC, addresscc);  // 참조설정
//            
//            msg.setSubject(MimeUtility.encodeText(subject, "utf-8","B"));// 제목 설정
//            msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
//            msg.setContent(content,"text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
//            
//            Transport.send(msg); // 메일 보내기
//            System.out.println(from + " -> " + to + " 메일 발송을 완료하였습니다.");
//        } catch ( MessagingException ex ) {
//            System.out.println("mail send error : " + ex.getMessage());
//        } catch ( Exception e ) {
//            System.out.println("error : " + e.getMessage());
//        }
//	}	
	public static String host = "210.114.108.2";//smtp 서버
	public static String subject = "";
	public static String from = "infe@cubea.co.kr"; //보내는 메일
	public static String password = "rkskek11!"; //보내는 메일
	public static String fromName = "KDI";
	public static String content = "";
	
	public static String msg_default = "";

	public static void send (String to,String msgSubj, String msgText) throws Exception {
		msgText = msgText + msg_default;
        subject = msgSubj;
        content = msgText;

        try{
            // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            
//            Session mailSession = Session.getInstance(props, null);           
            Session mailSession = Session.getDefaultInstance(props, 
            	    new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(
                        "infe@cubea.co.kr", "rkskek11!");// Specify the Username and the PassWord
                }
        });        
            Message msg = new MimeMessage(mailSession);

            msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//보내는 사람 설정
            
			InternetAddress[] address = {new InternetAddress(to)};  // 받는 사람
			msg.setRecipients(Message.RecipientType.TO, address);//받는 사람설정

            // InternetAddress[] addresscc = {new InternetAddress(from)}; //참조인
            // msg.setRecipients(Message.RecipientType.CC, addresscc);  // 참조설정
            
            msg.setSubject(MimeUtility.encodeText(subject, "utf-8","B"));// 제목 설정
            msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
            msg.setContent(content,"text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
            
            Transport.send(msg); // 메일 보내기
            System.out.println(from + " -> " + to + " 메일 발송을 완료하였습니다.");
        } catch ( MessagingException ex ) {
            System.out.println("mail send error : " + ex.getMessage());
        } catch ( Exception e ) {
            System.out.println("error : " + e.getMessage());
        }
	}

	public void send (String from, String to, String msgSubj, String msgText) throws Exception {
		msgText = msgText + msg_default;
        subject = msgSubj;
        content = msgText;

        try{
            // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
            Properties props = new Properties();
            props.put("mail.smtp.host",host);
            
            
            Session mailSession = Session.getInstance(props, null);
            Message msg = new MimeMessage(mailSession);

            msg.setFrom(new InternetAddress(from)); //보내는 email
            //msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B"))); //보내는 사람명과 email

			InternetAddress[] address = {new InternetAddress(to)}; // 받는 사람
            msg.setRecipients(Message.RecipientType.TO, address); //받는 사람설정

			// InternetAddress[] addresscc = {new InternetAddress(from)}; // 참조 메일
            // msg.setRecipients(Message.RecipientType.CC, addresscc);  // 참조 설정
            
            msg.setSubject(MimeUtility.encodeText(subject, "utf-8","B"));// 제목 설정
            msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
            msg.setContent(content,"text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
            
            Transport.send(msg); // 메일 보내기
            System.out.println(from + " -> " + to + " 메일 발송을 완료하였습니다.");
        } catch ( MessagingException ex ) {
            System.out.println("mail send error : " + ex.getMessage());
        } catch ( Exception e ) {
            System.out.println("error : " + e.getMessage());
        }
	}
	
	public void send (String fromemail, String fromsendname, String to, String msgSubj, String msgText) throws Exception {
		msgText = msgText + msg_default;
        subject = msgSubj;
        content = msgText;

        try{
            // 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
            Properties props = new Properties();
            props.put("mail.smtp.host",host);
            
            Session mailSession = Session.getInstance(props, null);           
            Message msg = new MimeMessage(mailSession);

            msg.setFrom(new InternetAddress(fromemail, MimeUtility.encodeText(fromsendname,"UTF-8","B")));//보내는 사람 설정

			InternetAddress[] address = {new InternetAddress(to)};  // 받는 사람
			msg.setRecipients(Message.RecipientType.TO, address);//받는 사람설정

            // InternetAddress[] addresscc = {new InternetAddress(from)}; //참조인
            // msg.setRecipients(Message.RecipientType.CC, addresscc);  // 참조설정
            
            msg.setSubject(MimeUtility.encodeText(subject, "utf-8","B"));// 제목 설정
            msg.setSentDate(new java.util.Date());// 보내는 날짜 설정
            msg.setContent(content,"text/html;charset=UTF-8"); // 내용 설정 (HTML 형식)
            
            Transport.send(msg); // 메일 보내기
            System.out.println(fromemail + " -> " + to + " 메일 발송을 완료하였습니다.");
        } catch ( MessagingException ex ) {
            System.out.println("mail send error : " + ex.getMessage());
        } catch ( Exception e ) {
            System.out.println("error : " + e.getMessage());
        }
	}
	
	
	
	
	public static String SendForm(String FilePath)
    {
        StringBuffer obj = new StringBuffer();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath),"UTF-8"));
            String str = null;
            do
            {
                str = br.readLine();
                if(str != null)
                    obj.append(str);
            } while(str != null);
            br.close();
            //fr.close();
        }
        catch(Exception e)
        {
        }
        return obj.toString();
    }		
}