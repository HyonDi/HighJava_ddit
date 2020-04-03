package service;

import java.util.List;
import java.util.Map;

import dao.ProdDao;
import dao.ProdDaoImpl;
import vo.LprodVO;
import vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	
	// 싱글톤
	private ProdServiceImpl () {}
	
	private static ProdServiceImpl instance;
	
	public static ProdServiceImpl getInstance() {
		if(instance == null) {
			instance = new ProdServiceImpl();
		}
		return instance;
	}
	
	// dao 객체생성
	ProdDao dao = ProdDaoImpl.getInstance();
	
	
	
	@Override
	public List<ProdVO> getSerchProd(Map<String, String> map) {
		return dao.getSerchProd(map);
	}

	@Override
	public List<LprodVO> getLprodName() {
		return dao.getLprodName();
	}

	@Override
	public List<ProdVO> getProdName(String lprodName) {
		return dao.getProdName(lprodName);
	}

}
