package dao;

import java.util.List;
import java.util.Map;

import vo.LprodVO;
import vo.ProdVO;

public interface ProdDao {
	List<ProdVO> getSerchProd(Map<String,String> map);
	
	List<LprodVO> getLprodName();
	
	List<ProdVO> getProdName(String lprodName);
}
