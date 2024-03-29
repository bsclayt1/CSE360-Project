package radio;

public class Station {
	private String name;
	private float freq;
	private String band;//FM or AM
	private int location;//1:3 specifies which route the station works at
	
	public Station(String name, float freq, String band, int location) {
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
	
	public String getFreqBand() {
		return freq + band;
	}
	
	public int getLocation(){
		return location;
	}
	
	public String toString() {
		if(band.equals("FM"))
			return name + " - " + String.format("%.1f", freq) + band;
		else
			return name + " - " + String.format("%.0f", freq) + band;
	}
	
	public boolean equals(Station eq) {
		return name.equals(eq.getName());
	}
}
