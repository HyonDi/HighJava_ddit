package kr.or.ddit.mvcwork.ex;

import java.util.List;

public interface BoardDao {

	public List<BoardVO> findout(BoardVO bv);
	
	public int delete(String no);
	
	public int repair(BoardVO bv);
	
	public List<BoardVO> selectview();
	
	public Integer write(BoardVO bv);
	
	public  boolean getSelect(String no);
}
