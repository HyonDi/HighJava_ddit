package 게시판MVC;

import java.util.List;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여 service에 전달하는 DAO의 interface
 * @author PC-16
 *
 */
public interface 게시판Dao {

	public List<게시판VO> displayAllList();
	
	public int write(게시판VO bod);
	
	public int update(게시판VO bod);
	
	public int delete(int bodNo);
	
	public List<게시판VO> search(게시판VO bod);
}
