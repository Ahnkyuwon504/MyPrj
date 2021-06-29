package board2.service;

import java.util.List;

import board2.dao.BoardDao;
import board2.dao.BoardDaoImpl;
import board2.domain.Board;

public class BoardServiceImpl implements BoardService {
	private static BoardServiceImpl instance = new BoardServiceImpl();
	private BoardServiceImpl() {
	}
	
	public static BoardServiceImpl getInstance() {
		return instance;
	}
	
	private BoardDao boardDao = BoardDaoImpl.getInstance();
	
	@Override
	public void create(Board board) {
		// TODO Auto-generated method stub
		boardDao.create(board);
		
	}

	@Override
	public Board selectOne(int id) {
		// TODO Auto-generated method stub
		return boardDao.selectOne(id);
	}

	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		return boardDao.selectAll();
				
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		boardDao.update(board);
	}

	@Override
	public void delete(Board board) {
		// TODO Auto-generated method stub
		boardDao.delete(board);
	}

}
