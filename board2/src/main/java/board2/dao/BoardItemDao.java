package board2.dao;

import java.util.List;

import board2.domain.BoardItem;

public interface BoardItemDao {
	void create(BoardItem boarditem);
	BoardItem selectOne(int itemid);
	List<BoardItem> selectAll(int boardid);
	List<BoardItem> selectAll(int boardid, String keyword);
	void update(BoardItem boarditem, String title, String content);
	void delete(BoardItem boarditem);
	List<BoardItem> getAllComments(BoardItem boarditem);
	void createComment(BoardItem boarditem, BoardItem comment);

}
