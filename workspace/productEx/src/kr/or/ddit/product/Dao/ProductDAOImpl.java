package kr.or.ddit.product.Dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.product.Vo.ProductInfoVO;
import kr.or.ddit.product.Vo.ProductVO;

public class ProductDAOImpl implements ProductDAO{
	
	private static ProductDAOImpl pddao;
	private SqlMapClient smc= null;
	private ProductDAOImpl() {

    	try {
    	Charset charset =Charset.forName("UTF-8");							//한글 깨짐 방지
		Resources.setCharset(charset);										//한글 깨짐 방지
		Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");	//ibatis의 설정 파일을 읽어온다(ibatis객체를 만들때 필요한 객체를설정하려고)
		smc =SqlMapClientBuilder.buildSqlMapClient(rd);		//ibatis를 쓰기위한 Client객체 생성
			rd.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static ProductDAOImpl getInstance() {
		if(pddao == null) {
			pddao = new ProductDAOImpl();
		}
		return pddao;
	}

	@Override
	public List<ProductVO> combo1List() {
		List<ProductVO> compd1 = new ArrayList<ProductVO>();
		try {
			compd1=smc.queryForList("productTest.selectpd");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return compd1;
	}
	@Override
	public List<ProductInfoVO> combo2List(String mv) {
		List<ProductInfoVO> compd2 = new ArrayList<ProductInfoVO>();
		try {
			compd2=smc.queryForList("productTest.selectpd2",mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return compd2;
	}


}
