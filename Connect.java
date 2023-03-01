package game;

import java.sql.*;


public class Connect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con;
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
		Statement myStmt = con.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from highscore");
		while (myRs.next()) {
			System.out.println(myRs.getString("hname") + ", " + myRs.getString("score"));
		}
	}
	

}
