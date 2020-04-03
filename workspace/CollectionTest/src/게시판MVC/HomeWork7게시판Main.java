package 게시판MVC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HomeWork7게시판Main {
	private Scanner scan = new Scanner(System.in);
	게시판ServiceImpl bodService =게시판ServiceImpl.getinstance();
	int choice;
/*	//service의 객체변수 선언
	private 게시판Service bodService;
	
	public HomeWork7게시판Main() {
		bodService = new 게시판ServiceImpl();
	}
	*/
	
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
		// 검색할 회원ID, 회원이름, 전화번호, 주소등을 입력하면
		// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		// 주소는 입력한 값이 포함만 되어도 검색 되도록 한다.
		// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println();
		
		System.out.println("검색할 키워드를 입력해 주세요. >>");
		String keyword = scan.nextLine();
		
		게시판VO bodVO = new 게시판VO();
/*		System.out.print("게시글 번호 >> ");
		int bodNo = System.in.read();*/
		
/*		System.out.print("게시글 제목 >> ");
		String bodTitle = scan.nextLine().trim();
		

		System.out.print("게시글 내용 >> ");
		String bodContent = scan.nextLine().trim();
		
		
		System.out.println("작성자 >> ");
		String bodWriter = scan.nextLine().trim();
		
		게시판VO bodVO = new 게시판VO();
*/
//		bodVO.setBoard_no(bodNo);
		bodVO.setBoard_title(keyword);
		bodVO.setBoard_content(keyword);
		bodVO.setBoard_writer(keyword);
		List<게시판VO> bodList = bodService.search(bodVO);
		ArrayList<String> str = new ArrayList<String>();
		
		if(bodList.size() == 0) {
			System.out.println("검색 결과가 없습니다.");
		}else {
			System.out.println("================검색 결과===============");
			for(게시판VO bodVO2 : bodList) {
				System.out.println("-------------------------------------");
				System.out.println("게시판 번호 : " + bodVO2.getBoard_no());
				System.out.println("작성자 : " + bodVO2.getBoard_writer());
				System.out.println("작성날짜 : " + bodVO2.getBoard_date() );
				System.out.println("제목 : " + bodVO2.getBoard_title());
				System.out.println("내용 : " + bodVO2.getBoard_content());
				System.out.println("-------------------------------------");
			}
			System.out.println("=====================================");
			System.out.println("출력을 완료했습니다.");
		}

	}

	
	private void delete() {
		System.out.println();
		System.out.print("삭제할 게시글 번호를 입력해 주세요 >>");
		int bodNo = scan.nextInt();
		
		int cnt = bodService.delete(bodNo);
		
		if(cnt > 0) {
			System.out.println(bodNo + "번 게시글 삭제를 완료했습니다.");
		} else {
			System.out.println(bodNo + "번 게시글 삭제 실패했습니다.");
		}
		
	}

	private void update() {
		System.out.println();
		int bodNo = 0;
		
		
		System.out.print("수정할 게시판 번호를 입력해주세요. >>");
		bodNo = scan.nextInt();
		
		
		System.out.println("수정할 내용을 입력해 주세요.");
		System.out.print("수정할 제목  >>");
		String bodTitle = scan.next();

		scan.nextLine(); 
		System.out.print("수정할 내용 >>");
		String bodContent = scan.next();
		
		게시판VO bod = new 게시판VO();
		bod.setBoard_no(bodNo);
		bod.setBoard_content(bodContent);
		bod.setBoard_title(bodTitle);
		
		int cnt = bodService.update(bod);
		
		if(cnt > 0) {
			System.out.println(bodNo + "번 게시글 수정을 완료했습니다.");
		} else {
			System.out.println(bodNo + "번 게시글 수정을 실패했습니다.");
		}
		
		
		
	}

	

	/**
	 * 글 전체보기
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("=============게시판 전체 출력==============");
		
		List<게시판VO> bodList = bodService.displayAllList();
		if(bodList.size() == 0) {
			System.out.println("게시글이 등록되어있지 않습니다.");
		}else {
			for(게시판VO bodVO : bodList) {
				
				System.out.println("-------------------------------------");
				System.out.println("게시판 번호 : " + bodVO.getBoard_no());
				System.out.println("작성자 : " + bodVO.getBoard_writer());
				System.out.println("작성날짜 : " + bodVO.getBoard_date() );
				System.out.println("제목 : " + bodVO.getBoard_title());
				System.out.println("내용 : " + bodVO.getBoard_content());
				System.out.println("-------------------------------------");
			}
		}
		System.out.println("=====================================");
		System.out.println("출력을 완료했습니다.");
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
			scan.nextLine();
			
			//입력받은 정보를 VO객체에 넣는다.
			게시판VO bodVO = new 게시판VO();
			bodVO.setBoard_writer(writer);
			bodVO.setBoard_title(title);
			bodVO.setBoard_content(contents);
			
			int cnt = bodService.write(bodVO);
			
			if(cnt > 0) {
				System.out.println(writer + "님 게시글이 작성되었습니다.");
			} else {
				System.out.println(writer + "님 게시글 작성을 실패하였습니다.");
			}
			
		
		
	}
	
	

	
	public static void main(String[] args) {
		
		HomeWork7게시판Main board = new HomeWork7게시판Main();
		board.start();
		
		
		
	}
	
}
