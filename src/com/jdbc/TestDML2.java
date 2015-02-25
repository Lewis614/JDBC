package com.jdbc;


import java.sql.*;
import java.util.*;

public class TestDML2 {

	public static void main(String[] args) {

		int sid =0;
		
		if(args.length != 3) {
			System.out.println("Incorrect input parameters number.");
			//别忘了系统退出。
			System.exit(-1);
		}
		try {
			sid = Integer.parseInt(args[0]);		
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage()+"--> Parameter Error, input a Int");
			System.exit(-1);
		} 
		
		String sname= args[1];
		String nation= args[2];
		
		Connection conn = null;
		Statement st = null;

		Scanner scan = new Scanner(System.in);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");
			st = conn.createStatement();
			//注意sql语句格式：单引号
			//INSERT INTO Persons VALUES ('Gates', 'Bill', 'Xuanwumen 10', 'Beijing')
			
			
			String sqlString = "Insert into sailors values ("+sid+", '"+sname+"', 22, 1, '"+nation+"')";
			System.out.println(sqlString);
			st.executeUpdate(sqlString);

		} catch (ClassNotFoundException fe) {
			fe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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