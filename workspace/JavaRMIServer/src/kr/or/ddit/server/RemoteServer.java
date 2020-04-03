package kr.or.ddit.server;

//kr.or.ddit.inf 랑 kr.or.ddit.vo 를 client 에 복사붙여넣기함. 똑같아야해.
// 클라이언트가 리모컨임.


import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.inf.RemoteInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

/*
 	RMI 용 서비스를 제공하는 객체는 RMI용 인터페이스를 구현하고,
 	 UnicastRemoteObject객체를 상속해서 작성한다.
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{
	// 이 클래스(RemoteServer) 를 클라이언트가 자기거처럼 사용할것이다. 얘가 원격객체. 

	protected RemoteServer() throws RemoteException {// 생성자에서 remoteException 를 throws 하도록 해야한다.
		// 디폴트생성자.
		// RMI용 인터페이스를 구현한 객체의 생성자도 RemoteException을 throws 하도록 작성한다.
		// remote 관련된애들은 다 throws RemoteException 해줘야함.
		super();
	}

	
	// 본격적인 기능
	public static void main(String[] args) {
		try {
			// 구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI서버에 등록한다.
			
			// 1. RMI용 인터페이스를 구현한 객체 생성(자기자신?)
			RemoteInterface inf = new RemoteServer();
			
			//2. 구현한 객체를 클라이언트에서 찾을 수 있도록 Registry객체를 생성해서 등록한다.
			
			// 포트번호를 지정하여 Registry객체 생성(기본포트값 : 1099)
			Registry reg = LocateRegistry.createRegistry(8888); // 8888로 바꾼거야.
			
			// Registry 서버에 제공하는 객체 등록하기
			// 형식) Registry  객체변수. rebind("객체의 Alias", 등록할 객체변수);
			// bind(레지스터에 원격객체를 등록하는 것.)      unbind(원격객체 빼는거.)     rebind(바인드 돼있으면 말고 안돼있으면 바인드시켜라.)
			reg.rebind("server", inf);// inf의 별명을 server 로 준거야.lookup으로 원격에있는 객체를 찾을건데 등록된이름이 server야.
			
			System.out.println("서버가 준비되었습니다.");
		} catch(RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	// RMI용 인터페이스에서 선언한 메서드들을 구현한다.
	
	@Override
	public int doRemotePrint(String str) throws RemoteException {
		int length = str.length();
		System.out.println("클라이언트에서 보내온 메세지 : " + str);
		System.out.println("출력 끝...");
		
		return length;
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("클라이언트에서 보낸 List값들...");
		for(int i = 0 ; i < list.size(); i++) {
			System.out.println((i+1) + "번 째 : " + list.get(i));
		}
		System.out.println("List 출력 끝...");
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("클라이언트에서 보내온 TestVO객체 값 출력");
		System.out.println("testId : " + vo.getTestId());
		System.out.println("testNum : " + vo.getTestNum());
		System.out.println("TestVO객체 출력 끝...");
	}

	@Override
	public void setFiles(FileInfoVO[] finfo) throws RemoteException {
		FileOutputStream fos = null;
		String dir = "d:/C_Lib/"; // 파일이 저장될 폴더 지정
		System.out.println("파일 저장 시작...");
		
		for(int i =0 ; i < finfo.length; i ++) {
			try {
				fos = new FileOutputStream(dir + finfo[i].getFileName());
				// 클라이언트에서 전달한 파일데이터(byte[])를 서버측에 저장한다.
				fos.write(finfo[i].getFileData());
				fos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("파일 저장 완료...");

		
	}

}
