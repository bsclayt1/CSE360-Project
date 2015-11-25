package logging;

import java.util.Date;
import java.util.LinkedHashMap;

import radio.Station;

public class StationLog extends Log {
	private Station station;

	public StationLog(Station station, Date date, long durration) {
		super(date, durration);
		this.station = station;
	}
	
	public LinkedHashMap<String, String> getJSONStationLog() {
		LinkedHashMap<String, String> stationlog = new LinkedHashMap<String, String>();
		stationlog.put("station", station.getName());
		stationlog.put("date", getDate());
		stationlog.put("durration", getDurration());
		return stationlog;
	}
	
	public String toString() {
		return "Listened to " + station.getName() + " on " + getDate() + " for " + getDurration();
	}

}
