package kr.or.ddit.basic;
/*
 * wait 전에 notify가 먼저 있어도 되는건가? : wait-set에 아무것도 없어도 코드작성은 됨.
 * notify 는 대상을 지정할 수 없는것? : ㅇㅇ지정못함.
 * 근데 B는 왜 안끝나?? :10번씩 반복문 돌며 하나는 대기실에서 자고있고, 깨워줄사람이 없어서.
 * 살리고싶으면 wait에 시간을 넣어주면된다.wait(1000) 이런식으로. 이시간지나면 깨어남.
 * 
 * 실행마다 결과 달라지는 이유는? 여기만해도 A종료됐다가 B종료되고 함. : 스타트는 A가먼저지만 내부에서 스택을 만들고 하는 과정이 달라서.
 * 										먼저시작하는게 다름. 이메서드는 먼저시작한거만 끝나서 종료되는것도 다름.
 * 
 * 
 * 
 * 
 * 
 	wait() 메서드 => 동기화 영역(한명만 들어갈 수 있음) 에서 락을 풀고 
 					Wait-Set영역(공유객체별로 각각 존재(공유객체들은 모두 하나씩 갖고있음.))
 					(대기실. 대합실.) 으로 이동시킨다.
 					
 	notify() 또는 notifyAll() 메서드 => Wait-Set 영역에 있는 쓰레드를 깨워서
 	 				실행될 수 있도록  한다.
 	 				<notify()는 하나, notifyAll() 은 Wait-Set 에 있는 전부를 깨운다.>
 	=> wait()와 notify(), notifyAll() 메서드는 동기화 영역에서만 실행 할 수 있고, 
 		Object 클래스에서 제공하는 메서드 이다.
 		
 		* 둘다 동기화영역에서만 사용할 수 있음.
 */

/**
 * wait()과 notify()를 이용한 두 쓰레드가 번갈아가면서 
 * 한번씩 실행하는 예제
 * @author PC-16
 *
 */
public class T19_WaitNotifyTest {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

/**
 * 공통으로 사용할 객체
 */
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * WorkObject의 methodA() 메서드만 호출하는 쓰레드
 */
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i ++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료.");
	}
}


/**
 * WorkObject의 methodB() 메서드만 호출하는 쓰레드
 */
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i ++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료.");
	}
}

/*
  methodA()만 종료되고 terminated 가 뜨지않으니 B는 아직 살아있다.
  여기서 보려는 것 : 동기화처리 내역 안에서 효율적인 작업을 위해 wait()와 notify() 를 사용.
 */
 