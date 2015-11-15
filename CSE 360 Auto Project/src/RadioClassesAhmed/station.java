package RadioClassesAhmed;

//Created by Ahmed Moussa on 10/25/2015 12:30PM

public class station {

	double freq;
	boolean band;//false for FM, true for AM
	String name;
	int location;//1:3 specifies which route the station works at
	
	public station(){
		freq = 0;
		band = false;
		name = "sample Radio Name";
		location = 1;
		
	}
	//setters
	public void setName(String name){
		this.name = name;
	}
	public void setFreq(double freq){
		this.freq = freq;
	}
	public void setBand(boolean band){
		this.band = band;
	}
	public void setLocation(int location){
		this.location = location;
	}
	//getters
	public String getName(){
		return this.name;
	}
	public double getFreq(){
		return this.freq;
	}
	public int getLocation(){
		return this.location;
	}
	public boolean getBand(){
		return this.band;
	}
}
