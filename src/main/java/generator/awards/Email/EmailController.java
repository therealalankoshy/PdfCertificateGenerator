package generator.awards.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	@GetMapping(value = "sample")
	void sample() {
		try {
			emailService.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("API Working");
	}
	
}
