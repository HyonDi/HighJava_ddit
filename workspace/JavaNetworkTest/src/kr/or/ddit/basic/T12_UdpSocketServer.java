package kr.or.ddit.basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * socket 이랑 packet 이 쌍으로 같이 다닌다.
 * packet이 그릇역할.
 * 
 * udp는 서버 클라이언트의 역할이 애매함.
 * 
 * */

public class T12_UdpSocketServer {
	private DatagramSocket socket;
	
	public void start() throws IOException {
		socket = new DatagramSocket(8888); //  포트번호 8888번을 사용하는 소켓 생성.
		DatagramPacket inPacket, outPacket; // 패킷 송수신을 위한 객체변수. 받을그릇, 보낼그릇
		
		byte[] inMsg = new byte[1]; // 패킷 수신을 위한 바이트 배열 선언. 1바이트로 만들었다!
		byte[] outMsg;				// 패킷 송신을 위한 바이트 배열 선언
		
		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length); // DatagramPacket에 byte배열을 파라미터로 넣어주어야한다.
			
			System.out.println("패킷 수신 대기중..."); //상대가 보낼때 까지 계속 block돼있음.
			
			// 패킷을 통해 데이터를 수신(receive) 한다. 
			socket.receive(inPacket); // 1바이트뿐 못받음. 얘는 보낸사람의 ip와 port를 알기위한것 뿐이어서 1바이트로한것.
			
			System.out.println("패킷 수신 완료."); 
			
			// 수신한 패킷으로부터 client의 IP주소와 Port를 얻는다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 서버의 현재시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date()); // 서버의 현재시간 
			outMsg = time.getBytes(); // 시간문자열을 byte배열로 변환. 
										//String안에 getBytes 라는 메서드가 들어있어서 byte배열로 변환할 수 있다.
			
			
			// 패킷을 생성해서 client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port); 
			// 서버의 시간데이터를 상대방에게 보내주고있다.
			// 상대방이 받든 말든 보냄.
			
			socket.send(outPacket); // 전송 시작
		}
	}
	public static void main(String[] args) {
		try { 
			new T12_UdpSocketServer().start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}








