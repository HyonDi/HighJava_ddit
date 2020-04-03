package kr.or.ddit.basic;

import java.util.List;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao = BoardDaoImpl.getInstance();
	private static BoardServiceImpl instance;
	private BoardServiceImpl() {}
	
	
	public static BoardServiceImpl getInstance() {
		if(instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return boardDao.getAllBoardList();
	}

	@Override
	public boolean getBoard(int board_no) {
		// TODO Auto-generated method stub
		return boardDao.getBoard(board_no);
	}

	@Override
	public int insertBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return boardDao.insertBoard(bv);
	}

	@Override
	public int updateBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return boardDao.updateBoard(bv);
	}

	@Override
	public int deletBoard(int board_no) {
		// TODO Auto-generated method stub
		return boardDao.deletBoard(board_no);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return boardDao.getSearchBoard(bv);
	}

}
