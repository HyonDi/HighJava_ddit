package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 클래스
 * 반복되는 부분을 미리 만들어놓을 클래스. (DB연결부분(로딩, 커넥션)
 */
public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
		}
	}
	
	public static Connection getConnection() { //메모리에 바로 올리기위해 static으로 씀.
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe", 
					"khj", 
					"java");
			// "jdbc:oracle:thin: @호스트ip : 포트 / sid",
			// "접속이름","비밀번호" 
		} catch(SQLException e) {
			System.out.println("DB연결 실패!!!");
			e.printStackTrace();
			return null;
		}	// 호스트가 닫혀있거나 비밀번호가 틀렸거나 등의 이유로 예외처리를 준비한다.
	}
}
