package dao;

import dto.EmployeeDTO;

public interface IEmployeeDAO {
	public void insertEmployee(EmployeeDTO emp);
	public EmployeeDTO readEmployee();
}
