package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	//사용할 DAO의 객체 변수를 선언한다.
	private IMemberDao memDao; //MemberDaoImpl로 선언해도 됨
	
	//자기자신 참조변수
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() { //생성자
		memDao = MemberDaoImpl.getInstance();
	}

	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	
	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMeber(String memId) {
/*		int cnt = memDao.deleteMeber(memId);
		if(cnt > 0) {
			관리자에게 메일 발송하기...
		} -> 삭제됐는지 Dao에서 확인하고 Service에서 삭제확인메일 보내는 기능을 수행한다*/

		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		return memDao.getSearchMember(mv);
	}


}
