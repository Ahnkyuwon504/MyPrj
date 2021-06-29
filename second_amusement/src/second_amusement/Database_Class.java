package second_amusement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Database_Class {
	private Connection conn;
	private Statement stmt;
	private Calendar cal;
	private SimpleDateFormat sdt;

	public Database_Class() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/ahnkyuwon",
					"root",
					"dksrbdnjs");
			stmt = conn.createStatement();
			cal = Calendar.getInstance();
			sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addData(List<List<String>> data) throws SQLException {
		try {
			
			for (int i = 0; i < data.size(); i++) {
				stmt.execute(String.format("INSERT INTO `report` (`date`, `ticket_type`,"
						+ "`age`, `amount`, `price`, `treat`) VALUES (now(), "
						+ "'%s', '%s', '%s', '%s', '%s')" , 
						data.get(i).get(1), data.get(i).get(2), data.get(i).get(3),
						data.get(i).get(4), data.get(i).get(5)));
			}
		} catch (Exception e) {
		}
	}
}
