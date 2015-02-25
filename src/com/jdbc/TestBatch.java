package com.jdbc;

import java.sql.*;
public class TestBatch {


	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");

		Statement stmt = conn.createStatement();
		stmt.addBatch("insert into dept2 values (51, '500', 'haha')");
		stmt.addBatch("insert into dept2 values (52, '500', 'haha')");
		stmt.addBatch("insert into dept2 values (53, '500', 'haha')");
		//无需创建三个stataments，直接加入到一个batch里面一并执行了
		stmt.executeBatch();
		stmt.close();

		/*PreparedStatement中的批处理也是可以的
		PreparedStatement ps = conn.prepareStatement("insert into dept2 values (?, ?, ?)");
		ps.setInt(1, 61);
		ps.setString(2, "haha");
		ps.setString(3, "bj");
		ps.addBatch();

		ps.setInt(1, 62);
		ps.setString(2, "haha");
		ps.setString(3, "bj");
		ps.addBatch();

		ps.setInt(1, 63);
		ps.setString(2, "haha");
		ps.setString(3, "bj");
		ps.addBatch();

		ps.executeBatch();
		ps.close();

		conn.close();
		 */

	}

}
