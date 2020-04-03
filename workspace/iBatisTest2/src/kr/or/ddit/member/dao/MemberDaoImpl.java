package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;


public class MemberDaoImpl implements IMemberDao {
	
	
	//자기 자신을 참조할 변수
	private static MemberDaoImpl dao;
	
	private SqlMapClient smc;

	//외부 생성을 막을 생성자
	private MemberDaoImpl() {
		//1-1. xml문서 읽어오기
		//설정파일의 인코딩 설정
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml"); //ibatis가 사용할 기능들이 들어있는 파일
			//1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체(sqlMapClient) 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); //Reader객체 닫기
		} catch (IOException e) {
			System.out.println("sqlMapClient 객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static MemberDaoImpl getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0; //cnt값을 리턴하기 위해 try-catch 밖에서 선언
		
		Object obj;
		
		try {
			obj = smc.insert("member.insertMember", mv);
			
			if(obj == null) {
				cnt = 1; //성공
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	@Override
	public boolean getMember(String memId) {
		
		boolean chk = false;
		
		try {
			
			int cnt = (int) smc.queryForObject("member.getMember", memId);
			
			if(cnt > 0) {
				chk = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			chk = false; //false로 세팅
		}
		return chk;
	}

	
	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			
			memList = smc.queryForList("member.getMemberAll");
			
		}catch(SQLException e) {
			System.out.println("회원자료 가져오기 실패!!!");
			e.printStackTrace();
		}
		return memList;
	}

	
	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			cnt = smc.update("member.updateMember", mv);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	@Override
	public int deleteMember(String memId) {

		int cnt = 0;
		
		try {
			
			cnt = smc.delete("member.deleteMember", memId);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			
			memList = smc.queryForList("member.getSearchMember", mv);
			
		}catch(SQLException e) {
			memList = null; //예외가 발생하면 memList를 null로 처리한다.
			e.printStackTrace();
		}
		return memList;
	}

}
