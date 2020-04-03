package 게시판MVC;

import java.util.List;


public class 게시판ServiceImpl implements 게시판Service {
//	사용할 Dao의 객체변수를 선언한다.
/*
 * iBatis 적용하며 제외.
 * 	private 게시판Dao bodDao;
	
	public 게시판ServiceImpl() {
		bodDao = new 게시판DaoImpl();
	}
*/	
	
	// 싱글톤 만드는 부분
	private static 게시판ServiceImpl instance;
	
	private 게시판ServiceImpl() {
		
	}
	public static 게시판ServiceImpl getinstance() {
		if(instance == null) {
			instance = new 게시판ServiceImpl();
		}
		return instance;
	}
	//----------------------------------------
	게시판DaoImpl bodDao = 게시판DaoImpl.getInstance();
	
	@Override
	public List<게시판VO> displayAllList() {
		return bodDao.displayAllList();
	}

	@Override
	public int write(게시판VO bod) {
		return bodDao.write(bod);
	}

	@Override
	public int update(게시판VO bod) {
		return bodDao.update(bod);
	}

	@Override
	public int delete(int bodNo) {
		return bodDao.delete(bodNo);
	}

	@Override
	public List<게시판VO> search(게시판VO bod) {
		return bodDao.search(bod);
	}
}
