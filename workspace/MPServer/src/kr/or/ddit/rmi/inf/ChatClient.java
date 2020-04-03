package kr.or.ddit.rmi.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.rmi.vo.ChatVO;

public interface ChatClient extends Remote {
	
	/**
	 * RMI서버에서 모든 클라이언트에 메시지를 전송해 주기 위해 필요한 메서드
	 * @param msg
	 * @throws RemoteException
	 */
	public void printMessage(String msg) throws RemoteException;
	
	/**
	 * RMI서버에서 특정 클라이언트에 메시지를 전송해 주기 위해 필요한 메서드
	 * @param msg
	 * @throws RemoteException
	 */
	public void printPrivateMessage(String msg) throws RemoteException;
	
	
	/**
	 * 서버에서 사용자 목록 정보를 등록해 주기 위한 메서드
	 * @throws RemoteException
	 */
	public void setUserList(List<String> userList) throws RemoteException;
	
	/**
	 * 서버에서 1:1대화 요청창을 띄워주기 위한 메서드
	 * @param fromNickName
	 * @throws RemoteException
	 */
	public void showReqPrivateChatDialog(String fromNickName) throws RemoteException;
	
	
	/**
	 * 서버에서 1:1대화 요청 응답을 알려주기 위한 메서드
	 * @param fromNickName
	 * @throws RemoteException
	 */
	public void showReplyPrivateChat(String fromNickName, boolean isAccecpted) throws RemoteException;
	
	
}
