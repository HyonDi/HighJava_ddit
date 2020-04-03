package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Insert 예제
 * @author PC-16
 *
 */
public class T03_JdbcTest {
/*	lprod_id : 101, lprod_gu : N101, lprod_nm : 농산물
	lprod_id : 102, lprod_gu : N102, lprod_nm : 수산물
	lprod_id : 103, lprod_gu : N103, lprod_nm : 축산물

*	위 세개를 insert해서 추가해보자.
*/	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //쿼리문이 select일 경우에 필요함. 결과값을 받는다.
		//
		
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe",
					"khj",
					"java"
					);
			
/*			// Statement객체를 이용한 자료추가 방법
			stmt = conn.createStatement();

			
			String sql = "insert into lprod" + " (lprod_id, lprod_gu, lprod_nm) " + " values(101, 'N101', '농산물')";
			
			//SQL문이 select문이 아닐 경우에는 executeUpdate()메서드를 사용한다.
			//executeUpdate()메서드는 실행에 성공한 레코드 수를 반환한다.
			int cnt = stmt.executeUpdate(sql);
			
			System.out.println("첫 번째 반환 값 : " + cnt);
			//-----------------------------------------------
			sql = "insert into lprod" + " (lprod_id, lprod_gu, lprod_nm) " + " values(102, 'N102', '수산물')";
			cnt = stmt.executeUpdate(sql);
			System.out.println("두 번째 반환 값 : " + cnt);
			//------------------------------------------------------------
			sql = "insert into lprod" + " (lprod_id, lprod_gu, lprod_nm) " + " values(103, 'N103', '축산물')";
			cnt = stmt.executeUpdate(sql);
			System.out.println("세 번째 반환 값 : " + cnt);
			//------------------------------------------------------------
			 * 
			 *
*/			
			//------------------------------------------------------------------------------------------------
			
/*			
			//preparedStatement객체를 이용한 자료 추가 방법
			//SQL문작성(데이터가 들어갈 자리에 물음표(?)를 넣는다.)
			String sql = "insert into lprod" + " (lprod_id, lprod_gu, lprod_nm) " + " values(?, ?, ?)";
			//sql 준비시 데이터에 ? 를 넣어놓는다. 3개면 물픔표 3개. 이걸 이용해서 데이터를 넘길때 세팅을 할 것이다. sql문 자체를 미리준비해놔야한다.
			
			//preparedStatement객체를 생성할 때 SQL문을 넣엇 생성한다. 미리 파라미터로 sql도 받아놔야함.
			pstmt = conn.prepareStatement(sql);
			
			//쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set 자료형이름(물음표순번, 데이터);
			//		물음표 순번은 1번부터 시작한다.
			pstmt.setInt(1, 101); //파라미터 첫번째는 인덱스번호. 물음표 위치. 넣을값이 숫자면 setInt사용, 문자열이면 setString 사용한다.
			pstmt.setString(2, "N101");
			pstmt.setString(3, "농산물");
			
			// 데이터를 세팅한 후 쿼리문을 실행한다. insert구문이니 executeUpdate 실행.
			int cnt = pstmt.executeUpdate();
			System.out.println("첫 번째 반환 값 : " + cnt);
			//-------------------------------------------
			pstmt.setInt(1, 102);
			pstmt.setString(2, "N102");
			pstmt.setString(3, "수산물");
			
			cnt = pstmt.executeUpdate();
			System.out.println("두 번째 반환 값 : " + cnt);
			//-------------------------------------------
			pstmt.setInt(1, 103);
			pstmt.setString(2, "N103");
			pstmt.setString(3, "축산물");
			
			cnt = pstmt.executeUpdate();
			System.out.println("세 번째 반환 값 : " + cnt);
			
			
*/			
			
			//==============================================================================================================================================
			/*SQL Inject 예제*/
			/*pstmt가 보안성이 높다고 하는 이유*/
			
			String lprod_gu=" ";//사용자 입력값
			/*String lprod_gu="' or 1=1 --";  : 전체출력*/
			
			String sql = "select * from lprod " + " where lprod_gu = ? and lprod_nm = '축산물'";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lprod_gu);
			System.out.println("실행할 쿼리 : " + sql);
			
			rs = pstmt.executeQuery(sql);

			System.out.println("=== 쿼리문 실행 결과 ===");

			while(rs.next()) {

				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("----------------------------------------------------");
			}

			
			
			//=========================================================================================	
			System.out.println("작업 끝...");
			
		//
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close();}catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}
		//에러 'ora%' 로 나오는건 sql문 에러임! 삭제했다 다시실행해볼때엔 sql에서 commit 하는거 잊지말기!
		
		//preparedStatement객체와 statement객체의 차이점
		//쿼리 템플릿을 ?로 미리 짜놓는다.
		// 장점 : 쿼리가 미리 고정적으로 만들어져있어 컴파일해서 값만 바꿔서 바로바로 여러번 실행이 가능하다. state보다 속도가 향상됨.
		// preparedStatement 는 보안이슈때문에 권고된다. 가장 큰 장점은 ? 때문에 보안이 된다는 점임./???sqlinjection? sqlindection? 을 검색해보렴. 
		// ? 세팅할 시 setInt setString 으로 나누는 이유? String sql에서 넣을 때에 입력방식의 차이때문.

	}
}
