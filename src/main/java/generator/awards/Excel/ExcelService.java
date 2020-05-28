package generator.awards.Excel;

import static com.google.common.base.Preconditions.checkNotNull;
import generator.awards.Certificate.CertificateData;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {
	private final String ID = "Id";
	private final String NAME = "Name";
	private final String EMAIL_ID = "Email ID";
	private final String PROJECT_NAME = "Project Name";

	private Integer nameCell = null, emailCell = null, projectCell = null,idCell = null;
    
	public List<CertificateData> loadSheet(Workbook workbook) {
		Sheet sheet = workbook.getSheet("Data_List");
		validateIssuesSheet(sheet);
		List<CertificateData> data = new LinkedList<CertificateData>();
		//Row header = sheet.getRow(0);

		for (Row row : sheet) {
			if (row.getRowNum() != 0 && row.getCell(idCell) != null) {
				CertificateData datum = new CertificateData();
				String name = row.getCell(nameCell).getStringCellValue().trim();
				String email = row.getCell(emailCell).getStringCellValue().trim();
				String projectName = row.getCell(projectCell).getStringCellValue().trim();
				Double id = row.getCell(idCell).getNumericCellValue();

				datum.setId(id != null ? id : new Double("0"));
				datum.setName(name != null ? name : "");
				datum.setEmailId(email != null ? email : "");
				datum.setProjectName(projectName != null ? projectName : "");
				data.add(datum);
			}
		}
		return data;
	}

	private void validateIssuesSheet(Sheet sheet) {
		checkNotNull(sheet,
				"Your template workbook must contain a sheet named 'Data_List'.");

		for (Cell cell : sheet.getRow(0)) {
			if (NAME.equals(cell.getStringCellValue().trim())) {
				nameCell = cell.getColumnIndex();
			}
			if (EMAIL_ID.equals(cell.getStringCellValue().trim())) {
				emailCell = cell.getColumnIndex();
			}
			if (PROJECT_NAME.equals(cell.getStringCellValue().trim())) {
				projectCell = cell.getColumnIndex();
			}
			if (ID.equals(cell.getStringCellValue().trim())) {
				idCell = cell.getColumnIndex();
			}

			if (nameCell != null && emailCell != null && projectCell != null && idCell != null ) {
				break;
			}
		}

		checkNotNull(nameCell, "'Issues' sheet must have a column called %s",NAME);
		checkNotNull(emailCell, "'Issues' sheet must have a column called %s",EMAIL_ID);
		checkNotNull(projectCell,"'Issues' sheet must have a column called %s", PROJECT_NAME);
		checkNotNull(projectCell,"'Issues' sheet must have a column called %s", ID);
	}
}
