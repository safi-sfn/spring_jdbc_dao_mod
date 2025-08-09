package jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ClobInsert {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		FileReader fr = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB","root","MySQL@2025!");
		    pst = con.prepareStatement("insert into docs values(?,?)");
		    pst.setInt(1, 103);  // this is for first parameter
		    
		   File file = new File("/home/safi/DOCS/SQL.pdf");
		   fr = new FileReader(file);
		    pst.setCharacterStream(2, fr, file.length());
		    
		    int rowCount = pst.executeUpdate();
		    if(rowCount == 1) {
		    	System.out.println("Document Inserted successfully");
		    }
		    else {
		    	System.out.println("Document Inserted Failure");
		    }
		    
		} catch (Exception e) {
			System.out.println("Document Inserted Failure");
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				pst.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
