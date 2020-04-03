package kr.or.ddit.product.Service;

import java.util.List;

import kr.or.ddit.product.Vo.ProductInfoVO;
import kr.or.ddit.product.Vo.ProductVO;

public interface ProductService {

	
	public List<ProductVO> combo1List();
	public List<ProductInfoVO> combo2List(String mv);
}
