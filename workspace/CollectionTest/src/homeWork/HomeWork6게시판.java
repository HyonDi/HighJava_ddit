package homeWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal




*/
public class HomeWork6게시판 {
	

	private Connection conn; // db 연결시에 사용.
	private Statement stmt; //sql문을 dbms에 보낼 때 사용. 보안에 약하다는 단점.
	private PreparedStatement pstmt; // sql문을 dbms에 보낼 때 사용. '?' 로 보내기에 보안에 강하다.
	private PreparedStatement pstmt2; // 보낼 sql문에 2개여서.
	private ResultSet rs; // dbms에게 받을 sql결과를 담는 용도.
	
	//preparedStatement객체와 statement객체의 차이점
	// preparedStatement 는 쿼리 템플릿을 ?로 미리 짜놓는다.
	// 장점 : 쿼리가 미리 고정적으로 만들어져있어 컴파일해서 값만 바꿔서 바로바로 여러번 실행이 가능하다. 따라서 statement보다 속도가 향상됨.
	// preparedStatement 가 보안이슈 때문에 권고된다. ? 때문에 보안이 되기때문. 
	// ? 세팅할 시 setInt setString 으로 나누는 이유? String타입은 sql에서 넣을 때에 입력방식이 다르기때문.
	
	
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴출력
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("=====================================");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 글 전체 보기");
		System.out.println("  2. 새 글 작성");
		System.out.println("  3. 수정");
		System.out.println("  4. 삭제");
		System.out.println("  5. 검색");
		System.out.println("  6. 종료");
		System.out.println("=====================================");
		System.out.print("원하는 작업을 선택해주세요. >> ");
	}
	
	/**
	 *  프로그램 시작
	 */
	public void start() {
		int choice;
		do {
			displayMenu();
			choice = scan.nextInt();
			switch(choice) {
				case 1 : //글 전체보기
					displayAll();
					break;
					
				case 2 : //새 글 작성
					write();
					break;
					
				case 3 : //수정
					update();
					break;
					
				case 4 : //삭제
					delete();
					break;
					
				case 5 : //검색
					search();
					break;
				
				case 6 : //종료
					System.out.println("프로그램을 종료합니다.");
					System.out.println("=====================================");
					break;
				
				default : 
					System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");
					
			}
		}while(choice!=6);
	}

	private void search() {
		
		System.out.println();
		scan.nextLine(); // 버퍼에 남아있는 공백이나 엔터키가 있을까봐 비워놓는용.
		System.out.println("검색할 키워드를 입력하세요. >>");
		String keyword = scan.nextLine();
		
		try {
			
			conn =DBUtil.getConnection(); // sql문을 dbms로 보내야해서 미리 연결한다.
			
			String sql = "SELECT * FROM jdbc_board where board_content like '%'||? "
							+ " OR board_content like ?||'%' OR board_content like '%'||?||'%'"
							+ " OR board_title like ?||'%' OR board_title like '%'||? "
							+ " OR board_title like '%'||?||'%' " + " OR board_writer like ?||'%' "
							+ " OR board_writer like '%'||?||'%' OR board_writer like '%'||?";
							// dbms에 보낼 sql문. dbms에 그대로 쓸 부분은 ""혹은 '' 속에 담아 작성하고,
							// 자바에서 찾아야하는 변수, + 기호 등은 따옴표속에 담으면 안됨.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			pstmt.setString(4, keyword);
			pstmt.setString(5, keyword);
			pstmt.setString(6, keyword);
			pstmt.setString(7, keyword);
			pstmt.setString(8, keyword);
			pstmt.setString(9, keyword);
			// prepareStatement 를 사용해 dbms에 보낼것이기에 미리 양식을 작성해놓는 것.
			
			
			rs = pstmt.executeQuery();
			// SELECT문의 결과는 pstmt.executeQuery() 로 받는다.
			// 받은 결과는 resultSet(rs) 에 담아둔다.
			
			
			
			System.out.println("============================================");
			while(rs.next()) {// 위에서 작성한 쿼리의 결과의 처음부터, 다음결과가 있는가? 있으면 true, 없으면 false가 되어 rs 전체를 읽는 용도.
				String bodNo = rs.getString("board_no");
				String bodTitle = rs.getString("board_title");
				String bodWriter = rs.getString("board_writer");
				String bodDateStr = rs.getString("board_date");
				String bodContent = rs.getString("board_content");
				// resultSet 이 가지고있는 함수를 사용해  파라미터로 sql의 컬럼명을 넣어 해당값을 다른 변수에 담았다.
				System.out.println("---------------------------------------");
				System.out.println("게시판 번호 : " + bodNo);
				System.out.println("작성자 : " + bodWriter);
				System.out.println("작성날짜 : " + bodDateStr );
				System.out.println("제목 : " + bodTitle);
				System.out.println("내용 : " + bodContent);
				System.out.println("---------------------------------------");
				// 출력 시 위에서 담은 변수를 사용했다.
			}
			System.out.println("============================================");
			System.out.println("작업 끝=");
			
		}catch(SQLException e) { // sql문에 관한 에러발생시를 위함.
			e.printStackTrace();
		}finally {
			disConnect(); // 사용했던 자원을 반납하는 용도. 
		}
		
		
		
		
		
	}

	private void delete() {
		System.out.println();
		System.out.print("삭제할 게시글 번호를 입력해 주세요 >>");
		String bodNo = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bodNo);
			// 앞에써진 1이 첫번째 컬럼을 의미하는데, 현재 첫번째 컬럼은 board_no 이다.
			
			int cnt = pstmt.executeUpdate();
			// SELECT 문을 제외하고는 pstmt.executeUpdate() 메서드를 사용한다.
			// 이 메서드는 성공한 값의 갯수를 반환해 int타입의 변수에 담았다.
			
			if(cnt > 0) {
				System.out.println("============================================");
				System.out.println(bodNo + "번 게시글이 삭제되었습니다.");
				System.out.println("============================================");
			} else {
				System.out.println("============================================");
				System.out.println(bodNo + "번 게시글 삭제 실패!!");
				System.out.println("============================================");
			}
		} catch(SQLException e) {
			System.out.println("============================================");
			System.out.println(bodNo + "번 게시글 삭제 실패!!");
			System.out.println("============================================");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	private void update() {
		System.out.println();
		String bodNo = "";
		
		System.out.print("수정할 게시판 번호를 입력해주세요. >>");
		bodNo = scan.next();
		
		scan.nextLine(); // 버퍼비우는 용도.
		System.out.println("수정할 내용을 입력해 주세요.");
		System.out.print("수정할 제목  >>");
		String bodTitle = scan.next();
		scan.nextLine();
		
		System.out.print("수정할 내용 >>");
		String bodContent = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE jdbc_board " + " SET board_title = ?, " + " board_content = ?" + " WHERE board_no = ?";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bodTitle);
			pstmt.setString(2, bodContent);
			pstmt.setString(3, bodNo);
			
			int cnt = pstmt.executeUpdate();
			// SELECT문 X, UPDATE문이니까.
			
			if(cnt > 0) { // 0개 : UPDATE에 성공한 쿼리가 없다는 뜻.
				System.out.println("============================================");
				System.out.println(bodNo + "번 게시물 수정을 완료했습니다.");
				System.out.println("============================================");
			} else {
				System.out.println("============================================");
				System.out.println(bodNo + "번 게시물 수정에 실패했습니다.");
				System.out.println("============================================");
			}
		} catch(SQLException e) {
			System.out.println("============================================");
			System.out.println(bodNo + "번 게시물 수정 실패!");
			System.out.println("============================================");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
	}

	

	/**
	 * 글 전체보기
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("===============게시판 전체 출력================");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board";
			
			stmt = conn.createStatement();
			// 연결!
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				int bodNo = rs.getInt("board_no");
				String bodWriter = rs.getString("board_writer");
				String bodContent = rs.getString("board_content");
				String bodTitle = rs.getString("board_title");
				
				
				Date utilDate = new java.util.Date(rs.getTimestamp("board_date").getTime());
		//얘는 자바타입 데이트 변수									얘는 sql타입데이트
				   
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd hh:mm:ss");
				String bodDateStr =  sdf.format(utilDate);
				
				System.out.println("---------------------------------------");
				System.out.println("게시판 번호 : " + bodNo);
				System.out.println("작성자 : " + bodWriter);
				System.out.println("작성날짜 : " + bodDateStr );
				System.out.println("제목 : " + bodTitle);
				System.out.println("내용 : " + bodContent);
				System.out.println("---------------------------------------");
				
			}
			System.out.println("======================================");
			System.out.println("출력 완료.");
			System.out.println("============================================");
		} catch(SQLException e) {
			System.out.println("============================================");
			System.out.println("게시글 가져오기 실패!!!");
			System.out.println("============================================");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	/**
	 * 새 글 작성
	 */
	private void write() {

			System.out.print("이름을 입력해주세요. >>");
			String writer = scan.next();
			scan.nextLine();
			System.out.print("제목을 입력해 주세요. >>");
			String title = scan.next();
			scan.nextLine();
			System.out.print("내용을 입력해 주세요. >>");
			String contents = scan.next();
			
			try {
				conn = DBUtil.getConnection();
				
			String sql2 = "insert into jdbc_board values (board_seq1.nextval, ?, ?, SYSDATE, ?)";
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, title);			
			pstmt2.setString(2, writer);
			pstmt2.setString(3, contents);
			
			
			int cnt = pstmt2.executeUpdate();
			if(cnt>0) {
				System.out.println("============================================");
				System.out.println(writer + "님의 글이 등록되었습니다.");
				System.out.println("============================================");
			}else {
				System.out.println("============================================");
				System.out.println(writer + "님의 글 등록이 실패했습니다.");
				System.out.println("============================================");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
		
	}
	
	
	private void disConnect() {
	//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}
	
	public static void main(String[] args) {
//		HomeWork6게시판 board = new HomeWork6게시판();
		new HomeWork6게시판().start(); //실행부	
	}
	
	
	
}
