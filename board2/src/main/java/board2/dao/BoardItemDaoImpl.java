package board2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board2.domain.BoardItem;

public class BoardItemDaoImpl implements BoardItemDao {
	private static BoardItemDaoImpl instance = new BoardItemDaoImpl();
	private BoardItemDaoImpl() {
	}
	public static BoardItemDaoImpl getInstance() {
		return instance;
	}
	
	static Connection conn;
	static Statement stmt;
	static ResultSet rset;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void create(BoardItem boarditem) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

			String QueryTxt = "insert into boarditem (title, date, content, boardid)"
					+ " values ("
					+ "'" + boarditem.getTitle() + "', "
					+ "'" + boarditem.getDate() + "', "
					+ "'" + boarditem.getContent() + "', "
					+ boarditem.getBoardid() + ");";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BoardItem selectOne(int itemid) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select * from boarditem where itemid=" + itemid + ";");
			BoardItem boardItem = new BoardItem();
			
			while (rset.next()) {
				boardItem.setItemid(rset.getInt(1));
				boardItem.setTitle(rset.getString(2));
				boardItem.setDate(rset.getString(3));
				boardItem.setContent(rset.getString(4));
				boardItem.setBoardid(rset.getInt(5));
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return boardItem;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<BoardItem> selectAll(int boardid) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			rset = stmt.executeQuery("select * from boarditem where parent is null and boardid=" + boardid + ";");
			ArrayList<BoardItem> listOfBoardItem = new ArrayList<BoardItem>();
			
			while (rset.next()) {
				BoardItem boardItem = new BoardItem();
				boardItem.setItemid(rset.getInt(1));
				boardItem.setTitle(rset.getString(2));
				boardItem.setDate(rset.getString(3));
				boardItem.setContent(rset.getString(4));
				boardItem.setBoardid(rset.getInt(5));
				
				listOfBoardItem.add(boardItem);
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return listOfBoardItem;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void update(BoardItem boarditem, String title, String content) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

			String QueryTxt = "update boarditem set title='" + title + 
					"', content='" + content + 
					"' where itemid=" + boarditem.getItemid() + ";";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(BoardItem boarditem) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

			String QueryTxt = "delete from boarditem" +
					" where itemid=" + boarditem.getItemid() + ";";
			stmt.execute(QueryTxt);
			
			QueryTxt = "delete from boarditem where parent=" + boarditem.getItemid() + ";";
			stmt.execute(QueryTxt);
			
			// 코멘트도 지워주자

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void createComment(BoardItem boarditem, BoardItem comment) {
		// TODO Auto-generated method stub
		try {
			boarditem.getComments().add(comment);
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

			String QueryTxt = "insert into boarditem (title, date, content, boardid, parent) values"
					+ " ('" + comment.getTitle() 
					+ "', '" + comment.getDate()
					+ "', '" + comment.getContent()
					+ "', " + comment.getBoardid()
					+ ", " + boarditem.getItemid() + ");"  ;
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<BoardItem> getAllComments(BoardItem boarditem) {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			String QueryTxt = "select * from boarditem where parent=" + boarditem.getItemid() + ";";
			rset = stmt.executeQuery(QueryTxt);
			ArrayList<BoardItem> listOfComment = new ArrayList<BoardItem>();
			
			while (rset.next()) {
				BoardItem boardItem = new BoardItem();
				boardItem.setItemid(rset.getInt(1));
				boardItem.setTitle(rset.getString(2));
				boardItem.setDate(rset.getString(3));
				boardItem.setContent(rset.getString(4));
				boardItem.setBoardid(rset.getInt(5));
				
				listOfComment.add(boardItem);
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return listOfComment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<BoardItem> selectAll(int boardid, String keyword) {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			String QueryTxt;
			
			if (keyword.length() == 0) {
				QueryTxt = "select * from boarditem where parent is null and boardid=" + boardid + ";";
			} else {
				QueryTxt = "select * from boarditem where parent is null and boardid=" + boardid + " and title like '%" + keyword + "%';";
			}
			rset = stmt.executeQuery(QueryTxt);
			ArrayList<BoardItem> listOfBoardItem = new ArrayList<BoardItem>();
			
			while (rset.next()) {
				BoardItem boardItem = new BoardItem();
				boardItem.setItemid(rset.getInt(1));
				boardItem.setTitle(rset.getString(2));
				boardItem.setDate(rset.getString(3));
				boardItem.setContent(rset.getString(4));
				boardItem.setBoardid(rset.getInt(5));
				
				listOfBoardItem.add(boardItem);
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return listOfBoardItem;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
