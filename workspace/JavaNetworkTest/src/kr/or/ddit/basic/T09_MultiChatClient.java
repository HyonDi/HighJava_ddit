package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T09_MultiChatClient {
	private String nickName; // 대화명
	
	// 비지니스 로직 시작
	public void clientStart() {
		Scanner scan = new Scanner(System.in);
		System.out.print("대화명 : ");
		nickName = scan.next();
		
		scan.close();
		
		Socket socket = null;
		
		try {
			String serverIp = "192.168.205.10";
			socket = new Socket(serverIp, 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
// 클라이언트센더/리시버 만들고 맨마지막에 추가시켰음.
			
			// 송신용 쓰레드 생성
			ClientSender clientSender = new ClientSender(socket, nickName);
			
			// 수신용 쓰레드 생성
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			
			// 실행
			clientSender.start();
			clientReceiver.start();
//
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 서버(reciever thread 만 있으면 돼.)와 다르게 보내고 받고하는 두가지 thread가 모두 필요하다.
	class ClientSender extends Thread{
		Socket socket;
		DataOutputStream dos;
		String name;// 대화명
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.name = name;
			this.socket = socket;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			}catch(IOException e) { }
		}
		
		@Override
		public void run() {
			try {
				// 시작하자마자 자신의 대화명을 서버로 전송
				if(dos != null) {
					dos.writeUTF(name);
				}
				
				while(dos != null) {
					// 키보드로부터 입력받은 메세지를 서버로 전송
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}
			} catch (IOException e) {}
		}
	} // 송신용 쓰레드 끝...
	
	// 수신용 Thread 클래스 정의
	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream dis;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {}
		}
		
		// 런
		@Override
		public void run() {
			while(dis != null) {
				try {
					// 서버로부터 수신한 메시지 출력하기
					System.out.println(dis.readUTF());
				}catch(IOException e) {e.printStackTrace();}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new T09_MultiChatClient().clientStart();
	}
	
	
}
