package com.jdbc;

import java.sql.*;
public class TestTransaction {


	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:SXT", "scott", "tiger");
			//设置自动提交失效，
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.addBatch("insert into dept2 values (51, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (52, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (53, '500', 'haha')");
			stmt.executeBatch();
			//全部插入更新信息后，手动统一提交
			conn.commit();
			//恢复现场
			conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			
			e.printStackTrace();
			
			try {
				if(conn != null)
				{
				
					//注意有任何SQLException，一定要rollback了。之后再把现场还原。
					conn.rollback();
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}

