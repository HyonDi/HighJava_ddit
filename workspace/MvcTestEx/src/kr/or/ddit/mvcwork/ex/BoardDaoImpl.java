package kr.or.ddit.mvcwork.ex;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.util.DBUtil3;



public class BoardDaoImpl implements BoardDao {
	
	private static BoardDaoImpl iaminstance=null;
	
	private BoardDaoImpl() {
	}
	
	public static BoardDaoImpl getInstance() {
			if(iaminstance ==null) {
				iaminstance = new BoardDaoImpl();
			}
			return iaminstance;
	}
	
	@Override
	public List<BoardVO> findout(BoardVO bv) {
	List<BoardVO> bvList =new ArrayList<BoardVO>();
	List<BoardVO> list =new ArrayList<>();
	try {
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
		
		SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
		rd.close();
				bvList=smc.queryForList("board.findout",bv);
				for(int i =0; i< bvList.size(); i++) {
					list.add(bvList.get(i));
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	
		return list;
	}

	@Override
	public int delete(String no) {
		int cnt =0;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
				cnt = smc.delete("board.delete",no);
			if(cnt >0) {
				System.out.println("글삭제가 완료되었습니다.");
			}else {
				System.out.println("글삭제에 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("글삭제에 실패했습니다.");
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int repair(BoardVO bv) {
		int cnt=0;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			BoardVO bv2 = new BoardVO();
			bv2.setBoard_title(bv.getBoard_title());
			bv2.setBoard_content(bv.getBoard_content());
			bv2.setBoard_no(bv.getBoard_no());
			
			 cnt =smc.update("board.repair",bv2);
			if(cnt >0) {
				System.out.println("정상적으로 글이 수정되었습니다.");
			}else {
				System.out.println("글 수정에 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("글 수정에 실패했습니다.");
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectview() {
	List<BoardVO> bvList =new ArrayList<BoardVO>();
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			bvList = smc.queryForList("board.selectview");
		} catch (SQLException e) {
			System.out.println("selectview에서 조회실패");
			e.printStackTrace();
		}catch(IOException e) {
			 e.printStackTrace();
		}
		return bvList;
	}

	@Override
	public Integer write(BoardVO bv) {
		int cnt=0;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			BoardVO bv2 =new BoardVO();
			bv2.setBoard_writer(bv.getBoard_writer());
			bv2.setBoard_title(bv.getBoard_title());
			bv2.setBoard_content(bv.getBoard_content());
			Object obj = smc.insert("board.write",bv2);
			
		if(obj == null) {
			System.out.println(bv.getBoard_writer()+"님 게시글이 등록되었습니다.");
			cnt=1;
		}else {
			System.out.println(bv.getBoard_writer()+"님 게시글 등록되지 않았습니다.");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return  cnt;
	}

	@Override
	public boolean getSelect(String no) {
		boolean check =false;
		List<BoardVO> bvList =new ArrayList<BoardVO>();
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			bvList = smc.queryForList("board.getSelect", no);
			if(bvList == null) {
				System.out.println("찾는 값이 없습니다.");
			}else {
				check =true;
			}
		}catch(SQLException e) {
			System.out.println("조회실패");
			e.printStackTrace();
			check=false;
		}catch(IOException e) {
			e.printStackTrace();
		}
		return check;
	}

		
	}


	
	


