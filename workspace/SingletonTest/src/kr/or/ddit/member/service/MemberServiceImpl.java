package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	//DAO와 상관없는 메서드를 여기에 새로 만들어도 된다.
	//컨트롤러는 서비스만 바라봄.
	
	//현재는 모든 메서드가 crud여서 DAO와 전부 연결된다.
	
	//service는 없어도 되긴하는데! 암튼 있어야해
	
	//자기 자신을 참조할 변수
	private static MemberServiceImpl service;

	
	//getInstance를 publicstatic 으로 만든다.
	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	//생성자를 private으로 막아놓는다.
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	//사용할 DAO의 객체변수를 선언한다.
	private IMemberDao memDao;

	
	
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
	public int deleteMember(String memId) {
/*		int cnt = memDao.deleteMember(memId);
		if(cnt > 0) {
			//관리자에게 메일 발송하기...
		}
		return cnt;*/		
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		return memDao.getSearchMember(mv);
		
	}
	
	
}
