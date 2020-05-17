package generator.awards.Email;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	void sendEmail() throws AddressException, MessagingException, IOException {

		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo("alan.bk@inapp.com");
		helper.setSubject("Sample Sub");
		helper.setText("helo gelooo");
		helper.setFrom("alan.bk@inapp.com");
		FileSystemResource file = new FileSystemResource(
				new File(
						"/home/alan/Desktop/CSI-InApp_Second_Round_Evaluation_Results.pdf"));
		helper.addAttachment("Result.pdf", file);

		javaMailSenderImpl.send(message);

	}
}
