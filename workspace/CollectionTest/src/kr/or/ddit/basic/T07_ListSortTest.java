package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T07_ListSortTest {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-5555"));
		memList.add(new Member(2, "일지매", "010-6666-6666"));
		
		System.out.println("정렬전 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("============================================");
		
		Collections.sort(memList); // 정렬하기
		
		System.out.println("이름의 오름차순으로 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("================================================");
		
		//외부정렬기준을 이용한 정렬하기.
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("================================================");
		
	}

}

/**
 * 정렬 기준의 외부 선언을 위해서는 Comparator 인터페이스를 구현하면된다.
 * Member의 번호(num)의 내림차순으로 정렬하기
 * */

class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		} else {
			return 1;
		}
		*/
/*		
		Wrapper 클래스에서 제공하는 메서드를 이용하는 방법1
		내림차순일 경우에는 -1을 곱해준다.
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
*/				
//		Wrapper클래스에서 제공하는 메서드를 이용하는 방법2
		return new Integer(mem1.getNum()).compareTo(mem2.getNum())* -1;
		
	//맨뒤에 1을 곱하면 오름차순이다.
		
	}
	
}

class Member implements Comparable<Member> {//스튜던트객체를 정렬하도록 할것이다.
	private int num; //번호
	private String name; //이름
	private String tell; //전화번호

	
	
public Member(int num, String name, String tell) { //생성자
		super();
		this.num = num;
		this.name = name;
		this.tell = tell;
	}


public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}



public String getTell() {
	return tell;
}



public void setTell(String tell) {
	this.tell = tell;
}

public String toString() { //출력예쁘게하기위한 부분.
	return "Member [num =" + num + ", name =" + name + ", tel=" +tell + "]";
}

//이름을 기준으로 오름차순 정렬이 되도록 설정해보자.
@Override
public int compareTo(Member mem) { //나랑 비교대상이랑 비교하는 것. 스트링객체안에 compareTo 가 자체적으로 구현되어있다.
	return getName().compareTo(mem.getName());
}
	
	
}