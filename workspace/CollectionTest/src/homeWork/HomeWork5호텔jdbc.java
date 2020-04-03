
package homeWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class HomeWork5호텔jdbc {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	PreparedStatement pstmt2;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	//메뉴 출력
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("==어떤 업무를 하시겠습니까?==");
		System.out.println("	1. 체크인 ");
		System.out.println("	2. 체크아웃");
		System.out.println("	3. 객실상태");
		System.out.println("	4. 업무종료");
		System.out.println("*******************************************");
		System.out.print("번호 입력 >>");
	}
	
	//프로그램 시작 메서드
	public void hotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		
		int choice;
		do {
			displayMenu(); //메뉴출력
			choice = scan.nextInt(); // 메뉴 번호 입력받기
			switch(choice) {
			case 1 : // 체크인
				checkIn();
				break;
				
			case 2 : //체크아웃
				checkOut(); 
				break;
				
			case 3 : //객실상태
				room();
				break;
			
			case 4 : //종료
				System.out.println("**************************");
				System.out.println("호텔문을 닫습니다.");
				System.out.println("**************************");
				break;
			default :
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
			
		}while(choice!=4);
	}

	//전체출력
	private void room() {
		System.out.println();
		System.out.println("==================================");
		System.out.println("방번호\t투숙객이름");
		System.out.println("==================================");
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe",
					"khj",
					"java");
			
			String sql = "SELECT * FROM guest";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			
			while(rs.next()) {
				String roomNum = rs.getString("roomNum");
				String name = rs.getString("name");
				
				System.out.println(roomNum + "\t" + name );
			}
			System.out.println("==================================");
			System.out.println("--출력 완료--");
		} catch(SQLException e) {
			System.out.println("방 정보 가져오기 실패!!");
			e.printStackTrace();
		} finally {
			if(rs!=null)try{ rs.close();}catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}

		
	}
//---
	
	/*	boolean chk = false;
	try {
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.205.8:1521/xe",
				"khj",
				"java");
		String sql = "SELECT COUNT(*) cnt FROM guest " + "WHERE roomNum = ?";
		
		pstmt = conn.prepareStatement(sql);//날리는부분
		pstmt.setString(1, roomNum);
		
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(cnt > 0) {
			chk = true;
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		chk = false;
		
	}finally {
		if(rs!=null)try{ rs.close();}catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
		return chk;
	}
*/
	

//---------
	
	
	private void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 >> ");
		String roomNum = scan.next();
		
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe",
					"khj",
					"java");
//			//
			
			String name = "";
			String sql = "SELECT name FROM guest WHERE roomNum = " + roomNum;
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				name = rs.getString("name");
//				System.out.println(name);
			}
//			//
			
			
			String sql2 = "DELETE FROM guest WHERE roomNum = ?";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				
				System.out.println(roomNum + "호 " + name + " 님 체크아웃이 완료되었습니다.");
				
			} else {
				System.out.println(roomNum + "호 는 이용중인 방이 아닙니다.");
			}
	
		} catch(SQLException e) {
			System.out.println(roomNum + "호는 이용중인 방이 아닙니다.");
			e.printStackTrace();
		} finally {
			if(rs!=null)try{ rs.close();}catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
			if(pstmt2!=null)try{ pstmt2.close();}catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}
		}
	
	

	private void checkIn() {
		
		System.out.println();

		boolean chk = false;//중복체크
		String roomNum = "";
		
		do {
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.print("방 번호 >>");
			roomNum = scan.next();
			
			chk = getRoom(roomNum);
			if(chk) {
				System.out.println(roomNum + "호는 이미 이용중입니다.");
				System.out.println("다른 호수를 선택해주세요. ");
			}
		}while(chk == true);
		
		//새로 체크인
		
		System.out.print("체크인하시는 분 성합을 입력해주세요. >>");
		String name = scan.next();
		
		try { 
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.205.8:1521/xe",
					"khj",
					"java");
			String sql = "insert into guest (roomNum, name )" + " values (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(roomNum + "호, "+ name + "님 체크인이 완료되었습니다.");
			} else {
				System.out.println(roomNum + "호는 이미 이용중입니다.");
			}
		} catch(SQLException e) {
			System.out.println(roomNum + "호 체크인에 실패하였습니다.");
			e.printStackTrace();
		} finally {
			if(rs!=null)try{ rs.close();}catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		}

	}
	
	
private boolean getRoom(String roomNum) {
		
	boolean chk = false;
	try {
		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.205.8:1521/xe",
				"khj",
				"java");
		String sql = "SELECT COUNT(*) cnt FROM guest " + "WHERE roomNum = ?";
		
		pstmt = conn.prepareStatement(sql);//날리는부분
		pstmt.setString(1, roomNum);
		
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(cnt > 0) {
			chk = true;
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
		chk = false;
		
	}finally {
		if(rs!=null)try{ rs.close();}catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close();}catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
		return chk;
	}

//---------------------------------메인-------------------------------------------------
	public static void main(String[] args) {
		HomeWork5호텔jdbc guest = new HomeWork5호텔jdbc();
		new HomeWork5호텔jdbc().hotelStart(); //실행부	
	}

}
