package logging;

import java.util.Date;
import java.util.LinkedHashMap;

public class CallLog extends Log {
	private String numberCalled;
	
	public CallLog(String number, Date date, long milli) {
		super(date, milli);
		numberCalled = number;
	}
	
	public LinkedHashMap<String, String> getJSONCallLog() {
		LinkedHashMap<String, String> callLog = new LinkedHashMap<String, String>();
		callLog.put("numbercalled", numberCalled);
		callLog.put("date", getDate());
		callLog.put("durration", getDurration());
		return callLog;
	}
	
	public String toString() {
		return "Called Number: " + numberCalled + " on " + getDate() + " for " + getDurration();
	}
}
