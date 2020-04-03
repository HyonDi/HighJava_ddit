package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number/*, TT extends Number*/> int compare(T t1, T t2) {//extends Number 가 있어서 제한된 타입 파라미터다. 없으면 제너릭메서드. extends 를 사용하는게 이 문법이야.
		double v1 = t1.doubleValue();							//넘버를 익스텐즈했음. 넘버와 넘버자식들이 올 수 있음. ex)AtomicInteger, AtomicLong, BigDecimal, BigInteger, Byte, Double, DoubleAccumulator, DoubleAdder, Float, Integer, Long, LongAccumulator, LongAdder, Short
		double v2 = t2.doubleValue();							//여러개 받을 수도 있음.
		
		return Double.compare(v1, v2);
	}
}


/* 제한된 타입 파라미터 (Bounded Parameter) 예제 */
//타입파라미터는 타입 1개를 말하지만, 제한된타입파라미터는 어디부터 어디까지로 범위가 있어서 더 여러가지가 들어올 수 있다.

public class T05_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20); //AutoBoxing(Integer)
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);// AutoBoxing(Double)
		System.out.println(result2);
		
		//Util2.compare("C", "JAVA"); /에러발생
	}
}
