package generator.awards.Email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	@GetMapping(value = "sample")
	void sample() {
		try {
			 FileSystemResource file = new FileSystemResource(
			 new File(
			 "/home/alan/Downloads/Certificate_of_Participation.pdf"));
			emailService.sendEmail("alan.bk@inapp.com","alan.bk@inapp.com",file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("API Working");
	}
}
