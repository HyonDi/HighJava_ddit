package kr.or.ddit.basic;
/*
 * <싱글쓰레드 일생>
 * 실행이되고 쓰레드가 메인메서드로 들어오고 포문을 만나 반복되고 println으로 한칸띄우고 다시 포문만나서 반복되고 메인 닫는 괄호를 만나서 꽈당하고 죽음.
 * 
 * */
public class T01_ThreadTest {
public static void main(String[] args) {
	// 싱글 쓰레드 프로그램
	for(int i = 1; i <=200; i++) {
		System.out.print("*");
	}
	
		System.out.println();
		
	for(int i = 1; i <=200; i++) {
		System.out.print("$");
	}
}
}
