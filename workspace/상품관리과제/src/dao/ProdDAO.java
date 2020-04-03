package dao;

import java.util.List;

import vo.ProdMainVO;
import vo.ProdVO;

public interface ProdDAO {
	public List<ProdVO> combox1List();
	public List<ProdMainVO> combox2List(String mv);
}
