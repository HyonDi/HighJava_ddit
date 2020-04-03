package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 소켓에서 메시지를 받아서 화면에 출력하는 역할을 담당함.
 * @author PC-16
 *
 */
public class Receiver extends Thread{
	Socket socket;
	DataInputStream dis; // 문자열이니까.
	
	public Receiver(Socket socket) { //생성자
		this.socket = socket;
		
		try {
			dis = new DataInputStream(socket.getInputStream()); 
			// DataInputStream은 보조스트림이니까 소켓에서가져온 inputStream을 파라미터로 넣음.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		@Override
		public void run() {
			while(dis != null) {// 생성자로 객체를 만들었으니까 dis는 무조건 true임.
				try {
					System.out.println(dis.readUTF());
				}catch(Exception e) {
					 e.printStackTrace();
				}
			}
		}
	
}
