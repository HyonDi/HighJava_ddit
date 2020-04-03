package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T04_ArrayListTest {
	public static void main(String[] args) {
		//문제 1) 5명의 별명을 입력하여 ArrayList에  저장하고
		//		별명의 길이가 제일 긴 별명을 출력하시오.
		//		단, 각 별명의 길이는 모두 다르게 입력한다.
		Scanner s = new Scanner(System.in);
		

		List<String> aliasList = new ArrayList<String>();
		
		System.out.println("5명의 별명을 차례로 입력하세요.");
		
		for(int i = 0; i <5; i++) {
			System.out.println(i + "번째 별명 : ");
			String name = s.next();
			aliasList.add(name);
		}
		//s.nextLine 은 엔터기준으로 끊어오지만 s.next 는 스페이스바 등 공백으로 구분자를 비교. 비교해보자.	
		
		
		
		//maxAlias 는 제일 긴 별명이 저장될 변수.
		String maxAlias = aliasList.get(0);
//		String maxAlias = ""; // 빈문자로 초기화하면 반복을 0부터 시작한다.

		for(int i = 1; i < aliasList.size(); i++) {
			if(maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
				
			}
			
		}
		System.out.println("제일 긴 별명 : " + maxAlias);
		
		

		//문제2) 문제 1에서 별명의 길이가 같은 것을 여러개 입력했을 때도 처리되도록 하시오.
		List<String> aliasList1 = new ArrayList<String>();
		
		System.out.println("5명의 별명을 차례로 입력하세요.");
		
		for(int i = 0; i <5; i++) {
			System.out.println(i + "번째 별명 : ");
			String name = s.next();
			aliasList1.add(name);
		}
		//maxLen 은 제일 긴 별명의 길이를 저장하는 변수
		int maxLen = aliasList1.get(0).length();
		for(int i =1; i<aliasList1.size(); i++) {
			if(maxLen < aliasList1.get(i).length()) {
				maxLen = aliasList1.get(i).length();
			}
		}
		
		//길이가 가장 긴별명들 프린트.
		System.out.println("제일 긴 별명들");
		for(int i = 0; i <aliasList1.size(); i++) {
			if(maxLen==aliasList1.get(i).length()) {
				System.out.println(aliasList1.get(i));
			}
		}
		
		
		
		
	}
		
}
