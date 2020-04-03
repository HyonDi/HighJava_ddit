package kr.or.ddit.basic;
/**
 * T19 랑 비슷한데 기능을 구분해놓음?
 * @author PC-16
 *
 */
public class T20_WaitNotifyTset {
	public static void main(String[] args) {
		
		// 공유객체임.
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}


/**
 * 데이터를 공통으로 사용하는 클래스
 */
class DataBox {
	private String data;
	
	// data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				wait(); // wait 하는 이유 : null이면 누군가 데이터 세팅할때까지 기다리란의미.
						// null 아니면 데이터를 가져올거임.
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null; // 데이터를 꺼내왔다는 의미에서 null로 세팅해줌. 
		System.out.println(Thread.currentThread().getName() + " notify() 호출.");
		
		notify(); // 내가 가져갔으니까 세팅할사람 하라는 의미에서.
		
		return returnData;
	}
	
	// data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.data = data; // 공유객체에다가 세팅.
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " notify() 호출.");
		notify(); // 혹시 못가져간 데이터가 있을까봐 가져가는애 한 번 깨워준다.
	}
}


/**
 * 데이터를 셋팅만 하는 쓰레드. 
 */
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) { 
		super("ProducerThread"); // super에는 쓰레드클레스이름을 세팅할수있음.
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			String data = "Data-" + i;
			System.out.println("dataBox.setData(" + data + ") 호출");
			
			dataBox.setData(data);
		}
	}
}

/**
 * 데이터를 읽어만 오는 쓰레드
 */
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i ++) {
			String data = dataBox.getData();
			System.out.println("data.getData() : " + data); //프로듀서가만든거 가져옴???
		}
	}
}
// wait이 없으면 락반납이 안되니까 락이 영원히 끝나지않음.
// notifyAll 해도 됨.
// 생산자가 여럿, 소비자도 여럿일경우 All 사용해야해.
// notify 는 랜덤으로깨운다. 지정불가능.
// 생산자는 소비자만깨워야하고 소비자는 생산자만 깨워야함!
// All 쓰는게 낫겠다. 안좋은상황방지.


