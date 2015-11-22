package logging;

import java.util.Date;
import java.util.LinkedHashMap;

public class CallLog extends Log {
	private String numberCalled;
	
	public CallLog(String number, Date date, long milli) {
		super(date, milli);
		numberCalled = number;
	}
	
	public String getNumberCalled() {
		return numberCalled;
	}
	
	public LinkedHashMap<String, String> getJSONCallLog() {
		LinkedHashMap<String, String> callLog = new LinkedHashMap<String, String>();
		callLog.put("numbercalled", getNumberCalled());
		callLog.put("date", getDate());
		callLog.put("durration", getDurration());
		return callLog;
	}
	
	public String toString() {
		return "Called Number: " + getNumberCalled() + " on " + getDate() + " for " + getDurration();
	}
}
