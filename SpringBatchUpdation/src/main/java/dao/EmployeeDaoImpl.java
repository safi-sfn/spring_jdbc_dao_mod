package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import dto.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public int[] insert(List<Employee> empList) {		
		int[] rowCounts = null;
		try {
			// SQL query with parameter placeholders for batch insert
			String query = "insert into Employee values(?,?,?,?)";
			
			   // Execute batch update using Spring's JdbcTemplate
			rowCounts = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					
					// Get the employee at current batch position
					Employee emp = empList.get(i);
					 // Set parameter values for the prepared statement:
					ps.setInt(1, emp.getEid());
					ps.setString(2, emp.getEcity());
					ps.setString(3, emp.getEname());
					ps.setFloat(4, emp.getEsallary());
				}
				@Override
				public int getBatchSize() {
					 // Return total number of employees to insert
					return empList.size();
				}
			});
		} catch (Exception e) {
			 e.printStackTrace(); 
		     return new int[0]; 
		}
		return rowCounts;
	}

	


}




