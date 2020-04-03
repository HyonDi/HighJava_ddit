package service;

import java.util.List;

import vo.ProdVO;

public interface ProdService {
	public List<ProdVO> getCom1List();
	
	public List<ProdVO> getCom2List(String com2);
}
