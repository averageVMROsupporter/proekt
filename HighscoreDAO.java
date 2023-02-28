package game;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class HighscoreDAO {
 private Connection con;
 	public HighscoreDAO() throws Exception {
 		Properties props=new Properties();
 		props.load(new FileInputStream("demo.properties"));
 		
 		String hid =props.getProperty("hid");
 		String hname =props.getProperty("hname");
 		String score =props.getProperty("score");
 		
 		con =DriverManager.getConnection (hid, hname, score);
 		
 	public List<Highscore> getHighscore() throws Exception {
 		List<Highscore> list = new ArrayList<>(); 	
 		
 		Statement myStmt = null;
 		ResultSet myRs = null;
 		}
 	}
}
