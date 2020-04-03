package kr.or.ddit.product.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.product.Dao.ProductDAO;
import kr.or.ddit.product.Dao.ProductDAOImpl;
import kr.or.ddit.product.Vo.ProductInfoVO;
import kr.or.ddit.product.Vo.ProductVO;

public class ProductServiceImpl implements ProductService{
	
	
	private ProductDAOImpl pddao;
	private static ProductServiceImpl pdserv;
	private SqlMapClient smc= null;
	
	private ProductServiceImpl() {
		pddao = ProductDAOImpl.getInstance();
		
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
	
	public static ProductServiceImpl getInstance() {
		if(pdserv == null) {
			pdserv = new ProductServiceImpl();
		}
		return pdserv;
	}
	
	@Override
	public List<ProductVO> combo1List() {
		return pddao.combo1List();
	}

	@Override
	public List<ProductInfoVO> combo2List(String mv) {
		return pddao.combo2List(mv);
	}

}
