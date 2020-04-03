package thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import vo.ClientVO;

public class ChatServerRunnable implements Runnable{

	//UDP연결을 담당할 소켓
	private DatagramSocket socket = null;
	//클라이언트의 ip주소 리스트 - MainController의 clientList와 같은 주소를 바라보고 있음
	private ObservableList<String> clientList; // 사용자목록. Listview에 뿌려줄용.
	private Map<String, ClientVO> clientMap; // 정말중요한 진짜 사용자정보??
	//서버 실행 여부
	private boolean isServerOn = true;
	
	//생성자
	public ChatServerRunnable(ObservableList<String> clientList, Map<String, ClientVO> clientMap){
		// run부분이 중요함. 여긴별로하는게없다.
		try {
			//객체가 생성될 때 소켓을 7777포트로 초기화
			this.socket = new DatagramSocket(7777);
			
			//소켓의 타임아웃을 0.5초로 설정 - 클라이언트로부터 데이터가 들어오지 않은채로
			//0.5초가 지나면 타임아웃 익셉션을 발생시키고 넘어가게 됨(옵션이긴함.)
			this.socket.setSoTimeout(500);
			
			//MainController로부터 받은 클라이언트 정보 맵
			this.clientList = clientList;
			this.clientMap = clientMap;
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(isServerOn){ //true
			byte[] inMsg = new byte[100];// 사용자가 100바이트이상의메세지를 보내지않을것이라는 가정하에.
			DatagramPacket inPacket = new DatagramPacket(inMsg, inMsg.length);// 메세지 보낼때까지 대기. block.
			
			// 패킷을 만들었음.
			try {
				socket.receive(inPacket);
				final InetAddress address = inPacket.getAddress();
				final int port = inPacket.getPort();
				// 사용자의 ip와 port번호를 알아내는 부분.
				
				boolean isExist = clientList.contains(address.getHostName());
				// clientList(observableList)에 호스트네임이있는지 본다.
				
				if(!isExist){ // 목록에 존재하지 않는 사용자라면..
					Platform.runLater(new Runnable() { // Platform.runLater(new Runnable() : 자바fx만 ui를 건들수있게한다고??
						// 목록에 존재하지 않는 사용자를 등록해준다.
						
						
						@Override
						public void run() {
							//여기가 실질적데이터를 관리하는 부분. 위는 화면단에 보여주는역할함.
							clientList.add(address.getHostName()); // JavaFx Application 쓰레드 관련 작업임.
						}
					});
					
					ClientVO vo = new ClientVO(address.getHostAddress(), 
												port, 
												new String(inPacket.getData()).trim() // 대화명에서 불필요한 공백 제거.
												);
					clientMap.put(address.getHostName(), vo); // 키:호스트네임, 밸류:지금만든 vo / 맵에넣는다.
					
				}else{ // 목록에 존재하는 사용자인 경우...(이미 채팅중인 사용자인 경우...)
				
					System.out.println(new String(inPacket.getData())); // 보내온메세지를 받는다.(서버 콘솔에찍힐것임)
					
					Iterator<String> it = clientMap.keySet().iterator(); // 맵을 하나하나뒤짐.
					
					ClientVO senderVO =  clientMap.get(address.getHostName()); // VO객체를 꺼내온다. 상대에게보내려면 udp에서는 꼭필요.
					// 보낸사람정보 꺼내기.
					// tcp에서는 소켓만있으면 됐는데 udp에서는 아이피주소, 포트번호를 명시적으로알려줘야함.
					
					
					while(it.hasNext()) {
						//받을사람정보꺼내기
						String ipAddr = it.next();// 키값에 담겨있는 호스트네임?
						ClientVO vo = clientMap.get(ipAddr);// 키값을알면 vo를꺼내올수있다.
						InetAddress ipAddress = InetAddress.getByName(vo.getIpAddr());
						// 패킷에 상대방파라미터로넣은거 : 1.메세지바이트 2. 사이즈 3. 아이피 4. 포트번호 를 준비중임.
						DatagramPacket outPacket = null;
				// 버그로 추가한부분. 클라이언트가 앱종료시에 서버가 모른다. 클라이언트 포트번호는 랜덤하게 만들어짐.
						// 클라이언트가 종료했다가 다시접속시 중복이지만 포트번호는 새로랜덤하게만들어져서 메세지를 못받게될 수 있어서
						// 갱신하기위해추가된부분.
						if(address.getHostName().equals(vo.getIpAddr()) 
							&& (port != vo.getPortNum())) { // 아이피주소는 동일한데 포트번호가 다른경우...
							vo.setPortNum(port);
							clientMap.put(ipAddr, vo); // 기존 정보 갱신. 해쉬맵까서 넣음.
						}
				//
						String sendMsg = "[" + senderVO.getChatName() + "] " + new String(inPacket.getData()); // 사용자가보낸패킷을 스트링으로바꿈.
						
						System.out.println("메시지 : " + sendMsg);
						outPacket = new DatagramPacket(sendMsg.getBytes(), sendMsg.getBytes().length, ipAddress, vo.getPortNum());
						//1. 바이트배열 2. 사이즈 3.보낼아이피주소 4.포트번호 를 한놈한놈뿌림. 소켓이 tcp랑 비슷하지만 udp소켓은 해쉬맵으로 정보를 긁어와야함.
						
						socket.send(outPacket); // 접속한 클라이언트에게 메시지 전송
					}
				}
				
				//Thread.sleep(500); //이건왜??
			} catch(SocketTimeoutException e){	
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void turnOffServer(){
		isServerOn = false;
	}
}
