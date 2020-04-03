package kr.or.ddit.rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Runnable {
	// UnicastRemoteObject 

   private ChatServer server = null;

   protected static String name = null;

	public ChatClientImpl(String name) throws RemoteException, Exception {

		ChatClientImpl.name = name;

		// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후
		// 사용할 객체를 불러온다.
		// 등록은 안해. 등록은 서버에서할거야.
		// registry 는 rmi기술에서 서버역할을 함.
		Registry reg = LocateRegistry.getRegistry("192.168.201.45", 8888);

		server = (ChatServer) reg.lookup("RMIChatServer");

		// this = ChatClientImpl
		server.addClient(this, name);

	}

   public void printMessage(String msg) throws RemoteException {

       System.out.println(msg);     

   }  

   public void run() {

       BufferedReader br = null;

       try {
    	   
    	   // ??버퍼드리더가 왜필요?? : 한번에 받기 위함.
          br = new BufferedReader(new InputStreamReader(System.in));

          String message = null;

          while ((message = br.readLine())!= null) {

             server.say(""+ name +" : " + message);  

             if(message.equals("exit")){                  

                server.disconnect(this, name);   

             }                   

          }

       }catch(Exception e) {

          System.out.println(e);

       }      

   }  

   public static void main(String[] args) throws Exception {
	   
	   String str = JOptionPane.showInputDialog("대화명을 입력하세요");
	   
       new Thread(new ChatClientImpl(str)).start();        

   }

}
