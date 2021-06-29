package reservation.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import reservation.dao.ReservationDaoImpl;
import reservation.domain.Reservation;

public class ReservationServiceImpl implements ReservationService {
	
	private  ReservationServiceImpl() {
	}
	
	private static ReservationServiceImpl instance = new ReservationServiceImpl();
	
	public static ReservationServiceImpl getInstance() {
		return instance;
	}
	
	private ReservationDaoImpl reservationDaoImpl = ReservationDaoImpl.getInstance();

	@Override
	public void create(Reservation reservation) {
		// TODO Auto-generated method stub
		reservationDaoImpl.create(reservation);
		
	}

	@Override
	public Reservation selectOne(String date, int room) {
		// TODO Auto-generated method stub
		return reservationDaoImpl.selectOne(date, room);
	}

	@Override
	public List<Reservation> selectAll() {
		// TODO Auto-generated method stub
		return reservationDaoImpl.selectAll();
	}

	@Override
	public void delete(Reservation reservation) {
		// TODO Auto-generated method stub
		reservationDaoImpl.delete(reservation);
	}

	@Override
	public void update(Reservation reservation) {
		// TODO Auto-generated method stub
		reservationDaoImpl.update(reservation);
	}

	@Override
	public String[] getDate(int wantDay) {
		// TODO Auto-generated method stub
		// wantDay == 0 => now
		// wantDay == 1 => tomorrow
		// dateAndDay[0] means date, dateAndDay[1] means day
		String[] dateAndDay = new String[2];
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(new Date());
		cal.add(Calendar.DATE, wantDay);
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 =new SimpleDateFormat("(E)");
		
		dateAndDay[0] = sf.format(cal.getTime());
		dateAndDay[1] = sf2.format(cal.getTime());
		
		return dateAndDay;
	}

	@Override
	public String checkAvail(String date, int room) {
		String reservable = "<a href='./reserve.jsp?key_date=" + date + "&key_room=" + room + "'>예약가능</a>";
		if (reservationDaoImpl.selectOne(date, room) != null) {
			if (reservationDaoImpl.selectOne(date, room).getName() != null) {
				reservable = reservationDaoImpl.selectOne(date, room).getName();
			}
		}
		return reservable;
	}

	@Override
	public String roomName(int room) {
		// TODO Auto-generated method stub
		switch(room) {
		case 1 : return "Suite";
		case 2 : return "Superior";
		case 3 : return "Standard";
		}
		return null;
	}

	@Override
	public void isAvailCreate(String date, String strroom, String name, String addr, String tel, String name_money,
			String memo) {
		// TODO Auto-generated method stub
		
		if (date != null) {
			Reservation reservation = new Reservation(
					date, Integer.parseInt(strroom), name, addr, tel, name_money, memo);
			reservationDaoImpl.create(reservation);
		}
	}

	@Override
	public int getMaxDay(String strMaxDay) {
		int maxDay;
		if (strMaxDay != null) {
			maxDay = Integer.parseInt(strMaxDay);
		} else {
			maxDay = ConstantValue.maxDay;
		}
		return maxDay;
	}
}
