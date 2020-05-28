package generator.awards.Certificate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

@RestController
public class CertificateController {
	private final static Logger LOGGER =  
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private CertificateService certificateService;

	@GetMapping(value = "api/sample/create")
	void createCertificate() throws FileNotFoundException, IOException, DocumentException {
		CertificateData data = new CertificateData();
		data.setEmailId("alan.bk@inapp.com");
		data.setId(new Double("1"));
		data.setName("Alan Baby Koshy");
		data.setProjectName("TVAAKU");
		certificateService.createCertificate(data);
		LOGGER.log(Level.INFO, "Certificate Generated"); 
	}
}
