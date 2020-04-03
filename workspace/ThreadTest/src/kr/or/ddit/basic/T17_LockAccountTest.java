package kr.or.ddit.basic;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * 	(Lock을 이용한 동기화 처리)
 * @author PC-16
 *
 */
public class T17_LockAccountTest {
	public static void main(String[] args) {
		LockAccount lAcc = new LockAccount();
		lAcc.setBalance(10000); // 입금 처리
		
		BankThread2 th1 = new BankThread2(lAcc);
		BankThread2 th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
		
	}
}


// 입출금을 담당하는 클래스
class LockAccount {
	 private int balance; // 잔액이 저장될 변수
	 
	 // lock 객체 생성 -> 되도록이면 private final 로 만든다. : 왜?? final : 값을 변경하지말아라. private : 다른클래스에서 쓰지 못해야함. 범위가장 좁게.
	 private final ReentrantLock lock = new ReentrantLock();

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드
	public void deposit(int money) {
		// Lock객체의 lock()메서드가 동기화 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		// Lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제해 주어야 한다.
		lock.lock(); // 동기화 시작
		balance += money; // 동기화 처리 부분
		lock.unlock(); // 해제
	}
	
	// 출금하는 메서드(출금 성공 : true, 출금 실패 : false)
	public boolean withdraw(int money) {
		lock.lock(); // 동기화 시작
		boolean chk = false;
		// try ~ catch 블럭을 사용할 경우에는 unlock()메서드 호출은 finally 블럭에서 하도록 한다.
		// 안그러면 접근도못하고 영원히 잠김.
		try {
			if(balance >= money) {
				for(int i = 1; i <= 1000000000; i++) {} // 시간 때우기
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				
				chk = true;
			}
		} catch(Exception e) {
			chk = false;
		}finally {
			lock.unlock(); // 동기화 해제
		}
		
		return chk;
	}
}

// 은행업무를 처리하는 쓰레드
class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println("쓰레드 안에서 result = " + result + ", balance = " + lAcc.getBalance());
	}
}