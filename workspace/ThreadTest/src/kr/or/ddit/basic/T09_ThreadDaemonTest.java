package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬쓰레드로 설정하기 : start()메서드를 호출하기 전에 설정한다.
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업" + i);
				Thread.sleep(1000);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료...");
		
		// autoSave 는 무한루프인데 메인쓰레드 종료되면 자결한다.
	}
}

/**
 * 자동 저장하는 스레드
 * @author PC-16
 *
 */
class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장 기능 호출
		}
		
	}
}