package service;

import java.util.List;
import java.util.Map;

import vo.LprodVO;
import vo.ProdVO;

public interface ProdInfoService {
	List<ProdVO> getSearchData(Map<String, String> map);

	List<LprodVO> getLpName();

	List<ProdVO> getPlist(String lpName);

}
