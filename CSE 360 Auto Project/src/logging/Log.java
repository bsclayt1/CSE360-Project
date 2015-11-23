package logging;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Log {
	private Date date;
	private long durration;
	
	public Log(Date date, long durration) {
		this.date = date;
		this.durration = durration;
	}
	
	@SuppressWarnings("deprecation")
	public String getDate() {
		int month = 1 + date.getMonth();
		int day = date.getDate();
		int year = 1901 + date.getYear();
		int hour = date.getHours();
		int min = date.getMinutes();
		int sec = date.getSeconds();
		//return month + "." + day + "." + year + " " + hour + ":" + min + ":" + sec;
		return String.format("%02d.%02d.%04d %02d:%02d:%02d", month, day, year, hour, min, sec);
	}
	
	public String getDurration() {
		return String.format("%02d min:%02d sec", TimeUnit.MILLISECONDS.toMinutes(durration), 
				TimeUnit.MILLISECONDS.toSeconds(durration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(durration)));
	}
}
