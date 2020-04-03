package kr.or.ddit.product.Dao;

import java.util.List;

import kr.or.ddit.product.Vo.ProductInfoVO;
import kr.or.ddit.product.Vo.ProductVO;

public interface ProductDAO {
	
	public List<ProductVO> combo1List ();

	public List<ProductInfoVO> combo2List(String mv);

}
