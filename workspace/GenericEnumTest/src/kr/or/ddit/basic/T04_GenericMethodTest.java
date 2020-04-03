package kr.or.ddit.basic;

/*
	제너릭메서드<T, R> R method(T t)
	
	파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드
	
	선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용함.
*/	

class Util{ //제너릭메서드가 되려면 ? compare 리턴타입 : 불린 앞에 <K, V>가 있음.
	//K, V 는 사용시(컴파일시)에만 명확히 타입을 알려주면 된다.
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) { //두개의 객체를 받는다.
												//페어 제너릭클래스구나.
		boolean keyCompare = p1.getKey().equals(p2.getKey()); // 그 객체의 키값 비교
		boolean valueCompare = p1.getValue().equals(p2.getValue()); // 벨류값 비교
		
		return keyCompare && valueCompare; // 키과 벨류 모두 일치할경우에만 true 가 나온다.
	}
}
/*
 * 멀티타입 < K, V>을 가지는 제너릭 클래스
 * @param <K>
 * @param <V>
 * --> 이게 무슨뜻이야??
 * */
class Pair<K, V> {//멀티타입갖는 멀티클래스.
	private K key;
	private V value;
	//지금은 K,V 가 무슨타입인지 모른다.
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	
	
}


public class T04_GenericMethodTest {
	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		//값은 동일하지만 다른 객체.
		
		//구체적 타입을 명시적으로 지정(생략가능)
		boolean result1 = Util.<Integer, String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		}else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
		Pair<String, String> p3 = new Pair<>("001", "홍길동");
		Pair<String, String> p4 = new Pair<>("002", "홍길동");
		
		boolean result2 = Util./*<String, String>*/compare(p3, p4);//생략가능
		
		if(result2) {
			System.out.println("논리(의미)적으로 동일한 객체임.");
		} else {
			System.out.println("논리(의미)적으로 동일한 객체 아님.");
		}
		
	}
}
