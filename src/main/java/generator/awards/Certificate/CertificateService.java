package generator.awards.Certificate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class CertificateService {

	@Value("${pdf.location.source}")
	private String sourcePDFLocation;
	@Value("${pdf.location.destination}")
	private String destinationPDFLocation;

	public void createCertificate(CertificateData data) throws FileNotFoundException, IOException, DocumentException {
		PdfReader reader = new PdfReader(sourcePDFLocation);
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
				destinationPDFLocation + data.getId().toString().replaceAll("[^a-zA-Z0-9]", "")
				+ data.getName().replaceAll("[^a-zA-Z0-9]", "")
						+ ".pdf")); // output PDF
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {

			PdfContentByte over = stamper.getOverContent(i);

			// write text for Name
			// Change the values for Text matrix to Reposition your Data
			over.beginText();
			over.setRGBColorFill(170, 28, 35);
			over.setFontAndSize(bf, 40); // set font and size
			over.setTextMatrix(265, 261); // set x,y position (0,0 is at the
			over.showText(data.getName()); // set text
			over.endText();

			// write text for Project Name
			// Change the values for Text matrix to Reposition your Data
			over.beginText();
			over.setRGBColorFill(170, 28, 35);
			over.setFontAndSize(bf, 15); // set font and size
			over.setTextMatrix(265, 173); // set x,y position (0,0 is at the
			over.showText(data.getProjectName()); // set text
			over.endText();
		}
		stamper.close();
		reader.close();
	}
}
