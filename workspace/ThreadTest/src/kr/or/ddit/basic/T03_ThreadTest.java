package kr.or.ddit.basic;

public class T03_ThreadTest {
	public static void main(String[] args) {
		// 쓰레드의 수행시간 체크해 보기.
		Thread th = new Thread(new MyRunner());
		
		// 1970년 1월 1일 0시 0분 0초(표준시) 로부터 경과한 시간을
		// 밀리세컨드 (1/1000초) 단위로 나타낸다.
		long startTime = System.currentTimeMillis();
		
		th.start(); //쓰레드 작업 시작.(메인쓰레드가시작시키고 MyRunner쓰레드가 시작된다.)
		
		try {
			th.join(); // 현재 실행중인 쓰레드에서 작업중인 쓰레드(지금은 th 쓰레드를 의미)
						// 가 종료될때 까지 기다린다.
						// 다른쓰레드를 기다리는 역할.
						// th.join() 으로 해서 th가 끝날때까지 기다리는건가봐
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + ( endTime - startTime));
		System.out.println("endTime : " + endTime);
		System.out.println("startTime : " + startTime);
	}
}

/**
 * 1~1000000000(동그라미9개.10억) 까지의 합계를 구하는 쓰레드.
 * @author PC-16
 *
 */
class MyRunner implements Runnable {
	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i <= 1000000000; i ++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}