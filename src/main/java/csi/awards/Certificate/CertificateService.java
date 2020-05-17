package csi.awards.Certificate;

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

	public void createCertificate() throws FileNotFoundException, IOException, DocumentException {
		PdfReader reader = new PdfReader("/home/alan/Downloads/Certificate_of_Participation.pdf");
		PdfStamper stamper = new PdfStamper(reader,
		          new FileOutputStream("/home/alan/Downloads/Certificate_of_Participation_change.pdf")); // output PDF
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
            over.showText("Alan Baby Koshy");  // set text
            over.endText();
            
         // write text
            over.beginText();
            over.setRGBColorFill(170,28,35);
            //over.setRGBColorStroke(170,28,35);
            over.setFontAndSize(bf, 30);    // set font and size
            over.setTextMatrix(265, 173);   // set x,y position (0,0 is at the bottom left)
            over.showText("TVAAKU");  // set text
            over.endText();
//
//            // draw a red circle
//            over.setRGBColorStroke(0xFF, 0x00, 0x00);
//            over.setLineWidth(5f);
//            over.ellipse(250, 450, 350, 550);
//            over.stroke();
        }
		stamper.close();
		reader.close();
	}
}
