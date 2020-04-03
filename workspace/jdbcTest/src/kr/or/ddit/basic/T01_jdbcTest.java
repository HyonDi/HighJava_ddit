package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*<jdbc설정하기>

새 자바프로젝트 만들고 프로젝트우클릭 > 프로퍼티스 > 자바 빌드패스 > 
add External jars 클릭> 라이브러리누르고>
c > 아이바티스> ojdbc6.jar >apply클릭 : referenced Libraries에 ojdbc6.jar(우유병모양) 가 추가되었다.

끝!

새 클래스만들고 단계별 테스트를 해봅니다.
*/
/*
JDBC를 이용한 데이터베이스 처리 순서

순서 : JDBC드라이버 로딩 -> 해당 DB에 접속 -> 질의(SQL명령을 수행한다.) -> 질의 결과를 받아서 처리한다. -> 종료(자원반납)

1. JDBC드라이버 로딩(오라클기준)
 	JDBC드라이버는 DB를 만든 회사에서 제공한다.
 	Class.forName("oracle.jdbc.driver.OracleDriver");

2. 접속하기 : 접속이 성공하면 Connection 객체가 생성된다.
	DriverManager.getConnection()메서드를 이용한다.
	
3. 질의 : Statement객체 또는 preparedStatement객체를 이용하여  SQL문장을 실행한다.

4. 결과 :
	1) SQL문이 select일 경우 => ResultSet객체가 만들어진다.
		ResultSet객체에는 select한 결과가 저장된다.
	2) SQL문이 insert, update, delete일 경우 => 정수값을 반환한다.
		(정수값은 보통 실행에 성공한 레코드 수를 말한다.)
*/	

public class T01_jdbcTest {
	public static void main(String[] args) {
		//DB작업에 필요한 객체변수 선언
		Connection conn = null; //커넥션객체를 받는다.
		Statement stmt = null; //CRUD작업의 객체를 받는다.
		ResultSet rs = null; //쿼리문이 select일 경우에 필요함. 결과값을 받는다.
		
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB에 접속(Connection객체 생성)
			String url = "jdbc:oracle:thin:@192.168.205.8:1521/xe"; //ip부분에 실행할 계정의 localhost 적으면된다.[ip:포트/sid]
			String userId = "khj"; //sql계정이름
			String password = "java"; //계정비밀번호
			
			//실제적으로 OracleDriver가 사용되는 부분.
			conn = DriverManager.getConnection(url,userId,password);
			
			// 3. Statement객체 생성 => Connection객체를 이용한다.
			stmt = conn.createStatement();
			
			// 4. SQL문을 Satement객체를 이용하여 실행하고
			//		실행결과를 ResultSet객체에 저장한다.

			String sql = "select * from lprod"; //실행할 SQL문
			
			// SQL문이 select일 경우에는 executeQuery()메서드를 사용하고
			// insert, update, delete일 경우에는 executeUpdate()메서드 사용함.
			rs = stmt.executeQuery(sql);
			//executeQuerysms resultSet 을 리턴. executeUpdate는 int 를 리턴.
			//구분해서 사용되어야한다. 여기서는 CRUD만 할 수 있음.
			// alter, drop 같은건 DBA가 하는거래!
			
			
			// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next() 메서드를 
			//	이용하여 차례로 읽어와 처리한다.
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			// rs.next() => ResultSet객체의 데이터를 가리키는 포인터를
			//				다음 레코드로 이동시키고 그곳에 자료가 있으면
			//				true, 없으면 false 를 반환한다.
			while(rs.next()) {//rs = resultSet
				// 컬럼의 자료를 가져오는방법
				// 방법1) rs.get자료형이름("컬럼명")
				// 방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1번부터 시작.
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("----------------------------------------------------");
			}
			System.out.println("출력 끝...");
			
			
		
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally { //연결끊는 부분!
				//6. 종료 (사용했던 자원을 모두 반납한다.)
				if(rs != null) try {rs.close();} catch (SQLException e2) {}
				if(stmt != null) try {stmt.close();} catch (SQLException e2) {}
				if(conn != null) try {conn.close();} catch (SQLException e2) {}
			}
		//자원반납 안할시 메모리가 부족할때는 치명적일 수 있습니다. 메인서버가 꺼진다던가.
		
	}	
}
