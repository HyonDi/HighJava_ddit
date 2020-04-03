package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class T08_MultiChatServer {
	// 대화명, 클라이언트 Socket을  저장하기 위한 Map변수 선언
	// 소켓이 대화 참여자 수 만큼 필요함.
	// 닉네임으로 찾을 수 있도록 맵 사용함.
	Map<String, Socket> clients;
	
	// 생성자
	public T08_MultiChatServer() {
		// 동기화 처리가 가능하도록 Map객체 생성. 늘리는 중 참여시 발생하는 예외를 위해 동기화처리함.
		clients = 
			Collections.synchronizedMap(new HashMap<>());
	}
	
	// 비지니스 로직을 처리하는 메서드
	public void serverStart() { // 메인쓰레드는 참여에만 관여해 계속 소켓을 만들고 대기하고 무한반복함.
		ServerSocket serverSocket = null; // 서버소켓.
		Socket socket = null; // 클라이언트와 연결시 만들어질 소켓.
		
		try {
			serverSocket = new ServerSocket(7777); // 1. 여기가 서버소켓!
			System.out.println("서버가 시작되었습니다.");
			
			while(true) { // 멀티챗은 무한반복이 추가됨.
				// 클라이언트의 접속을 대기한다.
				socket = serverSocket.accept(); // 2. 기다리는 코드. accept. 
				
				System.out.println("[" + socket.getInetAddress() 
								+ " : " + socket.getPort()
								+ "에서 접속하였습니다.");
				
				// 메시지 전송 처리를 하는 쓰레드 생성 및 실행
				ServerReceiver thread = new ServerReceiver(socket); // 사람당 하나의 쓰레드도 만든다. 서버리시버는 내부클래스로. 
				thread.start();						//실질적 로직은 서버리시버에 들어있을 것임.(메인쓰레드는 참여에만 관여하니까.)
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 서버 소켓 닫기
			if(serverSocket != null) {
				try {serverSocket.close();} catch(IOException e) {}
			}
		}
	}
	
	// 대화방 즉, Map에 저장된 전체 유저에게 메시지를 전송하는 메서드
	public void sendToAll(String msg) {
		// Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator(); // clients : 사용자들을 담고있는 맵.
		while(it.hasNext()) { // 맵갯수만큼 돌것임.
			try {
				String name = it.next(); // 대화명(key값) 구하기
				
				// 대화명에 해당하는 Socket의 OutputStream객체 구하기
				DataOutputStream dos = 
						new DataOutputStream(
								clients.get(name) // 대화명에 매핑되는 벨류 :소켓.
								.getOutputStream()); 
				dos.writeUTF(msg); // 메시지 보내기
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 서버에서 클라이언트로 메시지를 전송할 Thread를 Inner클래스로 정의
	// Inner클래스에서는 부모 클래스의 멤버변수들을 직접 사용할 수 있다.
	class ServerReceiver extends Thread { //내부클래스로 선언이유 : 여기(T08_MultiChatServer)서만 사용하기위함.
		Socket socket;
		DataInputStream dis; // 문자열 처리위한 보조스트림.-ReadUTF, WriteUTF 두가지 메서드사용하기위함.
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 수신용
				dis = new DataInputStream( //객체!
						socket.getInputStream()); // 기반스트림을 소켓에서 가져왔음.
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				// 이 클래스(ServerReceiver)에서 제일먼저시작될부분!
				
				// 서버에서는 클라이언트가 보내는 최초의 메시지 즉, 대화명을
				// 수신해야 한다.
				name = dis.readUTF();
				
				// 대화명을 받아서 다른 모든 클라이언트에게 대화방
				// 참여 메시지를 보낸다.
				sendToAll("#" + name + "님이 입장했습니다.");
				
				// 대화명과 소켓정보를 Map에 저장한다.
				clients.put(name, socket);
				System.out.println("현재 서버 접속자 수는 " + 
								clients.size() + "명 입니다.");
				
				// 이 후의 메시지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 
				// 보내준다.
				while(dis!=null) {
					sendToAll(dis.readUTF());//보내는게 있을 때 마다 SendToAll 로 보낸다.
				}
			}catch(IOException e) {
				
			}finally {
				// 이 finally영역이 실행되었다는 것은 클라이언트의 접속이
				// 종료되었다는 것을 의미한다.
				sendToAll(name + "님이 나가셨습니다.");
				
				// Map에서 해당 대화명을 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() 
								+ " : " + socket.getPort() 
								+ "]에서 접속을 종료 했습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() 
								+ "명 입니다.");
			}
		}
	}
	
	public static void main(String[] args) {
		new T08_MultiChatServer().serverStart();
	}
}
