package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) properties객체 이용하기
 * 설정파일정보를 외부프로퍼티스객체로 뽑아서 저장했대. 내일은 resourcebundel. MVC패턴공부해오기. 
 * @author PC-16
 */
public class DBUtil2 {
	static Properties prop; //Properties객체변수 선언
	
	static {
			//읽어온 정보를 저장할 Properties 객체 생성.
				Properties prop = new Properties();
				
				// 읽어올 파일명을 이용한 File객체 생성
				File file = new File("res/db.properties");
				
				try {
					//파일 읽기를 수행할 fileInputStream객체 생성
					FileInputStream fin = new FileInputStream(file);
					
					//Properties객체로 파일 내용 읽기
					prop.load(fin); // 파일 내용을 읽어와 key와 value 값으로 분류한 후 Properties객체에 담아준다.
					
					Class.forName(prop.getProperty("driver"));
					
				} catch(IOException e) {
					System.out.println("파일이 없거나 입출력 오류입니다.");
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패!!!");
				}
				
	}
	
	public static Connection getConnection() { //메모리에 바로 올리기위해 static으로 씀.
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("pass"));
		} catch(SQLException e) {
			System.out.println("DB연결 실패!!!");
			e.printStackTrace();
			return null;
		}
	}
	
}
