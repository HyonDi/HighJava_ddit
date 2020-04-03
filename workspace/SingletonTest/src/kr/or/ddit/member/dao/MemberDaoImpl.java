package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	//로그를 남겨봅니다.20/01/16
	//Log4j 를 이용한 로그남기기 위한 로거 생성. (로거 = 로그를 남기는 애.)
	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query"); 
	
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter"); 
	
	private static final Logger resultLogger = Logger.getLogger(MemberDaoImpl.class); 
	// 3개의 로거를 생성했습니다.
	
	
	
	
	
	
	
	
	
	
	
	
	//자기 자신을 참조할 변수
	private static MemberDaoImpl dao;
//	private static IMemberDao dao; //얘로 해도 되는데 잘맞춰야함.
	
	
	//오라클에 접속해서 crud 하는 작업!!!= DAP
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//생성자를 private으로 막아놓는다.
	private MemberDaoImpl() {
		
	}
	
	//getInstance를 publicstatic 으로 만든다.
	public static MemberDaoImpl getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	/**
	 * 자원반납용 메서드
	 */
	private void disConnect() {
	//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}
	private Scanner scan = new Scanner(System.in); 
	
	
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();//커넥션
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) " + " values (?, ?, ?, ?)";//쿼리문작성
			
			
			//19/01/16 로그찍기!!-쿼리(로그4j)
			sqlLogger.debug("쿼리 : " + sql);
			sqlLogger.warn("쿼리(WARN) : " + sql);
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id()); //1번째컬럼에 mem_id를 넣는다는 세팅같음.
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());
			
			//19/01/16 로그찍기!!-파라미터(로그4j)
			paramLogger.debug("파라미터 : (" + mv.getMem_id() + ", " + mv.getMem_name() + ", " + mv.getMem_tel() + ", " + mv.getMem_addr() + ")");
			
			
			//이제 날려야함.( = 보내는거?)
			//결과값이 인트로 날라올거야. executeUpdate() 쓸거니까. insert 구문이니까.
			
			cnt = pstmt.executeUpdate();
			
			//19/01/16 로그찍기!!-결과(로그4j)
			resultLogger.warn("결과 : " + cnt);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();//자원반납!
		}
	
		return cnt;
}

	@Override
	public boolean getMember(String memId) {
		
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM mymember " + " WHERE mem_id = ?"; //있는지없는지 행수를 조회해보았다.
			
			pstmt = conn.prepareStatement(sql);//날림
			pstmt.setString(1, memId);//세팅
					
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");//cnt는 알리아스임(COUNT(*))
			}
			
			if(cnt > 0) {
				chk = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			chk = false; //디폴트라 안써도 되긴함.
		}finally {
			disConnect();
		}
		return chk;
	}
	

	@Override
	public List<MemberVO> getAllMemberList() {
		
		//쿼리결과 리턴할 리스트 선언.
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM mymember";
			
			//prepared하지 않아도 될듯해 stmt에 담았다.
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				
				MemberVO mv = new MemberVO();
				mv.setMem_id(rs.getString("mem_id"));
				mv.setMem_name(rs.getString("mem_name"));
				mv.setMem_tel(rs.getString("mem_tel"));
				mv.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(mv);
			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE mymember" + " SET mem_name = ?, " + " mem_tel = ?, " + " mem_addr = ? " + " WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id());
			
			 cnt = pstmt.executeUpdate();
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();// 자원반납
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM mymember WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();//자원 반납
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		// 처음엔 어려워요.
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM mymember WHERE 1=1";
			
			//동적쿼리??
			if(mv.getMem_id() !=null && !mv.getMem_id().equals("")) {
				sql += " and mem_id = ? ";
			}
			
			if(mv.getMem_name() !=null && !mv.getMem_name().equals("")) {
				sql += " and mem_name = ? ";
			}
			
			if(mv.getMem_tel() !=null && !mv.getMem_tel().equals("")) {
				sql += " and mem_tel = ? ";
			}
			
			if(mv.getMem_addr() !=null && !mv.getMem_addr().equals("")) {
				sql += " and mem_addr like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			
			
			
			
			if(mv.getMem_id() !=null && !mv.getMem_id().equals("")) { 
				pstmt.setString(index++, mv.getMem_id());
			}
			
			if(mv.getMem_name() !=null && !mv.getMem_name().equals("")) { 
				pstmt.setString(index++, mv.getMem_name());
			}
			
			if(mv.getMem_tel() !=null && !mv.getMem_tel().equals("")) { 
				pstmt.setString(index++, mv.getMem_tel());
			}
			if(mv.getMem_addr() !=null && !mv.getMem_addr().equals("")) { 
				pstmt.setString(index++, mv.getMem_addr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()); {
				MemberVO memVO = new MemberVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setMem_name("mem_name");
				memVO.setMem_tel("mem_tel");
				memVO.setMem_addr("mem_addr");
				
				memList.add(memVO);
			}
		}catch(SQLException e) {
			memList = null;
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return memList;
	}

}
