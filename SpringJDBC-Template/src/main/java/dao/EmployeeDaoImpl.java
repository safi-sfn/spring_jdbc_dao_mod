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

		String status = "";
		try {
			List<Employee> empList = jdbcTemplate.query("SELECT * FROM Employee WHERE eId = " + emp.getEid(), // Before : new EmployeRowMapper() 
																												
					(rs, index) -> { // Replacing Employee row Mapper with lambda expression
						Employee emp1 = new Employee();
						emp.setEid(rs.getInt("eId"));
						emp.setEname(rs.getString("eName"));
						emp.setEsallary(rs.getFloat("eSallary"));
						emp.setEcity(rs.getString("eCity"));
						return emp;
					});
			if (empList.isEmpty() == true) {
				int rowCount = jdbcTemplate.update("INSERT INTO Employee VALUES(" + emp.getEid() + ",'" + emp.getEcity()
						+ "','" + emp.getEname() + "'," + emp.getEsallary() + ")");
				if (rowCount == 1) {
					status = "Employee inserted successfull";
				} else {
					status = "Employee insertion failure";
				}
			} else {
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status = "Employee insertion failure";
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public Employee search(int eid) {
		Employee emp = null;
		try {
			List<Employee> empList = jdbcTemplate.query("select * from Employee where eId = " + eid, (rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEid(rs.getInt("eId"));
				emp1.setEname(rs.getString("eName"));
				emp1.setEsallary(rs.getFloat("eSallary"));
				emp1.setEcity(rs.getString("eCity"));
				return emp1;
			});

			if (empList.isEmpty() == true) {
				return null;
			} else {
				emp = empList.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public String update(Employee emp) {
		String status = "";
		try {
//			Employee emp1 = search(emp.getEid());
//			if(emp1 == null) {
//				status = "Employee not existed";
//			}else {
			int rowCount = jdbcTemplate.update("update Employee set eName = '" + emp.getEname() + "', eSallary = "
					+ emp.getEsallary() + ", eCity = '" + emp.getEcity() + "' where eId = " + emp.getEid());
			if (rowCount == 1) {
				status = "employee updated successfully";
			} else {
				status = "employee updation failure....";
			}
//			}
		} catch (Exception e) {
			System.out.println("employee updation failure----");
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int eid) {
		String status = "";
		try {
			Employee emp = search(eid);
			if (emp == null) {
				status = "Employee not existed";
			} else {
				int rowCount = jdbcTemplate.update("delete from Employee where eId = " + eid);
				if (rowCount == 1) {
					status = "Deletion success";
				} else {
					status = "Deleation failure";
				}
			}
		} catch (Exception e) {
			status = "Deletion failure";
			e.printStackTrace();
		}
		return status;
	}

}
