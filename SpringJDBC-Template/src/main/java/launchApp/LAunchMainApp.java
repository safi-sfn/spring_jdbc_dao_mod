package launchApp;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IEmployeeDao;
import dto.Employee;

public class LAunchMainApp {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationConfig.xml");
		IEmployeeDao employeeDao = (IEmployeeDao)context.getBean("employeeDao");
		
		Scanner scn = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. Add Employee");
			System.out.println("2. Search Employee");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.print("Choose an Option from : 1,2,3,4,5 : ");
			int option = scn.nextInt();
			
			System.out.println();
			Employee emp = null;
			String status = "";
			int eid = 0;
			String ename = "";
			float esallary = 0.0f;
			String ecity = "";
			
			
			switch (option) {
			case 1:
				System.out.println("=======ADD MODULE=======");
				System.out.print("Employee ID      : ");
				 eid = scn.nextInt();
				System.out.print("Employee Name    : ");
				 ename = scn.next();
				System.out.print("Employee Sallary : ");
				 esallary = scn.nextInt();
				System.out.print("Employee City    : ");
				 ecity = scn.next();
				emp = new Employee();
				emp.setEid(eid);
				emp.setEname(ename);
				emp.setEsallary(esallary);
				emp.setEcity(ecity);
				 status = employeeDao.add(emp);
				System.out.println(status);
				  
				break;
				
				
			case 2:
				System.out.println("=========SEARCH MODULE========");
				System.out.print("Enter Employee ID   : ");
				 eid = scn.nextInt();
		
				emp = employeeDao.search(eid);
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
				
				break;
				
				
			case 3:
				System.out.println("=========UPDATE MODULE========");
				System.out.print("Enter Employee ID   : ");
				 eid = scn.nextInt();
				 emp = employeeDao.search(eid);
				 if(emp == null) {
					 System.out.println("Employee Not Existed");
				 }else {
					
					 System.out.print("Old Employee Name    : "+emp.getEname()+"     : ");
					 String new_ename = scn.next();
					 System.out.print("Old Employee Sallary : "+emp.getEsallary()+"  : ");
					 float new_esallary = scn.nextInt();
					 System.out.print("Old Employee City    : "+emp.getEcity()+"     : ");
					 String new_ecity = scn.next();
					 
					 Employee emp_new = new Employee();
					 emp_new.setEid(eid);
					 emp_new.setEname(new_ename);
					 emp_new.setEsallary(new_esallary);
					 emp_new.setEcity(new_ecity);
					 status = employeeDao.update(emp_new);
					 System.out.println(status);
				 }
				
				break;
				
				
			case 4:
				System.out.println("=========DELETE MODULE========");
				System.out.print("Enter Employee ID   : ");
				 eid = scn.nextInt();
				status = employeeDao.delete(eid);
				System.out.println(status);
				break;
			case 5:
				System.out.println("********Thanks for using this App**********");
				System.exit(0);
				break;

			default:
				System.out.println("Enter the Option from 1,2,3,4,5");
				break;
			}
		}
		
		
		
		
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
		
//		String status = employeeDao.update(112);
//		 System.out.println(status);
		
		
	}

}
