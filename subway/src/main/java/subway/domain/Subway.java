package subway.domain;

import java.util.ArrayList;

public class Subway {
	private ArrayList<ArrayList<String>> map;
	private int[][] time;
	private int[][] line;
	private int[] visit;
	private String[] isAvail;
	
	public Subway() {
		
	}
	
	public ArrayList<ArrayList<String>> getMap() {
		return map;
	}
	public void setMap(ArrayList<ArrayList<String>> map) {
		this.map = map;
	}
	public int[][] getTime() {
		return time;
	}
	public void setTime(int[][] time) {
		this.time = time;
	}
	public int[][] getLine() {
		return line;
	}
	public void setLine(int[][] line) {
		this.line = line;
	}

	public int[] getVisit() {
		return visit;
	}
	public void setVisit(int[] visit) {
		this.visit = visit;
	}

	public String[] getIsAvail() {
		return isAvail;
	}

	public void setIsAvail(String[] isAvail) {
		this.isAvail = isAvail;
	}
	
	

}
