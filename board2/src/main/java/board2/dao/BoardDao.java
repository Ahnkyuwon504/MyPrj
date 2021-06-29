package board2.dao;

import java.util.List;

import board2.domain.Board;

public interface BoardDao {
	void create(Board board);
	Board selectOne(int id);
	List<Board> selectAll();
	void update(Board board);
	void delete(Board board);

}
