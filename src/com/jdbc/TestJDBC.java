package com.jdbc;

import java.sql.*;

public class TestJDBC {

	public static void main(String[] args) throws Exception{
		//new一个对象的另一种方法：Class类装载器-->把堆内存中分配Driver对象空间，把指定类的实例new出来。
		//new一个Driver类的对象，new实例过程中，Driver自动向大管家注册。注册后可以通过大管家拿到一个数据库的连接

		//forName会抛出一个异常。查API---ClassNotFoundException,不能随便给个名字就new个类。
		Class.forName("com.mysql.jdbc.Driver");
		//法二		Class.forName("com.mysql.jdbc.Driver").newInstance();
		//法三	    new com.mysql.jdbc.Driver();

		//getConnection 三参数的是最常用的，参数一是数据库的连接字符串，不同数据库不同。参数2是用户名，参数三是密码
		//			Connection conn = DriverManager.getConnection(url, user, password);
		//例子		conn = DriverManager.getConnection("[url]jdbc:mysql://hostname:port/dbname","username", "password");
		Connection conn = null; 
		conn =	DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");


		/**写法相当固定：
		 * 1.相关数据库的jar包放在classPath里面，add external archive
		 * 2.引入相关包，new一个Driver类的实例出来，它在new的过程中会自动的向DriverManager注册。 
		 * 3.下一步是通过DriverManager拿到数据库的连接。
		 */


		//第三步：通过conn连接创建一个Statement
		Statement statement = conn.createStatement();
		//执行sql的select语句要用executeQuery方法。insert语句要用executeUpdate().是不同的
		//API:ResultSet executeQuery(String sql) throws SQLException,注意返回值。
		//第四步：执行一步sql语句，结果存入ResultSet中：
		ResultSet rs  = statement.executeQuery("select * from sailors;");

		//返回的结果集就像一个游标。rs是游标指针，指向第一条记录的前面，像J2SE的Iterator类。
		//循环的取出每个元素，游标向下移动就取出一个。boolean next();----非常固定用While();
		//第五步：结果集遍历最常用的方法：
		while(rs.next()) {
			System.out.println(rs.getString("sid")); // 把sid字段里面的内容当成字符串类型取出。
			//				System.out.println(rs.getDouble("age"));
		}


		//第六步：最后要关闭资源，后打开的先关。
		rs.close();
		statement.close();
		conn.close();
	}
}

class TestJDBCTry {

	public static void main(String[] args) {

		Connection conn = null; 
		Statement statement=null;
		ResultSet rs=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		
			conn =	DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Aiesec2010");
			statement = conn.createStatement();
			rs  = statement.executeQuery("select * from sailors;");
			while(rs.next()) {
				System.out.println(rs.getString("sid")); 
			}
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			//这里可以使用log4J的开源日志软件进行exception记录。
			se.printStackTrace(); 
		}
		//之前说的那种try可能执行的理解不够透彻啊，直接理解成大括号里面的局部变量即可。单解法还是一致的，就是声明时给null值。
		finally{
			//finally这里也成了模式了，finally的close也抛错，所以就必须再次try-catch
			try{
				//更精细的限制：良好的习惯有莫大的好处！！！！--考你写程序是不是严谨。
				if(rs != null) {
					rs.close();
					//垃圾收集器自动收集无用程序
					rs=null;
				}	
				if(statement !=null ) {
					statement.close();
					statement = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
				
			} catch (SQLException se) {
				se.getStackTrace();
			}
 
		}
	}
}
