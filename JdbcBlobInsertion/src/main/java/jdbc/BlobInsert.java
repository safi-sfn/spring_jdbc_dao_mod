package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BlobInsert {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		FileInputStream fis = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB","root","MySQL@2025!");
		    pst = con.prepareStatement("insert into image values(?,?)");
		    pst.setInt(1, 101);  // this is for first parameter
		    
		   File file = new File("/home/safi/Pictures/Screenshots/codes.png");
		   fis = new FileInputStream(file);
		    pst.setBinaryStream(2, fis, (long)file.length());
		    
		    int rowCount = pst.executeUpdate();
		    if(rowCount == 1) {
		    	System.out.println("Data insertion successfull");
		    }
		    else {
		    	System.out.println("Data insertion failure");
		    }
		    
		} catch (Exception e) {
			System.out.println("Data insertion failure");
			e.printStackTrace();
		}finally {
			try {
				fis.close();
				pst.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
