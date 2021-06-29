package reservation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import reservation.domain.Reservation;

public class ReservationDaoImpl implements ReservationDao {
	
	private static ReservationDaoImpl instance = new ReservationDaoImpl();
	
	private ReservationDaoImpl() {
	}
	
	public static ReservationDaoImpl getInstance() {
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
	public void create(Reservation reservation) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();

			String QueryTxt = "insert into reservation values ('"
					+ reservation.getDate() + "', "
					+ reservation.getRoom() + ", '"
					+ reservation.getName() + "', '"
					+ reservation.getAddr() + "', '"
					+ reservation.getTel() + "', '"
					+ reservation.getName_money() + "', '"
					+ reservation.getMemo() + "');";
			stmt.execute(QueryTxt);

			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Reservation selectOne(String date, int room) {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			String QueryTxt = "select * from reservation where date='" + date + "' and room=" + room + ";";
			rset = stmt.executeQuery(QueryTxt);
			
			Reservation reservation = new Reservation();
			
			while (rset.next()) {
				reservation.setDate(rset.getString(1));
				reservation.setRoom(rset.getInt(2));
				reservation.setName(rset.getString(3));
				reservation.setAddr(rset.getString(4));
				reservation.setTel(rset.getString(5));
				reservation.setName_money(rset.getString(6));
				reservation.setMemo(rset.getString(7));
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return reservation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reservation> selectAll() {
		// TODO Auto-generated method stub
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/kopoctc", "root", "kopoctc");
			stmt = conn.createStatement();
			String QueryTxt = "select * from reservation";
			rset = stmt.executeQuery(QueryTxt);
			ArrayList<Reservation> listOfReservation = new ArrayList<Reservation>();
			
			while (rset.next()) {
				Reservation reservation = new Reservation();
				
				reservation.setDate(rset.getString(1));
				reservation.setRoom(rset.getInt(2));
				reservation.setName(rset.getString(3));
				reservation.setAddr(rset.getString(4));
				reservation.setTel(rset.getString(5));
				reservation.setName_money(rset.getString(6));
				reservation.setMemo(rset.getString(7));
				
				listOfReservation.add(reservation);
			}
			
			rset.close();
			stmt.close();
			conn.close();
			
			return listOfReservation;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

}
