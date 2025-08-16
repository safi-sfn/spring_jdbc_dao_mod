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
			
			// Check if employee already exists by ID
			List<Employee> empList = jdbcTemplate.query("SELECT * FROM Employee WHERE eId = " + emp.getEid(), // Before : new EmployeRowMapper() 
																												
					(rs, index) -> { // Replacing Employee row Mapper with lambda expression
						Employee emp1 = new Employee();
						emp.setEid(rs.getInt("eId"));
						emp.setEname(rs.getString("eName"));
						emp.setEsallary(rs.getFloat("eSallary"));
						emp.setEcity(rs.getString("eCity"));
						return emp;
					});
			
			 // If no existing employee found
			if (empList.isEmpty() == true) {
				
				 // Insert new employee (using string concatenation - not parameterized)
				int rowCount = jdbcTemplate.update("INSERT INTO Employee VALUES(" + emp.getEid() + ",'" + emp.getEcity()
						+ "','" + emp.getEname() + "'," + emp.getEsallary() + ")");
				
				 // Check if insert was successful
				if (rowCount == 1) {
					status = "Employee inserted successfull";   // Success message
				} else {
					status = "Employee insertion failure";    // Failure message
				}
			} else {
				// Employee with this ID already exists
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status = "Employee insertion failure";
			e.printStackTrace();
		}

		// Return operation status
		return status;
	}

	@Override
	public Employee search(int eid) {
		
		 // Initialize employee as null (will remain null if not found or on error)
		Employee emp = null;
		try {
			 // Execute SQL query to find employee by ID
	        // WARNING: String concatenation makes this vulnerable to SQL injection
			List<Employee> empList = jdbcTemplate.query("select * from Employee where eId = " + eid,
			
			// Lambda-based RowMapper to convert ResultSet to Employee object
				(rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEid(rs.getInt("eId"));
				emp1.setEname(rs.getString("eName"));
				emp1.setEsallary(rs.getFloat("eSallary"));
				emp1.setEcity(rs.getString("eCity"));
				return emp1;
			});

			// Check if query returned any results
			if (empList.isEmpty() == true) {
				return null;
			} else {
				
				 // Get first employee from result list
	            // Note: Assumes eId is unique (should be true for primary key)
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
