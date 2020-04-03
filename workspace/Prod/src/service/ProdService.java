package service;

import java.util.List;
import java.util.Map;

import vo.LprodVO;
import vo.ProdVO;

public interface ProdService {
	List<ProdVO> getSerchProd(Map<String,String> map);
	
	List<LprodVO> getLprodName();
	
	List<ProdVO> getProdName(String lprodName);
}
