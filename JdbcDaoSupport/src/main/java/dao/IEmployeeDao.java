package dao;

import java.util.List;

import dto.Employee;

public interface IEmployeeDao {
	

	public String add(Employee emp);
	public Employee search(int eid);
	public List<Employee> getAllEmployees();
	public String update(Employee emp);
	public String delete(int eid);
}
