package kr.or.ddit.basic;

public class T02_ArgsTest {
/*	
	가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다.
			- 가변형 인수는 메서드 안에서 배열로 처리된다.
			- 가변형 인수는 한가지 자료형만 사용할 수 있다.
			
	인수 : argument.
	args : argument 의 복수형.
	
	받을거는 파라미터고 , 입력할 값은 매개변수(argument)임.
	
	
*/			

//	...data 가 가변형인수인거지??
	
/*	배열을 이용한 메서드
	매개변수로 받은 정수들의 합계를 구하는 메서드(이 정수들의 개수는 상황에 따라 다르다.)
*/
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i = 0; i < data.length; i ++) {
			sum += data[i];
		}
		return sum;
	}
	
	
	// 가변형 인수를 이용한 메서드
	public int sumArg(int ...data) { //가변형 인수의 문법! (int형 타입 몇개들어올지 몰라(...)data)
		int sum = 0;
		for(int i = 0; i < data.length; i ++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	//컴파일러가 읽을때에 가변형인수가 맨 앞에오면 몇개인지 모르니까 컴파일할수가 없어. 맨뒤에와야해.
	public String sumArg2(String name, int ...data) {
		int sum = 0;
		for(int i = 0; i < data.length; i++ ) {
			sum += data[i];
		}
		return name + "씨 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		T02_ArgsTest at = new T02_ArgsTest();
		
		int[] nums = {100, 200, 300};
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1, 2, 3, 4, 5,6, 7, 8, 9}));
		System.out.println();
		
		System.out.println(at.sumArg(100, 200, 300));
		System.out.println(at.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(at.sumArg2("홍길동", 1,2,3,4,5,6,7,8));
	}
}
