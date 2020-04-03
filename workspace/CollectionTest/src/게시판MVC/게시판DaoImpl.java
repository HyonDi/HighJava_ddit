package 게시판MVC;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;



public class 게시판DaoImpl implements 게시판Dao{
	
	//로그를 남겨봅니다.20/01/23
	//Log4j 를 이용한 로그남기기 위한 로거 생성. (로거 = 로그를 남기는 애.)
	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query"); 
	
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter"); 
	
	private static final Logger resultLogger = Logger.getLogger(게시판DaoImpl.class); 
	// 3개의 로거를 생성했습니다.
	
	
/*
 * iBatis 적용으로 제거부분.
 * 	//오라클에 접속해서 crud 하는 작업!!!= DAP
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	 //자원반납용 메서드
	 
	private void disConnect() {
	//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
		
	}
	Scanner scan = new Scanner(System.in); 
*/
	
	// 싱글톤부분
	private static 게시판DaoImpl iaminstance;
	
	private 게시판DaoImpl() {
		
	/*	try {
			
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		}catch(IOException e){
			System.out.println("SqlMapClient 생성 실패!!!");
			e.printStackTrace();
		}*/
		
	}
	
	public static 게시판DaoImpl getInstance() {
			if(iaminstance ==null) {
				iaminstance = new 게시판DaoImpl();
			}
			return iaminstance;
	}
	
	//-----------------------------------------------------
	
	@Override
	public int write(게시판VO bod) {
		
		int cnt = 0;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			
			//19/01/16 로그찍기!!-쿼리(로그4j)
			sqlLogger.info("쿼리 : " + smc);
			sqlLogger.info("쿼리(WARN) : " + smc);
			
			
			
			
			게시판VO bod2 =new 게시판VO();
			
			/*bod.setBoard_no(rs.getInt("board_no"));*/
			
			bod2.setBoard_writer(bod.getBoard_writer());
			bod2.setBoard_title(bod.getBoard_title());
			bod2.setBoard_content(bod.getBoard_content());
			bod2.setBoard_date(bod.getBoard_date());	
			
			//로그
			paramLogger.info("파라미터 : (" + bod.getBoard_writer() + ", " + bod.getBoard_no() + ", " + bod.getBoard_content() + ", " + bod.getBoard_date() + ")");
			
			Object obj = smc.insert("boardHomework.writeBoard",bod2);
			
			if(obj == null) {
				System.out.println(bod.getBoard_writer()+"님 게시글 작성이 완료되었습니다.");
				cnt=1;
				resultLogger.info("결과 : " + cnt);
			}else {
				System.out.println(bod.getBoard_writer() + "님 게시글 작성이 실패하였습니다.");
			}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return cnt;	
		
}
	
	@Override
	public List<게시판VO> displayAllList() {

		List<게시판VO> bodList = new ArrayList<게시판VO>();
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			bodList = smc.queryForList("boardHomework.displayAllBoard");
			// 노란줄 : 제너릭타입인수로 바꾸래. 어떻게하는거지??
			
		} catch (SQLException e) {
			System.out.println("조회 실패.");
			e.printStackTrace();
		}catch(IOException e) {
			 e.printStackTrace();
		}
		return bodList;
	}
	
/*		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				게시판VO bod = new 게시판VO();
				bod.setBoard_no(rs.getInt("board_no"));
				bod.setBoard_writer(rs.getString("board_writer"));
				bod.setBoard_content(rs.getString("board_content"));
				bod.setBoard_title(rs.getString("board_title"));
//				bod.setBoard_date(utilDate);
				
				Date utilDate = new java.util.Date(rs.getTimestamp("board_date").getTime());
		//얘는 자바타입 데이트 변수									얘는 sql타입데이트
				   
				bod.setBoard_date(utilDate);
				//javautill형으로 바꾼 데이터를 세팅함!!
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd hh:mm:ss");
				String bodDateStr =  sdf.format(utilDate);
				
				bodList.add(bod);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return bodList;
	}*/


	@Override
	public int update(게시판VO bod) {
		int cnt = 0;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			게시판VO bod2 = new 게시판VO();
			bod2.setBoard_title(bod.getBoard_title());
			bod2.setBoard_content(bod.getBoard_content());
			
			 cnt =smc.update("boardHomework.updateBoard",bod);
			 
			if (cnt >0) {
				System.out.println("게시글 수정이 완료되었습니다.");
			} else {
				System.out.println("게시글 수정이 실패하였습니다.");
			}
		} catch(SQLException e) {
			System.out.println("게시글 수정이 실패하였습니다.");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	
	@Override
	public int delete(int bodNo) {
		
		int cnt = 0;
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd =Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc =SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
				cnt = smc.delete("boardHomework.deleteBoard",bodNo);
			if(cnt >0) {
				System.out.println("게시글을 삭제했습니다.");
			}else {
				System.out.println("게시글 삭제가 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("게시글 삭제가 실패했습니다.");
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<게시판VO> search(게시판VO bod) {
		
		List<게시판VO> bodList = new ArrayList<게시판VO>();
		
		List<게시판VO> list =new ArrayList<>();
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			bodList = smc.queryForList("boardHomework.searchBoard",bod);
			// 얘도 제너릭타입인수로 바꾸래.
					for(int i =0; i< bodList.size(); i++) {
						list.add(bodList.get(i));
					}
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		
			return list;
		}

}
