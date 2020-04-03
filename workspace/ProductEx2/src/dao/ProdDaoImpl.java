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

import vo.ProdVO;

public class ProdDaoImpl implements ProdDao{

	private static ProdDaoImpl dao;
	Charset charset;
	Reader rd;
	SqlMapClient smc;
	
	private ProdDaoImpl() {
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
	
	public static ProdDaoImpl getInstance() {
		if(dao == null) {
			dao = new ProdDaoImpl();
		}
		return dao;
	}
	
	
	@Override
	public List<ProdVO> getCom1List() {
		
		List<ProdVO> com1List = new ArrayList<ProdVO>();
		
		try {
			com1List = smc.queryForList("prodTest.getCom1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com1List;
	}

	@Override
	public List<ProdVO> getCom2List(String com2) {
		
		List<ProdVO> com2List = new ArrayList<>();
		
		try {
			com2List = smc.queryForList("prodTest.getCom2", com2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com2List;
	}

}
