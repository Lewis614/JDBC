package com.jdbc;


import java.sql.*;
import java.util.*;

public class TestPreparedStmt {

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
		//是Statement的子接口。
		PreparedStatement pst = null;

		Scanner scan = new Scanner(System.in);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");
			//相应变化:?是占位符，等待确定的三个参数。
			pst = conn.prepareStatement("Insert into sailors values (?, ?, 3, 10, ?);");
			pst.setInt(1, sid);
			pst.setString(2, sname);
			pst.setString(3, nation);
			pst.executeUpdate();

		} catch (ClassNotFoundException fe) {
			fe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(pst != null) {
					pst.close();
					pst = null;
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

