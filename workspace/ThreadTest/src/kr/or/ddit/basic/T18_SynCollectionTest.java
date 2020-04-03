package kr.or.ddit.basic;
/*
 	Vector, Hashtable 등의 예전부터 존재하던 Collection 들은 내부에
 	동기화 처리가 되어 있다.
 	
 	그런데, 최근에 새로 구성된 Collection 클래스들은 동기화 처리가 되어있지 않다.
 	그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면
 	동기화 처리를 한 후에 사용해야 한다.
 	
 	왜 처음부터되게해놓지 않은거야? 
 	동기화처리 단점 : 성능-속도가 마아아않이 떨어짐. 여러쓰레드중 하나 접근시 나머지는 기다려야하니까.
 				   쓰레드 갯구 늘수록 악영향. (메모리문제도 0.001 정도있음.)
 				  동기화처리가 꼭 필요할때에만 수동으로 처리하는 편이 좋다.	
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18_SynCollectionTest {
	// 동기화를 하지 않는 경우
	private static List<Integer> list1 = new ArrayList<>();
	
	// 동기화 하는 경우
	// Collections의 정적 메서드 중에서  synchronized로 시작하는 메서드 이름
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		
//		Collections.syn 하고 ctrl+spacebar 하면 컬렉션클래스들에대한 synchronized 들이 쭈욱 나옴.
		
		// 익명 클래스로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 10000; i ++) {
					//list1.add(i); // 동기화 처리를 하지 않은 리스트 사용
					list2.add(i); // 동기화처리되어있는 리스트 사용
				}
			}
		};
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
		
	}
}
// 결과 : System.out.println("list1의 개수 : " + list1.size());  :  
// 예외발생(에러) 이 정상. 5번 쓰레드 시작시켜 list1의 갯수는 50000개가 나와야하는데 에러와함께 10000몇개 밖에 나오지않음.
// 5개가 동시에 add하고 등등 하니 인덱스를 벗어나 에러발생. 10000개밖에 들어가지 않음.

// System.out.println("list2의 개수 : " + list2.size()); :
// 50000개 정상적으로 예외없이 잘 나옴.
