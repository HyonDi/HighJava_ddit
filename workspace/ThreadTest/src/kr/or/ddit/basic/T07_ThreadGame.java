package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고 
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력핟는다.
 * 
 * 입력시간은 5초로 제한하고 카운트다운을 진행한다.
 * 5초동안 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과 예시)
 * == 결과 ==
 * 컴퓨터 : 가위
 * 당    신: 바위
 * 결과 : 당신이 이겼습니다.
 * @author PC-16
 *
 */
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	static String str = null;
	
	public static void main(String[] args) {
		System.out.println("== 가위 바위보 게임 ==");
		
		Thread th1 = new DataInput2(); 
		Thread th2 = new CountDown2(); 
		
		th1.start();
		th2.start();
		
		int com = (int)(Math.random()*3);//0 : 가위, 1 : 바위, 2 : 보
		String comInput;
		if(com == 0) {
			comInput = "가위";
		}else if(com == 1) {
			comInput = "바위";
		}else {
			comInput = "보";
		}
		
		System.out.println("당   신 : " + str);
		System.out.println("컴퓨터  : " + comInput);
		if(comInput == str) {
			System.out.println("결과 : 비겼습니다.");
		}else if((comInput.equals("가위") && str.equals("보"))||(comInput.equals("바위") && str.equals("가위"))||(comInput.equals("보") && str.equals("바위"))){
			System.out.println("결과 : 당신이 졌습니다.");
		}else {
			System.out.println("결과 : 당신이 이겼습니다.");
		}
		
	}
}

class DataInput2 extends Thread {
	@Override
	public void run() {
		T07_ThreadGame.str = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력해주세요. >> ");
		
		T07_ThreadGame.inputCheck = true;
		
		System.out.println("입력한 값은 " + T07_ThreadGame.str + " 입니다. ");
	}
}

class CountDown2 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i --) {

			if(T07_ThreadGame.inputCheck) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("입력시간 초과로 졌습니다.");
		System.exit(0);
	}
}