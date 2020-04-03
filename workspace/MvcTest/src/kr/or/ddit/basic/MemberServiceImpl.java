package kr.or.ddit.basic;

import java.util.List;

public class MemberServiceImpl implements IMemberService {

	//DAO와 상관없는 메서드를 여기에 새로 만들어도 된다.
	//컨트롤러는 서비스만 바라봄.
	
	//현재는 모든 메서드가 crud여서 DAO와 전부 연결된다.
	
	//service는 없어도 되긴하는데! 암튼 있어야해
	
	
	
	//사용할 DAO의 객체변수를 선언한다.
	private IMemberDao memDao;

	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
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
