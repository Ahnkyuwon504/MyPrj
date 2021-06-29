package subway.dao;


import subway.domain.Subway;

public interface SubwayDao {
	Subway create(); // 데이터베이스에서 정보 뽑아서 map, time, visit 만든 객체 생성
	void createDB();
	void deleteDB();
	void insertDB(); // CSV파일에서 데이터베이스 생성
	

}
