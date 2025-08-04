package launchApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IEmployeeDao;
import dto.Employee;

public class LAunchMainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
		IEmployeeDao employeeDao = (IEmployeeDao)context.getBean("employeeDao");
	/*
		Employee emp = new Employee();
		emp.setEid(112);
		emp.setEname("Gulam");
		emp.setEcity("HYD");
		emp.setEsallary(50000);
		String status = employeeDao.add(emp);
		System.out.println(status);
	*/
		
	/*	
		Employee emp = employeeDao.search(114);
		if(emp == null) {
			System.out.println("Employee not existed");
		}
		else {
			System.out.println("Employee detail");
			System.out.println("---------------------");
			System.out.println("Employee ID      : "+emp.getEid());
			System.out.println("Employee Name    : "+emp.getEname());
			System.out.println("Employee Sallary : "+emp.getEsallary());
			System.out.println("Employee City    : "+emp.getEcity());
		}
	*/
		
	/*
		Employee emp = new Employee();
		emp.setEid(112);
		emp.setEname("Gustavo");
		emp.setEcity("ENG");
		emp.setEsallary(12000);
		String status = employeeDao.update(emp);
		System.out.println(status);
	*/
		
		String status = employeeDao.delete(114);
		System.out.println(status);
		
		
	}

}
