package board2.domain;

import java.util.List;

public class BoardItem {
	private int itemid;
	private String title;
	private String date;
	private String content;
	private int boardid;
	private List<BoardItem> comments;
	
	public BoardItem() {
		
	}

	public BoardItem(String title, String date, String content, int boardid) {
		this.title = title;
		this.date = date;
		this.content = content;
		this.boardid = boardid;
	}

	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<BoardItem> getComments() {
		return comments;
	}
	public void setComments(List<BoardItem> comments) {
		this.comments = comments;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	
	
	

}
