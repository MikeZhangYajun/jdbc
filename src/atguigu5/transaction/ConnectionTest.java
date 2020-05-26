package atguigu5.transaction;

import java.sql.Connection;

import org.junit.Test;

import atguigu5.util.JDBCUtils;

public class ConnectionTest {
	
	@Test
	public void testGetConnection() throws Exception{
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
