package generator.awards.Email;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	private final static Logger LOGGER =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Value("${pdf.location.source}")
	private String docPath;
	@Autowired
	private EmailService emailService;
	@Value("${email.from}")
	private String fromEmail;

	@GetMapping(value = "api/sample/email")
	void sample() {
		try {
			FileSystemResource file = new FileSystemResource(new File(docPath));
			emailService.sendEmail(fromEmail,fromEmail,file);
			LOGGER.log(Level.INFO,"Sample Email Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
