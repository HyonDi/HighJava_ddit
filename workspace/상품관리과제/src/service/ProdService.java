package service;

import java.util.List;

import vo.ProdMainVO;
import vo.ProdVO;

public interface ProdService {
	
	public List<ProdVO> combox1List();
	public List<ProdMainVO> combox2List(String mv);
	
}
