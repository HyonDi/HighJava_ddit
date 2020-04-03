package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_jdbcTest {

/*	문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오.
	


	문제2) lprod_id값을 2개 입력받아 두 값중 작은 값부터 큰 값 사이의 자료를 출력하시오.
*/
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
			Scanner scan = new Scanner(System.in);
			System.out.println("lprod_id 를 입력하세요. > ");
			int lprodId = scan.nextInt();
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			// rs.next() => ResultSet객체의 데이터를 가리키는 포인터를
			//				다음 레코드로 이동시키고 그곳에 자료가 있으면
			//				true, 없으면 false 를 반환한다.
			
			while(rs.next()) {//rs = resultSet
				// 컬럼의 자료를 가져오는방법
				// 방법1) rs.get자료형이름("컬럼명")
				// 방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1번부터 시작.
				if(rs.getInt("lprod_id") > lprodId) { //1번문제
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("----------------------------------------------------");
				}
			}
			System.out.println("출력 끝...");
			//------------------------------------------------------------------------
			
			System.out.println("시작 lprod_id 입력 > ");
			int startId = scan.nextInt();
			System.out.println("끝 lprod_id 입력 > ");
			int lastId = scan.nextInt();
			
			while(rs.next()) {
				if(rs.getInt("lprod_id") > startId && rs.getInt("lprod_id") < lastId) { //2번문제
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("----------------------------------------------------");
				}
			}
			System.out.println("출력 끝...");
			
			
			//--------------------------------------------------------------------------
			
			
			
		
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
