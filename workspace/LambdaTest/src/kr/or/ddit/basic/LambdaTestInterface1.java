package kr.or.ddit.basic;
/*
 	함수적 인터페이스 => 추상메서드가 1개만 선언된 인터페이스.
 	
 */

@FunctionalInterface // 어노테이션! 
public interface LambdaTestInterface1 {
	// 반환값도 없고 매개변수도 없는 추상메서드 선언
	public void test();
	
}

@FunctionalInterface
interface LambdaTestInterface2 {
	// 반환값도 없고 매개변수만 있는 추상메서드 선언
	public void test(int a);
	
}

@FunctionalInterface
interface LambdaTestInterface3 {
	// 반환값도 있고 매개변수도 있는 추상메서드 선언
	public int test(int a, int b);
	
}


//@FunctionalInterface 이거는 안써도 되긴하는데 써놓으면 함수적인터페이스를 만드려면 인터페이스가 2개인것을 걸러준더.
//  => 어노테이션