package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dto.Employee;

/**
 * Custom RowMapper implementation to convert database rows into Employee objects.
 * Used by Spring's JdbcTemplate for result set processing.
 */

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		 // Create new Employee instance
		Employee emp = new Employee();
		
		 // Map database columns to Employee properties
		    emp.setEid(rs.getInt("eId"));         // Set employee ID from eId column
	        emp.setEname(rs.getString("eName"));   // Set name from eName column
	        emp.setEsallary(rs.getFloat("eSallary")); // Set salary from eSallary column
	        emp.setEcity(rs.getString("eCity"));    // Set city from eCity column
	        
	     // Return the fully constructed Employee
		return emp;
	}

}
