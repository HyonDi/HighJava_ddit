package kr.or.ddit.basic;

/*
	 열거형 => 상수값들을 선언하는 방법
	 장점 : type safty, 상수값 바뀔 경우 기존의 소스 다시컴파일필요없음.
	
	static final int A = 0;
	static final int B = 1;
	static final int C = 2;
	static final int D = 3;
	
	enum eData {A, B, C, D};
	
	열거형 선언하는 방법 (클래스와 비슷하다. 선언도 비슷하고 이늄자체가 클래스. 타입이야. 상수값들이 얘의 객체야.상수값들의 타입이 전부 '열거형 이름 '타입)
	enum 열거형 이름 { 상수값1, 상수값2,..., 상수값n};
*/

public class T03_EnumTest {
	// City 열거형 객체 선언(기본값을 이용하는 열거형)
	public enum City {서울, 부산, 대구, 광주, 대전};
	//서울, 부산, 대구 등의 상수값들은 이늄타입인 City 타입이야. City의 객체.
	
	
	// 데이터 값을 임의로 지정한 열거형 객체 선언
	// 데이터 값을 정해줄 경우에는 생성자를 만들어서 괄호속의 값이 변수에 저장되도록 해야한다.
	public enum Season{
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		//괄호안은 객체생성시 생성자가 실행
		// City와 다른점 : 괄호안에 데이터를 넣어놨다.
		// 봄, 여름, 가을, 겨울은? 상수.
		// 괄호 속내용은?
		
		
	// 괄호속의 값이 저장될 변수 선언
	private String str;
	
	//생성자 만들기(열거형의 생성자는 제어자가 묵시적으로 'private'이다.**외부에서 호출하지 못한다는 뜻.)
	//생성자 재정의
	private Season(String data) {
		str = data;
	}
	//괄호속 내용이 data로 넘어올것이야. 이걸 str에 저장.
	
	
	// 값을 반환하는 메서드 작성
	public String getStr() {
		return str;
		}
	}//enum Season 끝

	public static void main(String[] args) {
/*		
		열거형에서 사용되는 메서드
		1. name() => 열거형 상수의 이름을 문자열로 반환한다.
		2. ordinal() => 열거형 상수가 정의된 순서값을 반환한다.( 기본적으로 0부터 시작)
		3. valueOf("열거형 상수 이름"); => 지정된 열거형에서 '열거형상수 이름' 과 일치하는 열거형 상수를 반환한다.
*/		
		City myCity1; //열거형 객체변수 선언
		City myCity2;
		
		// 열거형 객체변수에 값 저장하기
		myCity2 = City.서울; // 시티타입의 서울이라는 객체가 마이시티2에 담겼다.
		myCity1 = City.valueOf("서울"); //City enum 에서 '서울'인 객체반환
		//위 두개는 같음.
		
		System.out.println("myCity1 : " + myCity1.name()); //해당 이늄상수의 이름을 문자열로 가져온것.
		System.out.println("myCity1 의 ordinal : " + myCity1.ordinal()); //ordinal : 인덱스 0번인것과 비슷.
		System.out.println();
		
		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2 의 ordinal : " + myCity2.ordinal());
		System.out.println("=====================================================");
		
		Season ss = Season.valueOf("여름");
		System.out.println("name => " + ss.name()); //ss.name() = "여름"
		System.out.println("ordinal => " + ss.ordinal()); //ss.ordinal() = 1 //인덱스와비슷
		System.out.println("get메서드 => " + ss.getStr()); //괄호속에 있던거.
		System.out.println("------------------------------------------------------");
		
		// 열거형 이름. values() => 데이터를 배열로 가져온다.
		Season[] enumArr = Season.values(); //배열enumArr에 시즌의 객체 4개(봄여름가을겨울)이 들어있다.
		for(int i = 0; i < enumArr.length; i ++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getStr());
		}
		System.out.println();
		
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}
		
		//------------------------------------------------------------------------------
		City city = City.대구;
		
		System.out.println(city == City.대전);
		System.out.println(city == City.대구);
//		System.out.println(city == Season.가을);
		//타입이다르면 연산불가.
		
		System.out.println("대구 => " + city.compareTo(City.대구));
		System.out.println("서울 => " + city.compareTo(City.서울));
		System.out.println("대전 => " + city.compareTo(City.대전));
		// ordinal 값 비교.
		
	}
	
}