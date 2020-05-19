package generator.awards.Excel;

import java.io.IOException;

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
    @PostMapping("/api/load/sheet")
    public void uploadSheet(@RequestParam("template") MultipartFile template) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(template.getInputStream());
        excelService.loadSheet(workbook);
    }
}
