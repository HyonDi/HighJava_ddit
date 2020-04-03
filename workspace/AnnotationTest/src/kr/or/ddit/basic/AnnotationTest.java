package kr.or.ddit.basic;

import java.lang.reflect.Method; //

public class AnnotationTest {
	public static void main(String[] args) {
		System.out.println("id = " + PrintAnnotation.id);
								//어노테이션도 하나의 타입으로 처리된다.
		
		//어느부분이 reflection이야??  getDeclaredMethods 랑 getDeclaredAnnotation 이 리플렉션이야???
		
		// 리플렉션은 어노테이션정보를 가져다가 쓴다는 점에서 프레임웍이라고 생각하면 된다.
		
		
		// reflection 기능을 이용한 메서드 실행
		// reflection 으로 어노테이션에 접근한다.
		Method[] declaredMethods = Service.class.getDeclaredMethods(); //클래스정보를 가져옴.??
		
		/*Class<Service> klazz = Service.class;
		klazz.getDeclaredMethods();
		이거를 위 한줄로 줄인것.
		*/
		
		
		for(Method m : declaredMethods) { // 얘는 3번돌거야. 메서드 3개 정의해놨기때문.
			System.out.println(m.getName()); // 메서드명 출력;
			
			for(int i = 0; i< m.getDeclaredAnnotation(PrintAnnotation.class).count(); i ++) { 
										// printAnnotation 의 count값 만큼
				System.out.print(m.getDeclaredAnnotation(PrintAnnotation.class).value());	
							//m : method 라는 클래스 의미.   	// printAnnotation의 value값 출력
			}
			
			System.out.println();
			Class<Service> clazz = Service.class; // 실행할때에는 클래스정보를 가져와야함.
			try {
				Service service = (Service) clazz.newInstance(); // 객체생성. 리플렉션으로 클래스정보를 가져와 객체생성을하고 서비스 변수에 담음.
				m.invoke(service); // 메서드.invoke(객체); = 메서드는 아까정의해둔 3개. 
									// invoke에 파라미터로 저 메서드를 가지고있는 클래스의 객체를 넣어야한다. 파라미터있으면 콤마하고 파라미터도 넣어야해.
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		  	Java Reflection에 대하여...
		  	
		  	1. 리플렉션은 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
		  	2.  Reflection API는 java.lang.reflection 패키지와 java.lang.Class 를 통해 제공된다.
		  	3. Java.lang.Class의 주요 메서드
		  		- getName(), getSuperclass(), getInterfaces(), getModifiers()
		  		
		  	4. java.lang.reflect의 패키지의 주요 클래스
		  		- Field, Method, Constructor, Modifier 등.
		  	5. Reflection API 를 이용하면 클래스의 private메서드나 변수에 접근 가능하다. (보안 위협)
		  	6. Reflection API의 기능은 뛰어나지만, 약간의 오버헤드가 발생한다.(느린 수행속도, 보안취약성, 권한문제 등)
		  							(NEW 해서 만드는것에 비해 reflection객체를 이용하는 것이 오버헤드가 발생한다.)
		  	 	그러므로, 가급적 마지막 수단으로 사용하도록 고려되어야 한다.
		  		  	
		 */
		
	}
}
