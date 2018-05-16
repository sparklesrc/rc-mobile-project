package pe.com.rc.mobile.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {

	@Autowired
	private MailComponent mail;

	public static final String sender = "info-solommr@gmail.com";

	public void sendMail(String to, String msgToAdd) {
		mail.sendMail(sender, to, "Asunto", prepareMessage(msgToAdd));

	}

	private String prepareMessage(String msgToAdd) {
		return "El codigo de verificacion es " + msgToAdd;
	}

	public void sendRecruitMail(String to, String message, String leader, String team) {
		mail.sendMail(sender, to, "Asunto", prepareRecruitMessage(message, leader, team));
	}

	private String prepareRecruitMessage(String message, String leader, String team) {
		return "El Leader " + leader + " del equipo " + team + " desea invitarte a su equipo. \nEste es su mensaje: \n\n"
				+ message + "\n\n****************************"
				+ "\nTe recomendamos revisar tus solicitudes en tu cuenta. \nhttp://solommr.com";
	}

}
