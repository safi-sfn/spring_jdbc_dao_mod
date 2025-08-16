package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import dto.EmployeeDTO;

public class EmployeeImplDAO implements IEmployeeDAO {

	// JDBC Template for database operations
	private JdbcTemplate jdbcTemplate;
	
	// Handler for Large OBject (LOB) database operations (BLOB/CLOB)
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

	
	//Inserts an employee record with LOB (Large Object) data into the database
	@Override
	public void insertEmployee(EmployeeDTO emp) {
		
		 // SQL query with placeholders for employee data and LOBs
		String query = "insert into emp_doc values(?,?,?,?)";
		
		 // Execute query with LOB handling callback
		jdbcTemplate.execute(query, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {

			 /**
	         * Sets values for the prepared statement including LOB handling
	         * @param ps PreparedStatement to set values on
	         * @param lobCreator Helper for LOB operations
	         */
			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator)
					throws SQLException, DataAccessException {
				try {
					ps.setInt(1,emp.getEno());   // Employee number
					ps.setString(2, emp.getEname());  // Employee name
					
					// Prepare file streams for LOBs
					// Binary stream for image
					FileInputStream fis = new FileInputStream(emp.getEmp_image());
					
					// Character stream for resume
					FileReader fr = new FileReader(emp.getEmp_resume());
					
					 // Set BLOB (image) parameter
					lobCreator.setBlobAsBinaryStream(ps, 3, fis, (int)emp.getEmp_image().length());
					
					// Set CLOB (resume) parameter
					lobCreator.setClobAsCharacterStream(ps, 4, fr, (int)emp.getEmp_resume().length());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	@Override
	public EmployeeDTO readEmployee(int eno) {
		EmployeeDTO emp = new EmployeeDTO();
		String query = "select * from emp_doc where eno = "+eno;
		jdbcTemplate.query(query, new AbstractLobStreamingResultSetExtractor<Object>() {

			@Override
			protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
				emp.setEno(rs.getInt("ENO"));
				emp.setEname(rs.getString("ENAME"));
				File f1 = new File("/home/safi/Pictures/my_image.jpg");
				FileOutputStream fos = new FileOutputStream(f1);
				FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, 3),fos);
				emp.setEmp_image(f1);
				
				File file2 = new File("/home/safi/Pictures/fakeResume.pdf");
				FileWriter fw = new FileWriter(file2);
				FileCopyUtils.copy(lobHandler.getClobAsCharacterStream(rs, 4), fw);
				emp.setEmp_resume(file2);
				
			}
		});
		return emp;
	}
	
	
	
	
}
