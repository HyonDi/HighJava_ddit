package kr.or.ddit.basic;
/**
 	Thread 의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서
 	나중에 실행되는 프로그램에 영향을 줄 수 있다.
 	그래서 현재는 stop()메서드는 비추천(Deprecated)으로 되어있다.
 	종료하는거!
 */
public class T13_ThreadStopTest {
	public static void main(String[] args) {
		
		ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
//		th.stop();
		th.setStop(true);
//		stop 은 자원정리없이 강제종료.
//		setStop 은 자원정리하고 안전하게 종료.
		
//		interrupt() 메서드를 이용한 쓰레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		th2.interrupt(); //인터럽트 걸기
	}

}

/**
 * 상태 플래그를 활용한 쓰레드 멏귀
 * @author PC-16
 *
 */
class ThreadStopEx1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop ) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 처리 중.....");
		}
		System.out.println("자원 정리 중......");
		System.out.println("실행 종료..");
	}
	
}

/**
 * interrupt() 메서드를 이용하여 쓰레드를 멈추게 하는 방법
 * @author PC-16
 *
 */
class ThreadStopEx2 extends Thread {
	@Override
	public void run() {
		// 방법 1 => sleep()메서드나 join()메서드 등을 사용했을 때 interrupt() 
		// 			메서드를 호출하면 InterruptedException 이 발생한다.
		/*try {
			while(true) {
				System.out.println("쓰레드 처리 중...");
				Thread.sleep(1);
//				이 sleep 이 있어야 interrupt 가 발생한다.
//				sleep, join 에만 interrupt 가 걸림. (인터럽트가 걸리는 환경. runnable 에는 못건다.)
				
				
			}
		} catch(InterruptedException e) { }*/
//		try catch 로 감싸놓았다.
//		메인스레드-ThreadStopEx2스레드 사이의 일. 
		
//		방법 2 => interrupt()메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("쓰레드 처리중...");
			
			// 검사 방법1 => 쓰레드의 인스턴스용 메서드를 이용하는 방법
			if(this.isInterrupted()) {//interrup메서드가 호출되면 true
				System.out.println("인스턴스용 isInterrupted()");
				break;
			}
			
			// 검사 방법 2 => 쓰레드의 정적 메서드를 이용하는 방법
			/*if(Thread.interrupted()) {//interrupt()메서드 호출외면true
				System.out.println("정적 메서드 interrupted()");
				break;
				
			}*/
			
		}
		System.out.println("자원 정리 중...");
		System.out.println("실횅 종료.");
		
	}
}