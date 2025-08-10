package dto;

import java.io.File;

public class EmployeeDTO {
	private int eno;
	private String ename;
	private File emp_image;
	private File emp_resume;

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public File getEmp_image() {
		return emp_image;
	}

	public void setEmp_image(File emp_image) {
		this.emp_image = emp_image;
	}

	public File getEmp_resume() {
		return emp_resume;
	}

	public void setEmp_resume(File emp_resume) {
		this.emp_resume = emp_resume;
	}

}
