package map;

import java.util.LinkedHashMap;

public class Route {
	private String name;
	private int location;
	private double distance; //In miles
	private double traveled;
	
	public Route(String name, int location, double distance, double traveled) {
		this.name = name;
		this.location = location;
		this.distance = distance;
		this.traveled = traveled;
	}
	
	public String getName() {
		return name;
	}
	
	public int getLocation() {
		return location;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getTraveled() {
		return traveled;
	}
	
	public boolean checkRoute(int location) {
		return this.location == location;
	}
	
	public void updateTravled(double speed) {
		traveled += speed / 36000;
	}
	
	public LinkedHashMap<String, String> getJSONRoute() {
		LinkedHashMap<String, String> route = new LinkedHashMap<String, String>();
		route.put("name", name);
		route.put("location", Integer.toString(location));
		route.put("distance", String.format("%.02f", distance));
		route.put("traveled", String.format("%.02f", traveled));
		return route;
	}
	
	public String toString() {
		return name;
	}
}
