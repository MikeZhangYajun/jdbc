package atguigu.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Driver;

public class ConnectionTest {
	//方式一
	@Test
	public void testConnection1() throws SQLException {
		Driver driver = new com.mysql.jdbc.Driver();
		String url = "jdbc:mysql://localhost:3306/vrg";
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "32456Mysql");
		Connection connect = driver.connect(url, info);
		System.out.println(connect);
	}
	//对方式一的迭代：在如下的程序中不出现第三方的api，使得程序具有更好的可移植性
	@Test
	public void testConnection2() throws Exception {
		Class clazz = Class.forName("com.mysql.jdbc.Driver");
		Driver driver = (Driver) clazz.newInstance();
		String url = "jdbc:mysql://localhost:3306/vrg";
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "32456Mysql");
		Connection connect = driver.connect(url, info);
		System.out.println(connect);
	}
	//方式三：使用DriverManager替换Driver
	@Test
	public void testConnection3() throws Exception {
		Class clazz = Class.forName("com.mysql.jdbc.Driver");
		Driver driver = (Driver) clazz.newInstance();
		String url = "jdbc:mysql://localhost:3306/vrg";
		String user = "root";
		String password = "32456Mysql";
		
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}
	
	//方式四： 可以只是加载驱动，不用显示地注册驱动
	@Test
	public void testConnection4() throws Exception {
		String url = "jdbc:mysql://localhost:3306/vrg";
		String user = "root";
		String password = "32456Mysql";
		
		Class.forName("com.mysql.jdbc.Driver");
		//省略注册环节，因为在mysql的Driver实现类中，声明了一个静待代码块
//		Driver driver = (Driver) clazz.newInstance();		
//		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}
	//方式五(final版)：将数据库连接需要的四个基本信息声明在配置文件中，通过读取配置文件的方式，获取链接
	/*
	 * 1.实现了数据与代码的分离，实现了解耦
	 * 2.如果需要修改配置文件信息，可以避免程序重新打包
	 */
	@Test
	public void testConnection5() throws Exception {
		InputStream inputStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros = new Properties();
		pros.load(inputStream);
		String driverClass = pros.getProperty("driverClass");
		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		Class.forName(driverClass);
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
