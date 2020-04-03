package 게시판MVC;

import java.util.List;

public interface 게시판Service {

	public List<게시판VO> displayAllList();
	
	public int write(게시판VO bod);
	
	public int update(게시판VO bod);
	
	public int delete(int bodNo);
	
	public List<게시판VO> search(게시판VO bod);
	
}
