package generator.awards.Certificate;

import java.math.BigDecimal;

public class CertificateData {

	private Double id;
	private String name;
	private String emailId;
	private String projectName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

}
