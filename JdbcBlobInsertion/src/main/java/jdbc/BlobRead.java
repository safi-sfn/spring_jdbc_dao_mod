package jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlobRead {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB","root","MySQL@2025!");
		    pst = con.prepareStatement("select * from image where ID= ? ");
		    pst.setInt(1, 103);
		    rs = pst.executeQuery();
		    rs.next();
		    System.out.println("Image ID : "+rs.getInt("ID"));
		    fos = new FileOutputStream("/home/safi/Pictures/Screenshots/blobRead.jpg");
		    is = rs.getBinaryStream(2);
		    
		    int val = is.read();
		    while(val != -1) {
		    	fos.write(val);
		    	val = is.read();
		    }
		    System.out.println("Image data is Retrieved from DB to /home/safi/Pictures/Screenshots/blobRead.jpg");
		    
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
				rs.close();
				fos.close();
				pst.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
