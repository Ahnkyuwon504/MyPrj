package reservation.service;

import java.util.List;

import reservation.domain.Reservation;

public interface ReservationService {
	void create(Reservation reservation);
	Reservation selectOne(String date, int room);
	List<Reservation> selectAll();
	void delete(Reservation reservation);
	void update(Reservation reservation);
	String[] getDate(int wantDay);
	String checkAvail(String date, int room);
	String roomName(int room);
	void isAvailCreate(String date, String strroom, String name, 
	String addr, String tel, String name_money, String memo);
	int getMaxDay(String strMaxDay);

}
