package homeWork;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HomeWork4로또 {
/*	 로또를 구매하는 프로그램 작성하기
	 
	 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	 입력한 금액에 맞게 로또번호를 출력한다.
	 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	      출력한다.)

		==========================
	         Lotto 프로그램
		--------------------------
		 1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 1  <-- 입력
				
		 Lotto 구입 시작
			 
		(1000원에 로또번호 하나입니다.)
		금액 입력 : 2500  <-- 입력
				
		행운의 로또번호는 아래와 같습니다.
		로또번호1 : 2,3,4,5,6,7
		로또번호2 : 20,21,22,23,24,25
				
		받은 금액은 2500원이고 거스름돈은 500원입니다.
				
	   	 ==========================
	         Lotto 프로그램
		--------------------------
		  1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 2  <-- 입력
			
		감사합니다*/
	
public static void main(String[] args) {
	
	while(true) {
		
		System.out.println("==========================");
		System.out.println("       Lotto 프로그램		  ");
		System.out.println("--------------------------");
		System.out.println(" 1. Lotto 구입");
		System.out.println(" 2. 프로그램 종료");
		System.out.println("==========================");
		
		Scanner scan = new Scanner(System.in);

		
		System.out.print("번호 입력 >> ");
		int menu = scan.nextInt();
		System.out.println();
	switch(menu) {
		case 1 : 
			buy();
			break;
		
		case 2:		
			System.out.println("프로그램 종료.");
			return;
			
		default : System.out.println("잘못입력하셨습니다. 다시입력해주세요.");
		
		}
	}

}


private static void buy() {
	Scanner scan = new Scanner(System.in);
	System.out.println("----------------------------------");
	System.out.println("얼마나 구입하시겠습니까?");
	System.out.println("로또 가격 : 1000원");
	System.out.print("지불할 금액 : ");
	
	int inputMon = scan.nextInt();
	System.out.println();
	
	
	/*횟수, 거스름돈*/
	int count = inputMon/1000;
	int exchange = inputMon%1000;

	System.out.println(inputMon + "원을 받아 " + count + "장 출력되었습니다.");
	Set<Integer> intRnd = new HashSet<>();
	
	
	for(int i= 0 ; i < count; i++) {
		intRnd.clear();
		while(intRnd.size() < 6) {// Set의 데이터가 6개 될때까지 반복함.
			int num = (int) (Math.random()*45 + 1);//1~100사이의 난수
			intRnd.add(num);
		}
//		System.out.println((i+1) + " 회 : " + intRnd);

		System.out.println("----------------------------------");
		System.out.print((i+1) + " 회 : ");
		for(Integer num : intRnd) {
			System.out.print(num + " ");
		}
		System.out.println();

		
	}
	System.out.println("----------------------------------");
	
	System.out.println("받은 금액은  " + inputMon +"원이고, "
					+ "거스름돈은 " + exchange + "원 입니다. ");
	System.out.println();
	
}

	
}
