package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
	
	/**
	 * 클라이언트 객체를 서버에 등록하기 위한 메서드
	 * @param client
	 * @param name
	 * @throws RemoteException
	 */
	public void addClient(ChatClient client, String name) throws RemoteException;
	
	/**
	 * 클라이언트를 제거(접속해제)하기 위한 메서드
	 * @param client
	 * @param name
	 * @throws RemoteException
	 */
	public void disconnect(ChatClient client, String name) throws RemoteException;

	/**
	 * 클라이언트에서 서버쪽으로 메시지 보내기 위한 메서드
	 * @param msg
	 * @param nickName
	 * @throws RemoteException
	 */
	public void sayToAll(String msg, String nickName) throws RemoteException;
	
	/**
	 * 1:1대화 신청을 위한 메서드
	 * (상대방 화면에 1:1대화 수락여부 창을 띄운다.)
	 * @param fromNickName
	 * @param toNickName
	 * @throws RemoteException
	 */
	public void requestPrivateChat(String fromNickName, String toNickName) throws RemoteException;
	
	/**
	 * 1:1대화 신청에 응답을 위한 메서드
	 * @param fromNickName
	 * @param toNickName
	 * @throws RemoteException
	 */
	public void replyPrivateChat(String fromNickName, String toNickName, boolean isAccepted) throws RemoteException;
	
	/**
	 * 클라이언트에서 서버쪽으로 특정인에게 메시지 보내기 위한 메서드
	 * @param msg
	 * @param nickName
	 * @throws RemoteException
	 */
	public void sayToSomeone(String msg, String fromNickName, String toNickName) throws RemoteException;
	
}
