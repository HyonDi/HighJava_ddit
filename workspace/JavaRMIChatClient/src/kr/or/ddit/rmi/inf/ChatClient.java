package kr.or.ddit.rmi.inf;

import java.lang.String;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface ChatClient extends Remote {
	// 클라이언트쪽 인터페이스는 서버에서 실행. 호출은 서버지만 실행되는곳은 클라이언트임.
	// 클라이언트가 서버에 이 메서드를 제공해준것임. 클라이언트쪽 콘솔창에 결과가 찍힐거야.
	// registry 는 rmi기술에서 서버역할을 함.
	
   public void printMessage(String msg) throws RemoteException;

}
