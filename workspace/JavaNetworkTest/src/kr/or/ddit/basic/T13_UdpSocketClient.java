package kr.or.ddit.basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class T13_UdpSocketClient {
	public void start() throws IOException {
		// 소켓객체 생성
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddr = InetAddress.getByName("localhost");
		
		// 데이터가 저장될 공간으로 byte배열을 생성한다.(패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddr, 8888); //보낼때는 1바이트를 서버에 대충보냄.
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length); // 받을때는 100바이트팩킷에다 서버날짜를 받을것이다.
		
		datagramSocket.send(outPacket); // DatagramPacket을 전송한다.
		datagramSocket.receive(inPacket); // DatagramPacket을 수신한다.
		
		System.out.println("현재 서버 시간 => " + new String(inPacket.getData()));// 바이트배열을 파라미터로 넣어주면 String 문자열로 바꿔준다.
		
		datagramSocket.close(); // 소켓 종료
	}
	
	public static void main(String[] args) throws IOException {
		new T13_UdpSocketClient().start();
	}
}
