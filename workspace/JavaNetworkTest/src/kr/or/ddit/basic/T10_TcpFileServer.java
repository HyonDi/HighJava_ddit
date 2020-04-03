package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class T10_TcpFileServer {
	// 서버는 클라이언트가 접속하면 서버컴퓨터는 D드라이브의 D_Other폴더에 있는 Tulips.jpg
	// 파일을 클라이언트로 전송한다.
	
	// 만든 소켓에 서버는 클라이언트에게 튤립이미지파일을 바로 넣어주고 클라이언트는 이미지를 받도록 처리할것이다.
	
	private ServerSocket server;
	private Socket socket;
	private OutputStream out;
	private FileInputStream fis;
	
	public void serverStart() {
		File file = new File("d:/D_Other/Tulips.jpg");
		
		try {
			server = new ServerSocket(7777); // 이게 서버소켓
			System.out.println("서버 준비 완료...");
			
			socket = server.accept(); // 대기하기위한 accept
			System.out.println("파일 전송 시작...");
			
			fis = new FileInputStream(file); 
			out = socket.getOutputStream();
			
			 // 한꺼번에 읽어와 전송할 데이터를 저장할 변수 선언.
			byte[] temp = new byte[1024];
			int length = 0;
			while((length = fis.read(temp)) != -1) {
				out.write(temp, 0, length); 
				// (bytearray inputStream)에서 temp가 있으면 버퍼역할을해서 한꺼번에 읽기 편하긴한데, 
				// 사이즈가 맞지않으면 남아있는 데이터가 있을 수 있음.
			}
			out.flush(); //  혹시 버퍼에 남아있을수도있는것까지 싹 내보낸다.
			System.out.println("파일 전송 완료...");
		}catch(IOException e) {
			
		} finally {
			if(fis != null) {
				try {fis.close();}catch(IOException e) { }
			}
			if(out != null) {
				try {out.close();}catch(IOException e) { }
			}
			if(socket != null) {
				try {socket.close();}catch(IOException e) { }
			}
			if(server != null) {
				try {server.close();}catch(IOException e) { }
			}
		}
	}
	
	public static void main(String[] args) {
		new T10_TcpFileServer().serverStart();
	}
}
