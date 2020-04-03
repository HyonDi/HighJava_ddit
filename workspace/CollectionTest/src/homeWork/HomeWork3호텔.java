package homeWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class HomeWork3호텔 {
	private Scanner scan;
	private Map<String, guest> hotelMap;
	
	public HomeWork3호텔() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	//메뉴 출력
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인 ");
		System.out.println("2. 체크아웃");
		System.out.println("3. 객실상태");
		System.out.println("4. 업무종료");
		System.out.print("번호 입력 >>");
	}
	
	//프로그램 시작 메서드
	public void hotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		
		while(true) {
			
			displayMenu();
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
			case 1 : checkIn(); //등록
				break;
				
			case 2 : checkOut(); //삭제
				break;
			
			case 3 : room(); // 전체출력
				break;
			
			case 4 :
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
				
			default :
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
			
		}
	}
	
//phonebook의 displayAll
	private void room() {
		Set<String> keySet = hotelMap.keySet();
		System.out.println("==================================");
		System.out.println("    \t방번호\t투숙객이름");
		System.out.println("==================================");
		
		if(keySet.size() == 0) {
			System.out.println("이용중인 방이 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt ++;
				String roomNum = it.next();
				guest g = hotelMap.get(roomNum);
				
				System.out.println(" " + cnt + "\t" +g.getRoomNum()+ "\t" + g.getName());
			}
		}
		System.out.println("======================================");
		System.out.println("출력완료");
		System.out.println();
		
	}
//------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 >> ");
		String roomNum = scan.next();
		
		guest g = hotelMap.get(roomNum);
		
		if(hotelMap.get(roomNum) == null) { 
			System.out.println(roomNum + "호에는 체크인한 사람이 없습니다. ");
			return;
		} else { 
			hotelMap.remove(roomNum);
			System.out.println(roomNum + "호 ," + g.getName() + " 님 체크아웃이 완료되었습니다.");
		}/*else if(hotelMap.remove(roomNum) == null){//체크를 위해 실행을 하니까 지워짐!! 
			System.out.println(roomNum + "호 ," + g.getName() + " 님 체크아웃이 완료되었습니다.");
		}*/
		
	}

	private void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 >>");
		String roomNum = scan.next();
		
		//방번호 중복 검사
		
		if(hotelMap.get(roomNum) != null ) {
			System.out.println();
			System.out.println(roomNum + "호 에는 이미 사람이 있습니다.");
			return;
	}
		System.out.print("투숙객 이름 >>");
		String name = scan.next();
		
		hotelMap.put(roomNum, new guest(roomNum, name));
		System.out.println(roomNum + "호 ," +name + "님 체크인 되었습니다.");
	}
	
	
//---------------------------------메인-------------------------------------------------
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		new HomeWork3호텔().hotelStart(); //실행부
		
			
}


class guest {
	private String roomNum;
	private String name;
	public guest(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}
	//set이랑 new 랑 두번쓸 필요없이 파라미터 두개를 받으면서 객체생성하는 동시에 세팅할 수 있음.
	
	
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "hotel [방번호 : " + roomNum + ", 투숙객 : " + name + "]";
	}
	
	//출력
	
	
	}
}
