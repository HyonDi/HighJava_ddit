package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		// 우리는 여태 기본생성되어있는 메인쓰레드만 써왔음.
		
		// 쓰레드객체에 항상 필요한 로직들은 run이란 메서드에 들어간다. 쓰레드의 진입점이 run이니까.
		// 자바프로그램은 main메서드가 진입점인것처럼.
		
		
		// 멀티쓰레드 프로그램 방식
		
		// 방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start() 메서드를 호출한다.
		
		// 메인쓰레드는 메인쓰레드대로 가고, th1은 얘대로 run부분을 실행하기시작한다. 이렇게 분리되어 실행됨.
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		// 방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로
		// 넘겨준다. 이 때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		th2.start();
		
		// 방법3 : 익명클래스를 이용하는 방법
		// Runnable인 인터페이스를 구현한 익명클래스를 Thread인스턴스를
		// 생성할 때 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					
					try {
						// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다.
						// 즉, 1000은 1초를 의미한다.
						Thread.sleep(100); /*0.1 초 잠자는 내용*/
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		th3.run();
		/*th3.start();*/
		// 왜 start()를 호출할까요? 
		// 전체 run()을 호출하면 single thread 처럼 움직인다.
		// 별도의 스택을 만들지 않고 메인쓰레드가 저 run 메서드를 실행하는것.
		// start()가 결국 별도의 스택만드는 역할.
		
	}
}



class MyThread1 extends Thread{
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100); /*0.1 초 잠자는 내용*/
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100); /*0.1 초 잠자는 내용*/
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/*메인>쓰레드1>쓰레드2순으로 시작되었다가 메인>쓰레드1>쓰레드2 순으로 죽는다.*/
/*0.1초 간격으로 200번을 쓰레드1, 2가 분리되어 각각 *혹은 $을 찍다가 죽는다.*/

