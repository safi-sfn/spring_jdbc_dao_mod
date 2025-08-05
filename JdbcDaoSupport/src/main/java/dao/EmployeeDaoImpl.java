package dao;


import java.util.List;


import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dto.Employee;

public class EmployeeDaoImpl extends JdbcDaoSupport implements IEmployeeDao {

	@Override
	public String add(Employee emp) {

		String status = "";
		try {

			Employee emp1 = search(emp.getEid());
			if (emp1 == null) {

				int rowCount = getJdbcTemplate().update("INSERT INTO Employee VALUES(?,?,?,?)",
						new Object[] { emp.getEid(), emp.getEcity(), emp.getEname(), emp.getEsal() });
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
			String query = "select * from Employee where eId = ?";
			// Replaced deprecated Object[] with PreparedStatementSetter. (Lambda) //, ps ->
			// ps.setInt(1, eid)
			List<Employee> empList = getJdbcTemplate().query(query, new Object[] { eid }, (rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEid(rs.getInt("eId"));
				emp1.setEname(rs.getString("eName"));
				emp1.setEsal(rs.getFloat("eSallary"));
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
	public List<Employee> getAllEmployees() {
		List<Employee> empsList = null;
		try {
			empsList = getJdbcTemplate().query("select * from Employee", (rs, index) -> {
				Employee emp = new Employee();
				emp.setEid(rs.getInt("eId"));
				emp.setEname(rs.getString("eName"));
				emp.setEsal(rs.getFloat("eSallary"));
				emp.setEcity(rs.getString("eCity"));
				return emp;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empsList;
	}

	@Override
	public String update(Employee emp) {
		String status = "";
		try {

			int rowCount = getJdbcTemplate().update("update Employee set eName=?,eSallary=?,eCity=? where eId=?",
					new Object[] { emp.getEname(), emp.getEsal(), emp.getEcity(), emp.getEid() });
			if (rowCount == 1) {
				status = "employee updated successfully";
			} else {
				status = "employee updation failure....";
			}
		} catch (Exception e) {
			status = "employee updation failure----";
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

				String query = "delete from Employee where eId = ?";

				// Executing delete
				int rowCount = getJdbcTemplate().update(query, new Object[] { eid });
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
