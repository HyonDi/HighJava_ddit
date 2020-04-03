package service;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import dao.ProdDao;
import dao.ProdDaoImpl;
import vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	
	private ProdDao dao;
	private static ProdServiceImpl service;
	Charset charset;
	Reader rd;
	SqlMapClient smc;
		
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
		try {
			charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ProdServiceImpl getInstance() {
		if(service == null) {
			service = new ProdServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<ProdVO> getCom1List() {
		return dao.getCom1List();
	}

	@Override
	public List<ProdVO> getCom2List(String com2) {
		return dao.getCom2List(com2);
	}

}
