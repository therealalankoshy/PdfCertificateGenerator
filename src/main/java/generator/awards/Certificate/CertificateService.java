package generator.awards.Certificate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class CertificateService {

	public void createCertificate(String name,String projectName) throws FileNotFoundException, IOException, DocumentException {
		PdfReader reader = new PdfReader("/home/alan/Downloads/Certificate_of_Participation.pdf");
		PdfStamper stamper = new PdfStamper(reader,
		          new FileOutputStream("/home/alan/Desktop/certificates/"+name.replaceAll("[^a-zA-Z0-9]", "")+"_"+projectName.replaceAll("[^a-zA-Z0-9]", "")+".pdf")); // output PDF
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		//loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setRGBColorFill(170,28,35);
            //over.setRGBColorStroke(170,28,35);
            over.setFontAndSize(bf, 40);    // set font and size
            over.setTextMatrix(265, 261);   // set x,y position (0,0 is at the bottom left)
            over.showText(name);  // set text
            over.endText();
            
            // write text
            over.beginText();
            over.setRGBColorFill(170,28,35);
            //over.setRGBColorStroke(170,28,35);
            over.setFontAndSize(bf, 15);    // set font and size
            over.setTextMatrix(265, 173);   // set x,y position (0,0 is at the bottom left)
            over.showText(projectName);  // set text
            over.endText();
        }
		stamper.close();
		reader.close();
	}
}
