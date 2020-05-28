package generator.awards.Excel;

import generator.awards.Certificate.CertificateData;
import generator.awards.Certificate.CertificateService;
import generator.awards.Email.EmailService;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelController {
	@Autowired
	private ExcelService excelService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private CertificateService certificateService;
	
    @PostMapping("/api/load/sheet")
    public void uploadSheet(@RequestParam("template") MultipartFile template) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(template.getInputStream());
		List<CertificateData> data = excelService.loadSheet(workbook);
		data.forEach(datum->{
			try {
				System.out.println(datum.getId());
				certificateService.createCertificate(datum);
				emailService.sendCertificate(datum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
    }
}
