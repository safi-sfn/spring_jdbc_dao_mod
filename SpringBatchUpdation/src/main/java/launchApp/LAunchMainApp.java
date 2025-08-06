package launchApp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IEmployeeDao;
import dto.Employee;

public class LAunchMainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
		IEmployeeDao employeeDao = (IEmployeeDao)context.getBean("employeeDao");
		
		Employee emp1 = new Employee();
		emp1.setEid(100);
		emp1.setEcity("HYD");
		emp1.setEname("LLL");
		emp1.setEsallary(1000);
		
		Employee emp2 = new Employee();
		emp2.setEid(102);
		emp2.setEcity("HYD");
		emp2.setEname("MMM");
		emp2.setEsallary(1000);
		
		Employee emp3 = new Employee();
		emp3.setEid(103);
		emp3.setEcity("HYD");
		emp3.setEname("NNN");
		emp3.setEsallary(1000);
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		
		int[] rowCounts = employeeDao.insert(empList);
		for(int rowCount : rowCounts) {
			System.out.println("row Count : "+rowCount);
		}
	
		
	}

}
