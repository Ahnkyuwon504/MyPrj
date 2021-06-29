package board2.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import board2.dao.BoardItemDao;
import board2.dao.BoardItemDaoImpl;
import board2.domain.BoardItem;

public class BoardItemServiceImpl implements BoardItemService {
	private static BoardItemServiceImpl instance = new BoardItemServiceImpl();
	private BoardItemServiceImpl() {
	}
	
	public static BoardItemServiceImpl getInstance() {
		return instance;
	}
	
	private BoardItemDao boardItemDao = BoardItemDaoImpl.getInstance();
	
	@Override
	public void create(BoardItem boarditem) {
		// TODO Auto-generated method stub
		boardItemDao.create(boarditem);
		
	}

	@Override
	public BoardItem selectOne(int itemid) {
		// TODO Auto-generated method stub
		return boardItemDao.selectOne(itemid);
	}

	@Override
	public List<BoardItem> selectAll(int boardid) {
		// TODO Auto-generated method stub
		return boardItemDao.selectAll(boardid);
	}

	@Override
	public void update(BoardItem boarditem, String title, String content) {
		// TODO Auto-generated method stub
		boardItemDao.update(boarditem, title, content);
		
	}

	@Override
	public void delete(BoardItem boarditem) {
		// TODO Auto-generated method stub
		boardItemDao.delete(boarditem);
	}

	@Override
	public String getDate() {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date());
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
		
		return sf.format(cal.getTime());
	}

	@Override
	public List<BoardItem> getAllComments(BoardItem boarditem) {
		// TODO Auto-generated method stub
		return boardItemDao.getAllComments(boarditem);
	}

	@Override
	public void createComment(BoardItem boarditem, BoardItem comment) {
		// TODO Auto-generated method stub
		boardItemDao.createComment(boarditem, comment);
		
	}

	@Override
	public List<BoardItem> selectAll(int boardid, String keyword) {
		// TODO Auto-generated method stub
		return boardItemDao.selectAll(boardid, keyword);
	}

	@Override
	public String getKeyWord(String search) {
		// TODO Auto-generated method stub
		if (search == null) {
			return "";
		}
		return search;
	}

	@Override
	public int[] getFromAndTo(String strfrom, String strto, int boardItemHowMany, int onePage) {
		// TODO Auto-generated method stub
		int[] arrayOfFromAndTo = new int[2];
		
		int from = 0;
		int to = 0;
		
		if (strfrom == null) {
			from = 1;
			to = onePage;
		} else {
			from = Integer.parseInt(strfrom);
			to = Integer.parseInt(strto);
			
			if (from < 1) {
				from = 1;
				to = onePage;
			}
			
			if (from > boardItemHowMany) {
				from = (boardItemHowMany / onePage) * onePage + 1;
				to = from + onePage - 1;
			}
		}
		
		arrayOfFromAndTo[0] = from;
		arrayOfFromAndTo[1] = to;
		
		return arrayOfFromAndTo;
	}
}
