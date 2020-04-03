package kr.or.ddit.member.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;


public class MemberServiceImpl extends UnicastRemoteObject implements IMemberService {
	// 사용할 DAO의 객체변수를 선언한다.
	private MemberDaoImpl memDao;
	private static MemberServiceImpl service;  // 자기자신 참조변수
	
	
	private MemberServiceImpl() throws RemoteException {
		//memDao = new MemberDaoImpl();  // Dao객체 생성
		memDao = MemberDaoImpl.getInstance();  // Dao객체 생성
	}
	
	public static MemberServiceImpl getInstance() throws RemoteException{
		if(service==null){
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	// 각 메서드에서는 생성된 Dao객체를 이용하여
	// 작업에 맞는 Dao객체의 메서드를 호출한다.
	
	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}


	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		return memDao.getSearchMember(mv);
	}

}
