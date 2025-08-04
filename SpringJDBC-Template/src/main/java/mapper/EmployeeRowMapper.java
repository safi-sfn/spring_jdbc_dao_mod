package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dto.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee emp = new Employee();
		emp.setEid(rs.getInt("eId"));
		emp.setEname(rs.getString("eName"));
		emp.setEsallary(rs.getFloat("eSallary"));
		emp.setEcity(rs.getString("eCity"));
		return emp;
	}

}
