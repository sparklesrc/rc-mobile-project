package pe.com.rc.mobile.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSenderImpl {

	@Autowired
	private Mail mail;

	public void sendMail(String to) {
		String sender = "info-solommr@gmail.com";
		mail.sendMail(sender, to, "Asunto", prepareMessage());

	}

	private String prepareMessage() {
		return "Welcome";
	}
}
