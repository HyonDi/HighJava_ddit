package service;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import dao.ProdDAOImpl;
import vo.ProdMainVO;
import vo.ProdVO;

public class ProdServiceImpl implements ProdService{

	private ProdDAOImpl prodDao;
	private static ProdServiceImpl prodService;
	private SqlMapClient smc = null;
	
	private ProdServiceImpl() {
		prodDao = ProdDAOImpl.getInstance();
		
    	try {
    	Charset charset =Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");	//ibatis의 설정 파일
		smc =SqlMapClientBuilder.buildSqlMapClient(rd);		// Client객체 생성
			rd.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static ProdServiceImpl getInstance() {
		if(prodService == null) {
			prodService = new ProdServiceImpl();
		}
		return prodService;
	}
	
	@Override
	public List<ProdVO> combox1List() {
		return prodDao.combox1List();
	}

	@Override
	public List<ProdMainVO> combox2List(String mv) {
		return prodDao.combox2List(mv);
	}

}
