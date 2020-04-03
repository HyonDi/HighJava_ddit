package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote { //remote 를 extends.

	// 추상메서드들은 throws remoteException;
	// 인터페이스는 서버에있지만 이 메서드들 사용은 client가 할것임.
   public void addClient(ChatClient client, String name) throws RemoteException;

   // 클라이언트가 접속자목록에서 자기자신을 지운다.
   public void disconnect(ChatClient client, String name) throws RemoteException;

   // 메세지를 모든접속자에게 뿌려준다.
   public void say(String msg) throws RemoteException;

}
