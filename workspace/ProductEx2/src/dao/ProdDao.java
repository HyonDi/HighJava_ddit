package dao;

import java.util.List;

import vo.ProdVO;

public interface ProdDao {
	public List<ProdVO> getCom1List();
	
	public List<ProdVO> getCom2List(String com2);
}
