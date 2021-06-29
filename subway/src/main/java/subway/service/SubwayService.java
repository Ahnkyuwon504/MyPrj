package subway.service;

import java.util.ArrayList;

import subway.domain.Print;
import subway.domain.Subway;

public interface SubwayService {
	Subway create();
	Print getTime(Subway subway, String start, String arrive);
	void insertDB(); 
	void createDB();
	void deleteDB();
	ArrayList<int[]> getLineAndTime(ArrayList<String> route);

}
