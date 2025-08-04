package launchApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IEmployeeDao;
import dto.Employee;

public class LAunchMainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
		IEmployeeDao employeeDao = (IEmployeeDao)context.getBean("employeeDao");
		Employee emp = new Employee();
		emp.setEid(112);
		emp.setEname("Gulam");
		emp.setEcity("HYD");
		emp.setEsallary(50000);
		String status = employeeDao.add(emp);
		System.out.println(status);
	}

}
