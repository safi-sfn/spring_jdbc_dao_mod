package launchMain;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IEmployeeDAO;
import dto.EmployeeDTO;


public class LaunchMainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
		IEmployeeDAO empDao = (IEmployeeDAO)context.getBean("employeeDao");
		
	/*	
		File file1 = new File("/home/safi/Pictures/Screenshots/emp1.jpg");
		File file2 = new File("/home/safi/Pictures/Screenshots/HunterBrownResume.pdf");
		EmployeeDTO emp1 = new EmployeeDTO();
		emp1.setEno(101);
		emp1.setEname("Hunter Brown");
		emp1.setEmp_image(file1);
		emp1.setEmp_resume(file2);
		empDao.insertEmployee(emp1);
		System.out.println("Employee Inserted Successfully");
	*/
		
		EmployeeDTO emp = empDao.readEmployee(101);
		System.out.println("Employee Retrived Successfully");
		System.out.println("Employee Details");
		System.out.println("---------------------");
		System.out.println("Employee Number :"+emp.getEno());
		System.out.println("Employee Name   :"+emp.getEname());
		System.out.println("Employee Image  :"+emp.getEmp_image().getAbsolutePath());
		System.out.println("Employee Resume :"+emp.getEmp_resume().getAbsolutePath());
	}

}
