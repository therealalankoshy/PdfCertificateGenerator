package generator.awards.Email;

import generator.awards.Certificate.CertificateData;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Value("${email.subject}")
	private String subject;
	@Value("${email.body}")
	private String body;
	@Value("${pdf.location.source}")
	private String sourcePDFLocation;
	@Value("${pdf.location.destination}")
	private String destinationPDFLocation;
	@Value("${email.from}")
	private String fromEmail;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	void sendEmail(String from, String to, FileSystemResource file) throws AddressException, MessagingException, IOException {

		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setFrom(from);
		helper.setText(body, true);
		helper.addAttachment("Certificate.pdf", file);
		javaMailSenderImpl.send(message);
	}

	public void sendCertificate(CertificateData data) {
		try {
			 FileSystemResource file = new FileSystemResource(
			 new File(destinationPDFLocation+data.getName().replaceAll("[^a-zA-Z0-9]", "")+"_"+data.getProjectName().replaceAll("[^a-zA-Z0-9]", "")+".pdf"));
			sendEmail(fromEmail,data.getEmailId(),file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
