package kr.or.ddit.basic;

import java.util.Vector;

public class T01_VectorTest {
//	리스트를 임플함.
	public static void main(String[] args) {
		//Vector => List 계열 클래스
		//List , Vector 폴리모피션? 다형성. 바꿔서 사용해도 된다.
		//컬렉션프레임워크를 상속받으면 . 리스트계열이면 바꿔쓸 수 있다는 듯.
		//벡터도 리스트기때문에 
		
		//<< CRUD >>
		
		//List v1 = new arrayList(); --아래와 같다.
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());
		
		//Vector 는 add() 메서드를 이용하여 데이터를 추가할 수 있다.
		v1.add("aaa");
		v1.add(111);//자동으로 파싱해서 아래와같이 자동으로 바꿔주어 잘 들어간다. 오토박싱?
		v1.add(new Integer(123));
		v1.add('a');//얘도 객체로 바꿔들어가서 ..래퍼클래스?
		v1.add(true);
		v1.add(3.14);
		
		System.out.println("현재 크기 : " + v1.size());
		
		//Vector는 addElement() 메서드를 이용하여 추가할 수도 있는데
		// 이 메서드는 기본적으로 add() 메서드와 같은 기능을 수행한다.
		v1.addElement("ccc");
		
		System.out.println("v1 => " + v1.toString());
		//add(index, 데이터) => 벡터의 index 번째에 '데이터'를 끼워 넣는다.
		//					=> index는 0부터 시작한다.
		
		
		v1.add(1, "kkk"); //오버로드된 메서드. 데이터를 하나 넣으면 / 데이터의 위치와함께 값을 넣으면
		System.out.println("v1 => " + v1.toString());
		
		//set(index, 데이터) => 벡터의 index번째의 값을 주어진 '데이터'로 덮어쓴다. (반환값 : 원래의 데이터)
		String temp = (String)v1.set(0, "zzz");
		System.out.println("set명령 후 v1 => " + v1);
		System.out.println("원래의 데이터 : " + temp);
		
		//remove(index) => 벡터의 index번째 자료를 삭제한다.
		//				=> 자료가 삭제되면 index번째 다음번째 이후의 데이터들이 앞으로 자동으로 당겨져서 채워진다.
		
		//remove(삭제할 데이터) => '삭제할 데이터'를 찾아 삭제한다.
		//					=> 만약 '삭제할 데이터' 가 여러개이면 앞에것 만 삭제함.
		//					=> 삭제할 데이터가 '정수형' 이거나 'char'형 일경우에는
		//					=> 삭제할 데이터를 객체로 변환해서 사용해야 한다.
		
		v1.remove(0);
		System.out.println("삭제 후 : " + v1);
		System.out.println();
		
		temp = (String)v1.remove(0);
		System.out.println("삭제된 자료 : " + temp);
		System.out.println("삭제 후 : " + v1);
		System.out.println();
		
//		v1.remove(123); //컴파일러가 인덱스번호인줄 안다.그래서 정수형, 캐릭터형은 객체를 만들어서 지워야한다.
//		v1.remove(true);
//		System.out.println("삭제후 : " + v1);
//		System.out.println();
//		
		v1.remove(new Integer(123)); //위를 객체형으로 만든 것.
		System.out.println("삭제 후 : " + v1);
		System.out.println();
		
		v1.remove(new Character('a')); //캐릭터형을 객체형으로 만든 것.
		System.out.println("삭제 후 : " + v1);
		System.out.println();
		
		v1.remove(3.14);
		System.out.println("삭제 후 : " + v1);
		System.out.println();
		
		//get(index) => index번째 자료를 반환한다.
		int data = (int) v1.get(0);
		System.out.println("0번째 자료 : " + data);
		System.out.println("-------------------------------------------------");
		
		
		// << 제너릭 >> 페이지를 보고온다.
		
		
		Vector<String> v2 = new Vector<>(); // String만 저장 가능
		Vector<Integer> v3 = new Vector<Integer>();//Integer만 저장 가능		
		
		v2.add("안녕하세요");
		//v2.add(123); //오류: 다른 종류의 데이터를 추가할 수 없다.
		
		String temp2 = v2.get(0);
		System.out.println("temp2 = >" + temp2);
		
		Vector<Vector> vv = new Vector<Vector>();
		
//		Vector<Vector<Vector>> vv = new Vector<>(); //이렇게도 가능하다.
		//------------------------------------------------------------------------------
		v2.clear(); //벡터의 모든 데이터를 삭제한다. 사이즈도 0으로 만듬.
		System.out.println("v2dml size = " + v2.size());
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("삭제되기 전 v2 => "+ v2);
		//removeAll(Collection객체) => 백터의 데이터중에서 'Collection 객체'
		//							가 가지고 있는 데이터를 모두 삭제한다.
		
		v2.removeAll(v4);
		System.out.println("삭제된 후 v2 => " + v2);
		System.out.println("-----------------------------------------------------------");
		//---------------------------------------------------------------------------------
		
		v2.clear();
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		//벡터의 데이터들을 순서대로 모두 가져와서 사용하고 싶으면
		//반복문을 사용하면 된다. (주로 for문을 사용함.)
		for(int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번째 자료 : " + v2.get(i));
		}
		System.out.println("==========================================================");
		
/*		향상된 for 문
		형식)
		for(자료형명 변수명 : 배열변수나 Collection계열 변수) {
			처리할 내용들;
			...
		}
		=> 주어진 '배열변수나 Collection계열 변수 '의 데이터 개수만큼 반복한다.
		=> 반복할때마다 '배열변수나 Collection계열 변수' 의 데이터를 하나씩 꺼내와서
			'변수명'에 저장한 후 '처리할 내용들'을 처리한다.
*/		
		for(String s : v2) {
			System.out.println(s);
		}
		System.out.println("-----------------------------------------------------------");
		
		// 만약 제너릭을 사용하지 않은 Collection을 향상된 for문으로 처리할 경우에는 Object형으로 처리한다.
		for(Object obj : v1) {
			System.out.println(obj);
		}
		
		
		//----------------------------------------------------------------------------------
		
		
		System.out.println("-------------------벡터 사이즈 및 용량 메서드 예제-------------------");
		
		Vector v = new Vector(5); //용량이 5인(사이즈는 0) 벡터 생성
		v.add("홍길동");
		v.add("박찬호");
		v.add("3");
		print(v);
		
		v.trimToSize(); //  벡터의 용량을 현재 벡터 사이즈로 줄인다.
		System.out.println("====== After trimToSize() ======");
		print(v);
		
		v.ensureCapacity(5); // 현재용량이 최소용량보다 작다면....
							// 신규용량 = 현재용량 * 2
							// 그래도 최소용량보다 작다면 최소용량으로 설정함.
		System.out.println("====== After ensureCapacity(5) ======");
		print(v);
		
		v.setSize(7); // 현재용량이 설정 사이즈보다 작으면 신규용량 = 현재용량*2
					  // 그래도 설정사이즈보다 작다면 신규용량 = 설정사이즈로 설정함.
		System.out.println("====== After setSize(7) =======");
		print(v);
		
		v.clear();
		System.out.println("======= After clear() =======");
		
		print(v);
	}
	
	public static void print(Vector v) {
		System.out.println(v);
		System.out.println("size : " + v.size());
		System.out.println("capacity : " + v.capacity());
	}
	
	
	
	
	
	
}
