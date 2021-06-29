package subway.domain;

import java.util.ArrayList;

public class Print {
	int time;
	ArrayList<String> route;
	public Print(int time, ArrayList<String> route) {
		super();
		this.time = time;
		this.route = route;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public ArrayList<String> getRoute() {
		return route;
	}
	public void setRoute(ArrayList<String> route) {
		this.route = route;
	}
	

}
