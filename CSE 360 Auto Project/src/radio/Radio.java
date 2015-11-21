package radio;

import user.*;

import java.util.ArrayList;

public class Radio {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private float speakerVol; //1:10 controls the volume 
	private Station currentStation;
	private ArrayList<Station> stationList;
	
	public Radio(User user){
		speakerVol = MAX_VOL / 2;
		currentStation = null;
		stationList = user.getStations();
	}
	
	public float getSpeakerVol() {
		return speakerVol;
	}
	
	public Station getCurrentStation() {
		return currentStation;
	}
	
	public ArrayList<Station> getStationList() {
		return stationList;
	}

	public void changeStation(Station station){
		currentStation = station;
	}

	public void incSpeakerVol() {
		if(speakerVol < MAX_VOL)
			speakerVol += 0.5;
	}
	
	public void decSpeakerVol() {
		if(speakerVol > MIN_VOL)
			speakerVol -= 0.5;
	}
}
