package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BoardDaoImpl implements IBoardDao {
	
	private static BoardDaoImpl instance;
	
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		}catch(IOException e){
			System.out.println("SqlMapClient 생성 실패!!!");
			e.printStackTrace();
		}
		
	}
	
	public static BoardDaoImpl getInstance() {
		if(instance == null) {
			instance = new BoardDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			
			boardList= smc.queryForList("BoardHW.getAllBoardList");
			
		}catch(SQLException e) {
			System.out.println("게시글 목록 가져오기 실패 !!!");
			e.printStackTrace();
		}
		
		return boardList;
	}

	
	@Override
	public boolean getBoard(int board_no) {

		boolean chk = false;
		
		try {
			
			int cnt = (int)smc.queryForObject("BoardHW.getBoard", board_no);
			if(cnt > 0) { chk = true;}
			
		}catch(SQLException e) {
			e.printStackTrace(); chk = false;
		}
		return chk;
	}

	
	@Override
	public int insertBoard(BoardVO bv) {
		
		int check = 0;
		
		try {
			
			Object obj = smc.insert("BoardHW.insertBoard", bv);
			
			if(obj == null) {
				return check;
			}else {check=1;}
			
		}catch(SQLException e) {
			System.out.println("게시글 작성 실패!!!");
			e.printStackTrace();
		}
		return check;
	}

	
	@Override
	public int updateBoard(BoardVO bv) {
		
		int check = 0;
		
		try {
			
			check = smc.update("BoardHW.updateBoard", bv);
			
		}catch(SQLException e) {
			System.out.println("게시글 수정 실패!!!");
			e.printStackTrace();
		}
		return check;
	}

	
	@Override
	public int deletBoard(int board_no) {
		
		int check = 0;
		
		try {
			check = smc.delete("BoardHW.deleteBoard", board_no);
			
		}catch(SQLException e) {
			System.out.println("게시글 삭제 실패!!!");
			e.printStackTrace();
		}
		return check;
	}

	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			
			boardList = smc.queryForList("BoardHW.getSearchBoard", bv);
			
		}catch(SQLException e) {
			boardList = null;
			e.printStackTrace();
		}
		
		return boardList;
	}

}
