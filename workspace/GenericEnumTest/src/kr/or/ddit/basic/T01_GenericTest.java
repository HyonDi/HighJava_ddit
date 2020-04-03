package kr.or.ddit.basic;

/*
	제너릭 클래스를 만드는 방법
	
	형식)
	class 클래스 명<제너릭타입글자>{
		제너릭타입글자 변수명; //변수 선언에 제너릭을 사용할 경우
		...
		제너릭 타입글자 메서드명() { //반환값이 있는 메서드에서 사용
			...
			
			return 값;
		}
		...
	}
	
	-- 제너릭타입글자 --
	T => type
	K => key
	V => value
	E => Element(자료구조에 들어가는 것들을 나타낼 때 사용)
	
	--제너릭!
	--타입세이프티(타입안정성)을 보장하기위해 사용.
	--내가 원하는 타입만 강제로 지정시킬 수 있다.
	--캐스팅작업을 할 필요가 없다는 장점이 있다.
	
	
*/	
	class NonGenericClass{
		private Object val;

		public Object getVal() {
			return val;
		}

		public void setVal(Object val) {
			this.val = val;
		}
	}
	
	class MyGeneric<T>{
		private T val;

		public T getVal() {
			return val;
		}

		public void setVal(T val) {
			this.val = val;
		}
	}
	
	public class T01_GenericTest {
		
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환 값 irtnNg2 => " + irtnNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);

		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제너릭 정수열 반환값 : " + irtnNg2);
		
				
		
	}
	
}
