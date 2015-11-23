package map;

public class Route {
	private int location;
	private double distance; //In miles
	private double traveled;
	
	public Route(int location, double distance, double traveled) {
		this.location = location;
		this.distance = distance;
		this.traveled = traveled;
	}
	
	public int getLocation() {
		return location;
	}
	
	public boolean checkRoute(int location) {
		return this.location == location;
	}
	
	public void updateTravled(double speed) {
		traveled += speed / 36000;
	}
}
