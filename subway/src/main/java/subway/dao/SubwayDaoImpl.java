package subway.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import subway.domain.Subway;

public class SubwayDaoImpl implements SubwayDao {

	private static SubwayDaoImpl instance = new SubwayDaoImpl();

	public static SubwayDaoImpl getInstance() {
		return instance;
	}

	private SubwayDaoImpl() {

	}

	static Connection conn;
	static Statement stmt;
	static ResultSet rset;

	static String[] isAvail;
	static int[][] time;
	static int[][] line;
	static int[] visit;
	static int[] lineLength;
	static ArrayList<ArrayList<String>> map;
	static ArrayList<ArrayList<String>> twoNodes;

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
	public void createDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/subway", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			stmt.execute("create table oneline (line int, name varchar(20));");
			stmt.execute("create table twoline (line int, name varchar(20));");
			stmt.execute("create table threeline (line int, name varchar(20));");
			stmt.execute("create table fourline (line int, name varchar(20));");
			stmt.execute("create table fiveline (line int, name varchar(20));");
			stmt.execute("create table sixline (line int, name varchar(20));");
			stmt.execute("create table sevenline (line int, name varchar(20));");
			stmt.execute("create table eightline (line int, name varchar(20));");
			stmt.execute("create table nineline (line int, name varchar(20));");
			stmt.execute("create table tenline (line int, name varchar(20));");
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/subway", "root", "kopoctc");
			Statement stmt = conn.createStatement();
			stmt.execute("drop table oneline;");
			stmt.execute("drop table twoline;");
			stmt.execute("drop table threeline;");
			stmt.execute("drop table fourline;");
			stmt.execute("drop table fiveline;");
			stmt.execute("drop table sixline;");
			stmt.execute("drop table sevenline;");
			stmt.execute("drop table eightline;");
			stmt.execute("drop table nineline;");
			stmt.execute("drop table tenline;");
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getCreatetQuery(int i) {
		String QueryTxt = "";
		switch (i) {
		case 0:
			QueryTxt = "select * from oneline;";
			break;
		case 1:
			QueryTxt = "select * from twoline;";
			break;
		case 2:
			QueryTxt = "select * from threeline;";
			break;
		case 3:
			QueryTxt = "select * from fourline;";
			break;
		case 4:
			QueryTxt = "select * from fiveline;";
			break;
		case 5:
			QueryTxt = "select * from sixline;";
			break;
		case 6:
			QueryTxt = "select * from sevenline;";
			break;
		case 7:
			QueryTxt = "select * from eightline;";
			break;
		case 8:
			QueryTxt = "select * from nineline;";
			break;
		case 9:
			QueryTxt = "select * from tenline;";
			break;
		}
		return QueryTxt;
	}

	static boolean isExist(String name) {
		for (int j = 0; j < isAvail.length; j++) {
			if (isAvail[j].equals(name))
				return true; // 새로운 역 등장시
		}
		return false;
	}

	static void insertArrayList(String start, String arrive) {
		for (int i = 0; i < map.size(); i++) {
			if (isAvail[i].equals(start)) {
				map.get(i).add(arrive);
			}
		}
	}

	static boolean isTwoNodes(int i, String name) {
		for (int j = 0; j < twoNodes.get(i).size(); j++) {
			if (name.equals(twoNodes.get(i).get(j)))
				return true;
		}
		return false;
	}

	@Override
	public Subway create() {

		try {
			map = new ArrayList<ArrayList<String>>();
			time = new int[1000][1000];
			line = new int[1000][1000];
			visit = new int[1000];
			isAvail = new String[1000];
			twoNodes = new ArrayList<ArrayList<String>>();
			lineLength = new int[10];
			Arrays.fill(isAvail, "");

			for (int i = 0; i < 10; i++) {
				twoNodes.add(new ArrayList<String>());
			}

			Subway subway = new Subway();

			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/subway", "root", "kopoctc");
			stmt = conn.createStatement();

			int cnt = 0;
			for (int i = 0; i < 10; i++) {
				String QueryTxt = getCreatetQuery(i);
				rset = stmt.executeQuery(QueryTxt);

				while (rset.next()) {
					String oneName = rset.getString(2);
					boolean isExist = isExist(oneName);

					if (!isExist) { // 새로운 역일 때
						map.add(new ArrayList<String>());
						isAvail[cnt] = oneName;
						cnt++;
					} else {
						twoNodes.get(i).add(oneName);
					}
				}
				lineLength[i] = cnt - 1;
			}

			for (int i = 0; i < 10; i++) {
				// boolean visit = false;
				int lineCnt = 0;
				String start = "";
				String arrive = "";
				String QueryTxt = getCreatetQuery(i);
				rset = stmt.executeQuery(QueryTxt);

				while (rset.next()) {
					start = arrive;
					arrive = rset.getString(2);

					if (lineCnt == 0) {
						lineCnt++;
						continue;
					}
					// if (isTwoNodes(i, arrive) && visit) continue;

					/*
					 * if (isTwoNodes(i, arrive) && !visit) { visit = true; }
					 */

					insertArrayList(start, arrive);
					insertArrayList(arrive, start);
				}
			}

			map.get(41).remove(2);
			map.get(78).remove(1);
			map.get(26).remove(3);
			
			/*
			 * for (int i = 0; i < map.size(); i++) { String oneStation = ""; for (int j =
			 * 0; j < map.get(i).size(); j++) { oneStation += map.get(i).get(j);
			 * oneStation+= " "; }
			 * 
			 * System.out.println((i+2) + " "+ isAvail[i]+ " 에서 출반해서 " +(
			 * i+3)+"번째에 해당하는 도착지 : " + oneStation); }
			 */
			 
			subway.setMap(map);
			subway.setTime(time);
			subway.setLine(line);
			subway.setVisit(visit);
			subway.setIsAvail(isAvail);

			rset.close();
			stmt.close();
			conn.close();
			return subway;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void insertDB() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.20:33060/subway", "root", "kopoctc");
			Statement stmt = conn.createStatement();

			File f = new File("C:\\Users\\kyuwon\\Desktop\\subway_0625.csv");
			BufferedReader br = new BufferedReader(new FileReader(f));

			String readtxt;
			if ((readtxt = br.readLine()) == null) {
				System.out.println("빈 파일입니다.");
				br.close();
				return;
			}

			while ((readtxt = br.readLine()) != null) {
				String[] name = readtxt.split(",");

				for (int i = 0; i < 10; i++) {
					if (!(name[i].equals("1"))) {
						String QueryTxt = getInsertQuery(i, name[i]);
						stmt.execute(QueryTxt);
					}
				}
			}

			br.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getInsertQuery(int i, String name) {
		String QueryTxt = "";
		switch (i) {
		case 0:
			QueryTxt = "insert into oneline values (1, '" + name + "');";
			break;
		case 1:
			QueryTxt = "insert into twoline values (2, '" + name + "');";
			break;
		case 2:
			QueryTxt = "insert into threeline values (3, '" + name + "');";
			break;
		case 3:
			QueryTxt = "insert into fourline values (4, '" + name + "');";
			break;
		case 4:
			QueryTxt = "insert into fiveline values (5, '" + name + "');";
			break;
		case 5:
			QueryTxt = "insert into sixline values (6, '" + name + "');";
			break;
		case 6:
			QueryTxt = "insert into sevenline values (7, '" + name + "');";
			break;
		case 7:
			QueryTxt = "insert into eightline values (8, '" + name + "');";
			break;
		case 8:
			QueryTxt = "insert into nineline values (9, '" + name + "');";
			break;
		case 9:
			QueryTxt = "insert into tenline values (10, '" + name + "');";
			break;
		}
		return QueryTxt;
	}
}
