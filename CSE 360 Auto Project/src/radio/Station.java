package radio;

public class Station {
	private String name;
	private float freq;
	private String band;//FM or AM
	private long location;//1:3 specifies which route the station works at
	
	public Station(String name, float freq, String band, long location) {
		this.name = name;
		this.freq = freq;
		this.band = band;
		this.location = location;
	}
	
	public String getName(){
		return name;
	}
	
	public float getFreq(){
		return freq;
	}
	
	public String getBand(){
		return band;
	}
	
	public long getLocation(){
		return location;
	}
}
