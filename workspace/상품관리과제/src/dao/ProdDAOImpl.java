package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import service.ProdServiceImpl;
import vo.ProdMainVO;
import vo.ProdVO;

public class ProdDAOImpl implements ProdDAO{

	private static ProdDAOImpl prodDao;
	private SqlMapClient smc = null;
	
	private ProdDAOImpl() {
		
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
	
	public static ProdDAOImpl getInstance() {
		if(prodDao == null) {
			prodDao = new ProdDAOImpl();
		}
		return prodDao;
	}
	
	
	
	
	
	@Override
	public List<ProdVO> combox1List() {
		
		List<ProdVO> comb1 = new ArrayList<ProdVO>();
		try {
			comb1=smc.queryForList("prodHw.selectprod");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comb1;
	}

	@Override
	public List<ProdMainVO> combox2List(String mv) {
		List<ProdMainVO> comb2 = new ArrayList<ProdMainVO>();
		try {
			comb2=smc.queryForList("prodHw.selectprod2",mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comb2;
	}
	
}
