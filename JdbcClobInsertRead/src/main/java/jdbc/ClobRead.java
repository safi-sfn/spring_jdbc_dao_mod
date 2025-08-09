package jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClobRead {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		FileWriter fw = null;
		Reader r = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB","root","MySQL@2025!");
		    pst = con.prepareStatement("select * from docs where ID= ? ");
		    pst.setInt(1, 101);
		    rs = pst.executeQuery();
		    rs.next();
		    System.out.println("Docs ID : "+rs.getInt("ID"));
		    r = rs.getCharacterStream (2);
		    fw = new FileWriter("/home/safi/Pictures/Screenshots/docXml.xml");
		    
		    int val = r.read();
		    while(val != -1) {
		    	fw.write(val);
		    	val = r.read();
		    }
		    System.out.println("Image data is Retrieved from DB to /home/safi/Pictures/Screenshots/docXml.pdf");
		    
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				r.close();
				rs.close();
				fw.close();
				pst.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
