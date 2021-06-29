package subway.service;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import subway.dao.SubwayDao;
import subway.dao.SubwayDaoImpl;
import subway.domain.Print;
import subway.domain.Subway;

public class SubwayServiceImpl implements SubwayService {
	
	private static SubwayServiceImpl instance = new SubwayServiceImpl();
	
	public static SubwayServiceImpl getInstance() {
		return instance;
	}
	
	private SubwayServiceImpl() {
		
	}
	
	private SubwayDao subwayDao = SubwayDaoImpl.getInstance();

	@Override
	public Subway create() {
		// TODO Auto-generated method stub
		return subwayDao.create();
	}

	@Override
	public Print getTime(Subway subway, String start, String arrive) {
		
		ArrayList<ArrayList<String>> map = subway.getMap();
		//int[][] time = subway.getTime();
		//int[][] line = subway.getLine();
		boolean[] visit = new boolean[1000];
		String[] isAvail = subway.getIsAvail();
		// TODO Auto-generated method stub
		ArrayList<String> startArr = new ArrayList<String>();
		startArr.add(start);
		
		Queue<Print> q = new LinkedList<Print>();
		
		q.offer(new Print(0, startArr));
		visit[indexOfStation(start, isAvail)] = true;
		
		while (!q.isEmpty()) {
			Print nowPrint = q.poll();
			
			ArrayList<String> nowRoute = nowPrint.getRoute();
			String lastStation = nowRoute.get(nowRoute.size() - 1);
			int nowTime = nowPrint.getTime();
			
			if (lastStation.equals(arrive)) {
				return nowPrint;
			}
			
			for (String next : getNextStation(lastStation, map, isAvail)) {
				ArrayList<String> newRoute = new ArrayList<String>();
				
				for (int k = 0; k < nowRoute.size(); k++) {
					newRoute.add(nowRoute.get(k));
				}
				if (visit[indexOfStation(next, isAvail)]) continue;
				
				newRoute.add(next);
				visit[indexOfStation(next, isAvail)] = true;
				
				q.offer(new Print(nowTime + 1, newRoute));
			}
		}
		
		return null;
	}
	
	static ArrayList<String> getNextStation(String lastStation, ArrayList<ArrayList<String>> map, String[] isAvail) {
		
		ArrayList<String> nextStation = new ArrayList<String>();
		
		for (int i = 0; i <isAvail.length; i++) {
			if (lastStation.equals(isAvail[i])) {
				for (int j = 0; j < map.get(i).size(); j++)
				nextStation.add(map.get(i).get(j));
			}
		}
		return nextStation;
	}
	
	static int indexOfStation (String lastStation, String[] isAvail) {
		for (int i = 0; i < isAvail.length; i++) {
			if (lastStation.equals(isAvail[i])) return i;
		}
		return 0;
	}

	@Override
	public void insertDB() {
		// TODO Auto-generated method stub
		subwayDao.insertDB();
		
	}

	@Override
	public void createDB() {
		// TODO Auto-generated method stub
		subwayDao.createDB();
		
	}

	@Override
	public void deleteDB() {
		// TODO Auto-generated method stub
		subwayDao.deleteDB();
		
	}

	@Override
	public ArrayList<int[]> getLineAndTime(ArrayList<String> route) {
		// TODO Auto-generated method stub
		ArrayList<int[]> lineAndTime = new ArrayList<int[]>();
		String[][] map = getLineMap();
		
		for (int i = 0; i < route.size() - 1; i++) {
			int[] thisLineAndTime = new int[2];
			
			String start = route.get(i);
			String arrive = route.get(i + 1);
			
			thisLineAndTime[0] = whatLine(start, arrive, map);
			thisLineAndTime[1] = (int)getTime(whatLine(start, arrive, map));
			
			lineAndTime.add(thisLineAndTime);
		}
		return lineAndTime;
	}
	
	static double getTime(int line) {
		double time = 2.1;
		
		switch (line) {
		case 1 : time *= 1.0; break;
		case 2 : time *= 1.2; break;
		case 3 : time *= 1.1; break;
		case 4 : time *= 1.2; break;
		case 5 : time *= 1.0; break;
		case 6 : time *= 1.0; break;
		case 7 : time *= 1.1; break;
		case 8 : time *= 1.1; break;
		case 9 : time *= 1.0; break;
		case 10 : time *= 1.5; break;
		}
		return time;
	}
	
	static int whatLine(String start, String arrive, String[][] map) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10; j++) {
				if ((map[i + 1][j].equals(start) && map[i][j].equals(arrive)) || (map[i + 1][j].equals(arrive) && map[i][j].equals(start))) {
					return j + 1;
				}
			}
		}
		
		return 0;
	}

	static String[][] getLineMap() {
		// TODO Auto-generated method stub
		String[][] map = new String[1000][1000];
		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], "1");
		}
		
		try {
			File f = new File("C:\\Users\\kyuwon\\Desktop\\subway_0625.csv");
			BufferedReader br = new BufferedReader(new FileReader(f));

			String readtxt;
			if ((readtxt = br.readLine()) == null) {
				System.out.println("빈 파일입니다.");
				br.close();
				return null;
			}
			
			int cnt = 0;
			while ((readtxt = br.readLine()) != null) {
				String[] name = readtxt.split(",");

				for (int i = 0; i < 10; i++) {
					map[cnt][i] = name[i];
				}
				cnt++;
			}
			br.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getStationName(int i) {
		// TODO Auto-generated method stub
		String stationName = "";
		
		switch (i) {
		case 1 : stationName = "1호선"; break;
		case 2 : stationName = "2호선"; break;
		case 3 : stationName = "3호선"; break;
		case 4 : stationName = "4호선"; break;
		case 5 : stationName = "5호선"; break;
		case 6 : stationName = "6호선"; break;
		case 7 : stationName = "7호선"; break;
		case 8 : stationName = "8호선"; break;
		case 9 : stationName = "9호선"; break;
		case 10 : stationName = "중앙선"; break;
		}
		return stationName;
	}
	
	
}
