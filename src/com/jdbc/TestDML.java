package com.jdbc;

//有关数据库连接的，一定要找到sql这个包，不要去了特定数据库的包里，那样的化就不能跨平台了。
import java.sql.*;

public class TestDML {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");
			st = conn.createStatement();
			//注意sql语句格式：单引号
			//INSERT INTO Persons VALUES ('Gates', 'Bill', 'Xuanwumen 10', 'Beijing')
			String sqlString = "Insert into sailors values (8, 'Livia', 22, 1, 'CHINA')";
			st.executeUpdate(sqlString);
			
		} catch (ClassNotFoundException fe) {
			fe.getStackTrace();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
					st = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
		
	}
}


class TestBackup {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");
			st = conn.createStatement();
			rs = st.executeQuery("Select * from sailors");
			
			while(rs.next()) {
				System.out.println(rs.getString("sname"));
			}
		} catch (ClassNotFoundException fe) {
			fe.getStackTrace();
		} catch (SQLException se) {
			se.getStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(st != null) {
					st.close();
					st = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
				
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
		
		
	}
}




