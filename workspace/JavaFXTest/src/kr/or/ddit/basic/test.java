package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;


public class test {
	public static void main(String[] args) {
//		List<String> list = new ArrayList<>();
//		ArrayList<String> list1 = new ArrayList<>();
//		
//		bbbb(list1);
//	}

/*	public static void bbbb(List<String> st) {
		int i =0;
		
	}*/
	현지 현 = new 현지();
	
	붕어빵 c = new 디저트();
	현.a(c);
	현.b((디저트)c);

	
	
	
}



}

class 붕어빵 {
	
}

class 디저트 extends 붕어빵 {
	
}

class 현지 {
	
	void a(붕어빵 aa) {
		System.out.println(aa);
	}

	void b(디저트 aa) {
		System.out.println(aa);
	}
	
}