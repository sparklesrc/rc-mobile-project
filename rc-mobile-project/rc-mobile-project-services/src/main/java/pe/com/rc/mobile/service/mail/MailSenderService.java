package pe.com.rc.mobile.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {

	@Autowired
	private MailComponent mail;

	public void sendMail(String to, String msgToAdd) {
		String sender = "info-solommr@gmail.com";
		mail.sendMail(sender, to, "Asunto", prepareMessage(msgToAdd));

	}

	private String prepareMessage(String msgToAdd) {
		return "El codigo de verificacion es " + msgToAdd;
	}
}
