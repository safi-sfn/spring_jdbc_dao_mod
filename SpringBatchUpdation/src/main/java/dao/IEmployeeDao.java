package dao;

import java.util.List;

import dto.Employee;

public interface IEmployeeDao {
	
	public int[] insert (List<Employee> empList);
	

}
