package dao;

import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import dto.EmployeeDTO;

public class EmployeeImplDAO implements IEmployeeDAO {

	private JdbcTemplate jdbcTemplate;
	private LobHandler lobHandler;
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public LobHandler getLobHandler() {
		return lobHandler;
	}

	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}

	@Override
	public void insertEmployee(EmployeeDTO emp) {
		String query = "insert into emp_doc values(?,?,?,?)";
		jdbcTemplate.execute(query, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {

			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				try {
					ps.setInt(1,emp.getEno());
					ps.setString(2, emp.getEname());
					FileInputStream fis = new FileInputStream(emp.getEmp_image());
					FileReader fr = new FileReader(emp.getEmp_resume());
					lobCreator.setBlobAsBinaryStream(ps, 3, fis, (int)emp.getEmp_image().length());
					lobCreator.setClobAsCharacterStream(ps, 4, fr, (int)emp.getEmp_resume().length());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	@Override
	public EmployeeDTO readEmployee() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
