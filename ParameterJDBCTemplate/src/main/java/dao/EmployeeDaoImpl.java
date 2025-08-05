package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import dto.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	// NamedParameterJdbcTemplate is injected via Spring DI
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		/*
		 * Template Methods 
		 * query(): For SELECT operations 
		 * update(): For INSERT/UPDATE/DELETE
		 * 
		 * example : namedParameterJdbcTemplate.query(sql_query,params,RowMapper);
		 *          namedParameterJdbcTemplate.update(sql_query,params);
		 */
	}

	@Override

	public String add(Employee emp) {
		String status = "";
		String sqlQuery = "";
		try {

			 // Named parameter query with :eid placeholder
			sqlQuery = "SELECT * FROM Employee WHERE eId = :eid";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("eid", emp.getEid());   // Binding value to named parameter
			
			 // Executing query with named parameters . syntax- npjt.query(sqlQuery,params,RowMapper)
			List<Employee> empList = namedParameterJdbcTemplate.query(sqlQuery, params, 
					(rs, index) -> { 
						/* ... row mapper logic ... */ 
						Employee emp1 = new Employee();
						emp.setEid(rs.getInt("eId"));
						emp.setEname(rs.getString("eName"));
						emp.setEsallary(rs.getFloat("eSallary"));
						emp.setEcity(rs.getString("eCity"));
						return emp;
					});
			if (empList.isEmpty() == true) {
				   // INSERT with named parameters (:eid, :ecity, etc.)
				sqlQuery = "INSERT INTO Employee VALUES(:eid, :ecity, :ename, :esallary)";
				params = new HashMap<String, Object>();
				
				//Binding values to Map object
				params.put("eid", emp.getEid());
				params.put("ecity", emp.getEcity());
				params.put("ename", emp.getEname());
				params.put("esallary", emp.getEsallary());
				
				// Update with named parameters
				int rowCount = namedParameterJdbcTemplate.update(sqlQuery, params);
				/* ... status handling ... */
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
			 // Named parameter query for search
			String query = "select * from Employee where eId = :eid";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("eid", eid);  // Binding Parameter : Values are added to a Map<String, Object>
			
			// Query execution with named parameters
			List<Employee> empList = namedParameterJdbcTemplate.query(query, params, (rs, index) -> {
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

			// UPDATE with named parameters
			String query = "update Employee set eName = :ename, eSallary = :esallary, eCity = :ecity where eid = :eid";
			Map<String, Object> params = new HashMap<String, Object>();
			// Binding multiple parameters
			params.put("ename", emp.getEname());
			params.put("esallary", emp.getEsallary());
			params.put("ecity", emp.getEcity());
			params.put("eid", emp.getEid());

			   // Executing update
			int rowCount = namedParameterJdbcTemplate.update(query, params);
			if (rowCount == 1) {
				status = "employee updated successfully";
			} else {
				status = "employee updation failure....";
			}

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
				// DELETE with named parameter
				String query = "delete from Employee where eId = :eid";
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("eid", eid);  // Binding parameter
				
				// Executing delete
				int rowCount = namedParameterJdbcTemplate.update(query, params);
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


/*Named Parameter Concepts Highlighted
 * 
 * Placeholder Syntax (:paramName) 
 *    - Used in SQL queries instead of ?
 *    - Example: WHERE eId = :eid
 * 
 * Parameter Binding
 *    - Values are added to a Map<String, Object>
 *    - Key matches the placeholder name (params.put("eid", value))
 *    
 * Template Methods
 *    - query(): For SELECT operations
 *    - update(): For INSERT/UPDATE/DELETE
 *    
 * Advantages Over Plain JDBC
 *    - No positional parameter confusion
 *    - Better readability with named parameters
 *    - Automatic SQL injection prevention
 * 
 * 
 * 
 */

