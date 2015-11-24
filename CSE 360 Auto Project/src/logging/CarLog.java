package logging;

import java.util.Date;
import java.util.LinkedHashMap;

public class CarLog extends Log {
	private String user;
	private double maxspeed;
	private double avgspeed;
		
	public CarLog(String user, double maxspeed, double avgspeed, Date date, long durration) {
		super(date, durration);
		
		this.user = user;
		this.maxspeed = maxspeed;
		this.avgspeed = avgspeed;
	}
	
	public LinkedHashMap<String, String> getJSONCarLog() {
		LinkedHashMap<String, String> carlog = new LinkedHashMap<String, String>();
		carlog.put("user", user);
		//carlog.put("maxspeed", Double.toString(maxspeed));
		carlog.put("maxspeed", String.format("%.1f", maxspeed));
		//carlog.put("avgspeed", Double.toString(avgspeed));
		carlog.put("avgspeed", String.format("%.1f", avgspeed));
		carlog.put("date", getDate());
		carlog.put("durration", getDurration());
		return carlog;
	}
	
	public String toString() {
		return "User: " + user + ", Start Time: " + getDate() + " for " + getDurration()
			+ ", Max Speed: " + String.format("%.1f", maxspeed) + ", Average Speed: " + String.format("%.1f", avgspeed);
	}
}
