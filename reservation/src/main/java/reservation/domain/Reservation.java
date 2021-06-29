package reservation.domain;

public class Reservation {
	private String date;
	private int room;
	private String name;
	private String addr;
	private String tel;
	private String name_money;
	private String memo;
	
	public Reservation() {
		
	}
	
	

	public Reservation(String date, int room, String name, String addr, String tel, String name_money, String memo) {
		this.date = date;
		this.room = room;
		this.name = name;
		this.addr = addr;
		this.tel = tel;
		this.name_money = name_money;
		this.memo = memo;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName_money() {
		return name_money;
	}

	public void setName_money(String name_money) {
		this.name_money = name_money;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
