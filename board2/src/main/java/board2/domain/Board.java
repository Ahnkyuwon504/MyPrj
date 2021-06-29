package board2.domain;

public class Board {
	private int boardid;
	private String title;
	
	public Board(int boardid, String title) {
		this.boardid = boardid;
		this.title = title;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
