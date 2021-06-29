package reservation.dao;

import java.util.List;

import reservation.domain.Reservation;

public interface ReservationDao {
	void create(Reservation reservation);
	Reservation selectOne(String date, int room);
	List<Reservation> selectAll();
	void delete(Reservation reservation);
	void update(Reservation reservation);

}
