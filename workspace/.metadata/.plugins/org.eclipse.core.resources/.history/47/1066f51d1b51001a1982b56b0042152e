package kr.or.ddit.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class ServerMain {
	
	public static void main(String[] args) {
		
		try {
			
			Registry reg = LocateRegistry.createRegistry(8888); // 레지스트리 생성, 포트번호 8888
			
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			reg.rebind("memberService",memberService); // memberService 를 레지스트리에 등록.
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		System.out.println("서버 준비 완료");
		
	}
}
