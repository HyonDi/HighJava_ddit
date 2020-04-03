package kr.or.ddit.basic;

/*
  <쓰레드 상태>
  NEW : 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태
  RUNNABLE : 실행 중 또는 실행 가능한 상태
  BLOCKED : 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴때까지 기다리는 상태)
  WAITING, TIMED_WAITING : 쓰레드의 작업이 종료되지는 않았지만 실행가능 하지 않은(UNRUNNABLE)일시정지 상태
  TERMINATED : 쓰레드의 작업이 종료된 상태
 */


/**
 * 쓰레드의 상태를 출력하는 예제
 * @author PC-16
 *
 */
public class T10_ThreadStateTest {
	public static void main(String[] args) {
		StatePrintThread spt = new StatePrintThread(new TargetThread());//TargetThread() 의 상태를 보려고 하는 중.
		
		spt.start();
	}
}

/**
 * 쓰레드의 상태를 출력하는 클래스(이 클래스도 쓰레드로 작성) 
 * @author PC-16
 *
 */
class StatePrintThread extends Thread {
	private Thread targetThread; // 상태를 출력할 쓰레드가 저장될 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			// Thread 상태 구하기(getState()메서드 이용)
			Thread.State state = targetThread.getState(); // Thread.State = 이늄타입.
			System.out.println("타겟 쓰레드의 상태값 : " + state);
			
			// NEW 상태인지검사
			if(state == Thread.State.NEW) {
				targetThread.start(); //쓰레드 시작
			}
			// 타겟쓰레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * Target용 쓰레드
 * @author PC-16
 *
 */
class TargetThread extends Thread {
	@Override
	public void run() {
		for(long i=1; i <=1000000000L; i++) {} //시간 지연용
		try {
			Thread.sleep(1500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		for(long i=1; i <=1000000000L; i++) {}
	}
}

