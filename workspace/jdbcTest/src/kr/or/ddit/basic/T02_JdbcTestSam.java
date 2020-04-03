package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * select 예제
 */
public class T02_JdbcTestSam {
	/*
	문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다
		  lprod_id가 큰 자료들을 출력하시오.
		  
	문제2) lprod_id값을 2개 입력받아서 두 값 중 작은값부터
		  큰 값 사이의 자료를 출력하시오.
	*/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// 문제1)
		//System.out.print("lprod_id값 입력 : ");
		//int num = scan.nextInt();
		
		// 문제2)
		System.out.print("lprod_id값 2개 입력 : ");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		scan.close();

		
		int max, min;
		/*
		if(num1>num2){
			max = num1;
			min = num2;
		}else{
			max = num2;
			min = num1;
		}
		*/
		max = Math.max(num1, num2);
		min = Math.min(num1, num2);
		
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe",
					"khj",
					"java"
					);
			
			// Statement객체 생성
			stmt = conn.createStatement();
			
			// SQL문을 Statement객체를 이용하여 실행하고
			// 실행 결과를 ResultSet객체에 저장한다.
			
			// 문제1의 SQL문
			//String sql = "select * from lprod where lprod_id > " + num ;
			
			// 문제2의 SQL문
			//String sql = "select * from lprod "
			//		+ " where lprod_id >= " + min + " and lprod_id <=" + max;
			
			String sql = "select * from lprod "
					+ " where lprod_id  between " + min + " and " + max;
			
			//sql부분은 ""안에 넣고 자바부분은 그냥넣어야하는구나
			
			rs = stmt.executeQuery(sql);
			
			// ResultSet객체에 저장된 자료를 출력한다.
			System.out.println("----- 실행 결과 -----");
			while(rs.next()){
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				//System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_gu : " + rs.getString(2));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------");
			}
			
			System.out.println("자료 출력 끝...");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}

	}

}






