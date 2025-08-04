package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


import dto.Employee;
//import mapper.EmployeeRowMapper;

//STEP-JDBC- 2. preparing DAO interface Implementation class

public class EmployeeDaoImpl implements IEmployeeDao {

	// 2.a Declare JDBC Template Property and respective Setter
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	


	@Override
	
	public String add(Employee emp) {
		
		String status="";
		try {
			List<Employee> empList = jdbcTemplate.query("SELECT * FROM Employee WHERE eId = "+emp.getEid(), // Before we use like this: new EmployeeRowMapper
				(rs,index) ->{  // Replacing Employee row Mapper with lambda expression  
					Employee emp1 = new Employee();
					emp.setEid(rs.getInt("eId"));
					emp.setEname(rs.getString("eName"));
					emp.setEsallary(rs.getFloat("eSallary"));
					emp.setEcity(rs.getString("eCity"));
					return emp;
			  });
			if(empList.isEmpty() == true) {
				int rowCount = jdbcTemplate.update("INSERT INTO Employee VALUES(" + emp.getEid() + ",'" + emp.getEcity() + "','" + emp.getEname() + "'," + emp.getEsallary() + ")");     
				if(rowCount == 1) {
					status="Employee inserted successfull";
				}else {
					status="Employee insertion failure";
				}
			}else {
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status="Employee insertion failure";
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public Employee search(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int eid) {
		// TODO Auto-generated method stub
		return null;
	}

}
