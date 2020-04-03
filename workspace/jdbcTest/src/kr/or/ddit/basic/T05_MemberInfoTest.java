package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);//이부분 오라클에 넣고 실행시켰음.

*/
public class T05_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력 (1번째로 만듦.)
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정(3번째로 만듦.)
					updateMember();
				
					break;
				case 4 :  // 전체 자료 출력(2번째로 만듦.)
					displayMemberAll();
			
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 회원 정보를 삭제하는 메서드 (입력받은 회원 ID를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 회원 ID를 입력해주세요 >> ");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM mymember WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			if( cnt > 0) {
				System.err.println(memId + "회원 삭제 성공...");
			} else {
				System.out.println(memId + "회원 삭제 실패!!!");
			}
		} catch(SQLException e) {
			System.out.println(memId + "회원 삭제 실패!!!!");
			e.printStackTrace();
		}finally {
			disConnect();//자원 반납
		}
	}

	/**
	 * 회원정보를 수정하는 메서드(insert 랑 비슷함)
	 */
	private void updateMember() {
		System.out.println();
		String memId = "";
		boolean chk = true;
		
		do {
			System.out.println("수정할 회원 ID를 입력하세요 >> ");
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk == false) {//count 가 0이면 false 니까 없는 아이디라는 뜻. 
				System.out.println(memId + "회원은 없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력해주세요.");
			}
		} while(chk == false);
		
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 회원 이름 >>");
		String memName = scan.next();
		
		System.out.print("새로운 회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //버퍼 비우기
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE mymember" + " SET mem_name = ?, " + " mem_tel = ?, " + " mem_addr = ? " + " WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				 System.out.println(memId + "회원의 정보를 수정했습니다.");
			} else {
				System.out.println(memId + "회원 정보 수정 실패!!!");
			}
		} catch(SQLException e) {
			System.out.println(memId + "회원 정보 수정 실패!!!!");
			e.printStackTrace();
		} finally {
			disConnect();// 자원반납
		}
	}

	
	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println(" ID\t이름\t전화번호\t\t주소");
		System.out.println("---------------------------------------------------");
		
		//전체출력이니까 파라미터 받을필요없음.
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM mymember";
			
			//prepared하지 않아도 될듯해 stmt에 담았다.
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr );
			}
			System.out.println("---------------------------------------------------");
			System.out.println("출력 작업 끝...");
		} catch(SQLException e) {
			System.out.println("회원 자료 가져오기 실패!!!!");
			e.printStackTrace();
		} finally {
			disConnect();
		}
				
		
	}

	/**
	 * 회원 추가하는 메서드
	 */
	private void insertMember() {
		
		boolean chk = false;//중복체크
		String memId = "";
		do {
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원  ID >> ");
			memId = scan.next();
			
			chk = getMember(memId);//중복체크
			if(chk) {
				System.out.println("회원ID가 ' " + memId + " ' 인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(chk == true);
		
		//여기에왔다는건 신규회원아이디를 입력했다는 것.
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();//버퍼비우기
		System.out.print("회원 주소 >> ");
		String memAddr = scan.next();
		
		try {
			conn = DBUtil.getConnection();//커넥션
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) " + " values (?, ?, ?, ?)";//쿼리문작성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId); //1번째컬럼에 mem_id를 넣는다는 세팅같음.
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			//이제 날려야함.( = 보내는거?)
			//결과값이 인트로 날라올거야. executeUpdate() 쓸거니까. insert 구문이니까.
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공...");
			} else {
				System.out.println(memId+ "회원 추가 작업 실패!!!");
			}
		} catch(SQLException e) {
			System.out.println(memId + "회원 추가 작업 실패!!!!");
			e.printStackTrace();
		}finally {
			disConnect();//자원반납!
		}
	}

	/**
	 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드.
	 * @param memId
	 * @return true : 이미존재함. 중복됨./ false : 신규회원. 중복되지않음.
	 */
	private boolean getMember(String memId) {//id값을 파라미터로 받아서 중복인지 아닌지 체크할것임.있으면(중복이 되면 true, 없으면 false 리턴.

		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM mymember " + " WHERE mem_id = ?"; //있는지없는지 행수를 조회해보았다.
			
			pstmt = conn.prepareStatement(sql);//날림
			pstmt.setString(1, memId);//세팅
					
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");//cnt는 알리아스임(COUNT(*))
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			chk = false; //디폴트라 안써도 되긴함.
		}finally {
			disConnect();
		}
		return chk;
	}
	
	/**
	 * 자원반납용 메서드
	 */
	private void disConnect() {
	//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}

	public static void main(String[] args) {
		T05_MemberInfoTest memObj = new T05_MemberInfoTest();
		memObj.start();
	}

}






