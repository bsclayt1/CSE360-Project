package logging;

import java.util.Date;
import java.util.LinkedHashMap;

public class RadioLog extends Log {
	private String user;
	
	public RadioLog(String user, Date date, long durration) {
		super(date, durration);
		this.user = user;
	}
	
	public LinkedHashMap<String, String> getJSONRadioLog() {
		LinkedHashMap<String, String> radiolog = new LinkedHashMap<String, String>();
		radiolog.put("user", user);
		radiolog.put("date", getDate());
		radiolog.put("durration", getDurration());
		return radiolog;
	}
	
	public String toString() {
		return "User: " + user + " turned on radio at " + getDate() + " for " + getDurration();
	}
}
