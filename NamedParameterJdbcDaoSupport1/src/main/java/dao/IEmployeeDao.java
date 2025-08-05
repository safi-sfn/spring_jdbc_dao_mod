package dao;

import dto.Employee;

public interface IEmployeeDao {
	
	public String add(Employee emp);
	public Employee search(int eid);
	public String update(Employee emp);
	public String delete(int eid);
}
