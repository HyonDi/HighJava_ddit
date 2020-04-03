package kr.or.ddit.member.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao{
	private SqlMapClient smc;
	
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl(){
		
		smc = SqlMapClientFactory.getInstance(); // SqlMapClient 객체 생성하기
		
	}
	
	public static MemberDaoImpl getInstance(){
		if(dao==null){
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	// MemberVO객체에 담겨진 자료를 DB에 insert하는 메서드	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("memberMain.insertMember", mv);
			if(obj==null){
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	// 회원ID를 이용하여 회원 정보를 삭제하는 메서드
	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("memberMain.deleteMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// MemberVO객체의 정보를 이용하여 회원정보를 수정하는 메서드
	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = smc.update("memberMain.updateMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	// mymember테이블의 전체 레코드를 가져와서 각각의 레코드는
	// MemberVO에 담고, 이 MemberVO를 다시 List에 담아서 반환하는 메서드
	@Override
	public List<MemberVO> getAllMemberList() {
		ArrayList<MemberVO> memList = null;
		try {
			memList = 
				(ArrayList<MemberVO>) smc.queryForList("memberMain.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	// 주어진 회원ID가 있으면 true, 없으면 false를 반환하는 메서드
	@Override
	public boolean getMember(String memId) {
		boolean check = false;
		try {
			int count = 
				(int) smc.queryForObject("memberMain.getMember", memId); 
			
			if(count>0){
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return check;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		ArrayList<MemberVO> memList = null;
		try {
			memList = 
			(ArrayList<MemberVO>) smc.queryForList("memberMain.getSearchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return memList;
	}

}
