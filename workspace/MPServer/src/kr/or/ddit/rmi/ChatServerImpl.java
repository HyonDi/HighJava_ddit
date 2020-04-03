package kr.or.ddit.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javafx.collections.ObservableList;
import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;
import kr.or.ddit.rmi.vo.ChatVO;

@SuppressWarnings("serial")
public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

   List<ChatVO> clientList = Collections.synchronizedList(new ArrayList<>());
   
   public ChatServerImpl() throws RemoteException{} 

   public static void main(String args[]){

       try{
    	   
          //System.setProperty("java.rmi.server.hostname","192.168.201.45");

          ChatServerImpl Server = new ChatServerImpl();    
          
          Registry reg = LocateRegistry.createRegistry(8888);

          reg.rebind("RMIChatServer", Server);

          System.out.println("RMI 채팅서버 시작...");

       } catch (Exception e){
          System.out.println("예외발생 : " + e.getMessage());
          e.printStackTrace();
       }

   }

   public void addClient(ChatClient client, String name) throws RemoteException{
	   
	   ChatVO chatVO = new ChatVO(client, name);
	   
       clientList.add(chatVO);
       
       sayToAll("님이 접속하셨습니다.", name);
       
       sendUserList(); // 각 사용자 유저리스트 출력

       System.out.println("접속함. 현재 접속중인 사용자수는 " + clientList.size());

   }
   
   
   public void disconnect(ChatClient client, String name) throws RemoteException{
	   
       int i = 0;
       for(ChatVO tmpChatVO : clientList) {
    	   if(tmpChatVO.getNickName().equals(name)) { // 동일한 사용자가 존재하면
    		   
    		   clientList.remove(i);
    		   
    		   sayToAll("님이 퇴장하셨습니다.", name);
    		   
    		   sendUserList(); // 각 사용자 유저리스트 출력
    		   
    		   System.out.println("퇴장함. 현재 접속중인 사용자수는 " + clientList.size());
    		   
    		   break;
    	   }
    	   i++;
       }
   }
   

   public void sayToAll(String msg, String nickName) throws RemoteException {

       int numOfConnect = clientList.size();
       ChatVO chatVO = null;
       for(int i = 0; i < numOfConnect; i++){

    	   chatVO = clientList.get(i);
    	   
    	   if(msg.indexOf("님이 접속하셨습니다.") > -1 
    			   || msg.indexOf("님이 퇴장하셨습니다.") > -1) {
    		   if(!chatVO.getNickName().equals(nickName)) { // 본인이 아닌경우에만 메시지 출력하기
    			   chatVO.getClient().printMessage(nickName + " " + msg);
    		   }
    	   }else {
    		   chatVO.getClient().printMessage(nickName + " : " + msg);
    	   }
       }
   }
   
   
   public void sendUserList() throws RemoteException {
	   
	   int numOfConnect = clientList.size();
	   
	   List<String> userList = new ArrayList<>();
	   for(int i = 0; i < numOfConnect; i++){
		   userList.add(clientList.get(i).getNickName());
	   }
       
	   
	   ChatVO chatVO = null;
	   for(int i = 0; i < numOfConnect; i++){
		   
		   chatVO = clientList.get(i);
		   
		   chatVO.getClient().setUserList(userList);
	   }
   }

	@Override
	public void requestPrivateChat(String fromNickName, String toNickName) throws RemoteException {
		
		int numOfConnect = clientList.size();
		
		 ChatVO chatVO = null;
		for (int i = 0; i < numOfConnect; i++) {
			if(clientList.get(i).getNickName().equals(toNickName)) {
				chatVO = clientList.get(i);
				chatVO.getClient().showReqPrivateChatDialog(fromNickName);
				
			}
		}

	}
	
	
	@Override
	public void replyPrivateChat(String fromNickName, String toNickName, boolean isAccepted) throws RemoteException {
		
		int numOfConnect = clientList.size();
		
		ChatVO chatVO = null;
		for (int i = 0; i < numOfConnect; i++) {
			if(clientList.get(i).getNickName().equals(toNickName)) {
				chatVO = clientList.get(i);
				chatVO.getClient().showReplyPrivateChat(fromNickName, isAccepted);
				
			}
		}
		
	}
	
	/**
	 * 특정인에게 메시지 보내기
	 */
	@Override
	public void sayToSomeone(String msg, String fromNickName, String toNickName) throws RemoteException {
		int numOfConnect = clientList.size();
		ChatVO chatVO = null;
		for (int i = 0; i < numOfConnect; i++) {

			chatVO = clientList.get(i);

			if (chatVO.getNickName().equals(toNickName)) { // 수신자에게 보내기
				chatVO.getClient().printPrivateMessage(fromNickName + " : " + msg);
			} else if (chatVO.getNickName().equals(fromNickName)) { // 송신자에게 보내기
				chatVO.getClient().printPrivateMessage(fromNickName + " : " + msg);
			}
		}
	}

}
