package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Statement;

public class JdbcApp {

	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB","root","MySQL@2025!");
	/*	
	 * Statement st = con.createStatement();
		st.addBatch("insert into Employee values(103,'HYD','CCC',1000)");
		st.addBatch("update Employee set eSallary = eSallary + 500 where eSallary<50000");
		st.addBatch("delete from Employee where eId=101");
		int[] rowCount = st.executeBatch();
		for(int i=0; i<rowCount.length; i++) {
			System.out.println("row count "+ rowCount[i]);
		}
		st.close();
	*/
		
		PreparedStatement pst = con.prepareStatement("insert into Employee values(?,?,?,?)");
		pst.setInt(1,106);
		pst.setString(2,"HYD");
		pst.setString(3,"FFF");
		pst.setFloat(4,1500);
		pst.addBatch();
		
		pst.setInt(1,107);
		pst.setString(2,"HYD");
		pst.setString(3,"GGG");
		pst.setFloat(4,1500);
		pst.addBatch();
		
		pst.setInt(1,108);
		pst.setString(2,"HYD");
		pst.setString(3,"HHH");
		pst.setFloat(4,1500);
		pst.addBatch();
		
		
		int[] rowCount = pst.executeBatch();
		for(int rowCounts : rowCount) {
			System.out.println("row count : "+ rowCounts);
		}
		
		pst.close();
		con.close();

	}

}
