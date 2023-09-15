package xyz.itwill.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//이메일을 보내는 역할을 하는 클래스
public class EmailSender {
	public static void sendEmail(String recipient, String subject, String content) {
		//이메일 설정 정보는 외부 프로퍼티 파일 사용
		Properties properties = EmailPropertiesReader.loadProperties();
		
		String email = properties.getProperty("email");
		String password = properties.getProperty("password");
		
		Properties props= new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		//세션 생성
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8"); // HTML 형식으로 전송

            // 메일 전송
            Transport.send(message);

            System.out.println("이메일 전송 완료!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
