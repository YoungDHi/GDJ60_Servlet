/**
 * 
 */
package com.naver.home.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author GDJ60
 *
 */
public class DBConnection {
	
	//getConnection
	public static Connection getConnection() throws Exception {
		
		//1. 연결 정보
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String diver = "oracle.jdbc.driver.OracleDriver";
		
		//2. Driver를 메모리에 로딩
		Class.forName(diver);
		
		//3. DB 연결
		return DriverManager.getConnection(url, user, password);
		
	}
	
	public static void disconnect(PreparedStatement st, Connection connect) throws Exception {
		
		st.close();
		connect.close();
	}
	
	public static void disconnect(ResultSet rs, PreparedStatement st, Connection connect) throws Exception {
		
		rs.close();
		st.close();
		connect.close();
	}
	
	
}
