package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.LprodVO;
import vo.ProdVO;

public class ProdDaoImpl implements ProdDao {
	// 싱글톤
	private static ProdDaoImpl instance;
	
	public static ProdDao getInstance() {
		if(instance == null) {
			instance = new ProdDaoImpl();
		}
		return instance;
	}
	
	// 연결
	private SqlMapClient smc;
	private ProdDaoImpl() {
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public List<ProdVO> getSerchProd(Map<String, String> map) {
		List<ProdVO> list = null;
		try {
			list = smc.queryForList("Prod.searchProd",map);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	

	@Override
	public List<LprodVO> getLprodName() {
		List<LprodVO> list = null;
		try {
			list = smc.queryForList("Prod.getLprodName");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProdVO> getProdName(String lprodName) {
		List<ProdVO> list = null;
		try {
			list = smc.queryForList("Prod.getProdName",lprodName);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
