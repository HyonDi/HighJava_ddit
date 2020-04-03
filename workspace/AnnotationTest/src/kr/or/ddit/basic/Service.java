package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation() // 지운다고 에러가 생기지않음. 주석처럼 달아놓음 = 어노테이션.
	public void method1() { //얘는 파라미터에 아무것도안넣어서 디폴트로 "-" 가 들어갈것임.
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation("%") // 타입요소값을 안썼음. (value= 을 안씀) 무조건 타입요소이름이 value일때만!! 생략 가능. 다른거면 안돼.
							// value="%"를 써도 돼. 모르면 타입요소이름(value, count) 그냥 써라.
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=25) // 여기는 value ="" 하고 넣었지만 요소 2개가 들어갈때는 꼭 명시적으로 알려줘야해.
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
