package generator.awards.Certificate;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

@RestController
public class CertificateController {

	@Autowired
	private CertificateService certificateService;

	@GetMapping(value = "create")
	void createCertificate() throws FileNotFoundException, IOException, DocumentException {
		certificateService.createCertificate();
	}
}
