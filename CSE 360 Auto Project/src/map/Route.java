package map;

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
}
