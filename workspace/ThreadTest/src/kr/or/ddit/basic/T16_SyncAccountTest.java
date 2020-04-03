package kr.or.ddit.basic;
/**
 * 중요중요***복습
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * (synchronized 을 이용한 동기화 처리)
 * @author PC-16
 *
 */
public class T16_SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBalance(10000); // 입금처리
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
		
		
		/* 결과 설명 : 10000원에서 6000원 빼니까 결과가 처음엔 4000 인데, 2번시작하니 -2000도 나왔다.
		 * 우리는 마이너스값이 나올수없게해놓았는데. 
		 * withdraw에서 첫번째쓰레드가 먼저 진입 후 반복문을 
		 * 도는 중 두번째 쓰레드가 들어와서 마이너스값이 나온것임.
		 * 이걸 막기 위해 동기화처리를 해줘야한다.
		 * 이런 예외적인 상황이 많이 발생하니 멀티쓰레드 사용은 조심해야한다.
		 * 에러확인을 위해 똑같은 에러상황을 만들거나 하는것도 어렵다.
		 *  */
	}
}

// 은행의 입출금을 관리하는 클래스
class SyncAccount{
	private int balance; // 잔액이 저장될 변수
	
	public synchronized int getBalance() {
		return balance;
	}
	
	public synchronized void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금처리를 수행하는 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드(출금 성공 : true, 출금 실패 : false 반환)
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 한다.
	synchronized public boolean withdraw(int money) { // 메인메서드아래 결과설명으로 앞에 synchronized 를 붙였다.
		if(balance >= money) { // 잔액이 많을 경우...
			for(int i = 1; i <=1000000000; i++) { } //시간 때우기 : critical section 만들기위해서 넣은 부분.
			balance -= money; // 인출부분
			System.out.println("메서드 안에서 balance = " + getBalance()); // 잔액보여주는 부분. 잘빠졌는지 확인용.
			
			return true;
		} else {
			return false;
		}
	}
}

// 은행 업무를 처리하는 쓰레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); // 6000원 연출
		System.out.println("쓰레드 안에서 result = " + result + ", balance = " + sAcc.getBalance());
	}
}