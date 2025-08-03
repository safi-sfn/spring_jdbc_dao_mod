package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.StudentDao;
import dto.Student;
import factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {
	
	private static final String SQL_QUERY = null;
	
	public enum sql_query {
		SELECT_QUERY("SELECT * FROM students WHERE sId=?"),
		INSERT_QUERY("INSERT INTO students VALUES(?,?,?)"),
		DELETE_QUERY("DELETE FROM students WHERE sId=?");
/*
 * When the enum loads : SELECT_QUERY gets "SELECT * FROM students WHERE sid=?" stored in its query field. 
 * INSERT_QUERY gets "INSERT INTO students VALUES(?,?,?)" stored in its query field.
 */
		private final String query;
		private sql_query(String query) {
			this.query = query;
		}

		public String getQuery() {
			// Returns the query string for the specific enum constant
			return query;
		}
	}

	String status = "";
	
	@Override
	public String add(Student std) {
		try {
			// Step 1: Get a database connection from the connection factory
			Connection connection = ConnectionFactory.getConnection();

			// Step 2: Check if student already exists
			// Prepare SQL query to search for student by ID
			PreparedStatement prst = connection.prepareStatement(sql_query.SELECT_QUERY.getQuery());
			prst.setString(1, std.getSid());     // Set student ID parameter
			ResultSet rs = prst.executeQuery();  // Execute the query and get results
			
			// Step 3: Check if student exists
			boolean b = rs.next();
			if (b == true) { // If result has data, student exists
				status = "existed";
			} else {
				// Step 4: Student doesn't exist - prepare INSERT statement
				prst = connection.prepareStatement(sql_query.INSERT_QUERY.getQuery());

				// Set parameters for the INSERT query from Student object
				prst.setString(1, std.getSid());   // Student Id
				prst.setString(2, std.getSname()); // Student Name
				prst.setString(3, std.getSaddr()); // Student Address

				prst.executeUpdate(); // it will return int value 0 or 1
				status = "success";
			}
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student std = null;
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement prst = connection.prepareStatement(sql_query.SELECT_QUERY.getQuery());
			
			prst.setString(1, sid);
			ResultSet rs = prst.executeQuery();
			boolean b = rs.next();
			if(b == true) {
			std = new Student();
			std.setSid(rs.getString("sId"));
			std.setSname(rs.getString("sName"));
			std.setSaddr(rs.getString("sAddr"));
			}else {
			std = null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return std;
	}

	@Override
	public String delete(String sid) {
		String status = "";
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement prst = connection.prepareStatement(sql_query.SELECT_QUERY.getQuery());
			prst.setString(1, sid);
			ResultSet rs = prst.executeQuery();
			boolean b = rs.next();
			if(b == true) {
				prst = connection.prepareStatement(sql_query.DELETE_QUERY.getQuery());
				prst.setString(1, sid);
				int rowCount = prst.executeUpdate();
				if(rowCount == 1) {
					status = "success";
				}else {
					status = "failure";
				}
			}else {
				status = "notexisted";
			}
		} catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}

}
