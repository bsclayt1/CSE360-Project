package radio;

import user.*;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
//import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import car.CarController;

public class Radio {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private boolean radioOn; 
	private float speakerVol; //1:10 controls the volume 
	private boolean isSpeakerMute;
	private Station currentStation;
	private ArrayList<Station> availableStationList;
	private ArrayList<Station> favStationList;
	private String band;
	private int location;
	private CarController car;
	
	public Radio(User user, CarController car){
		radioOn = false;
		speakerVol = MAX_VOL / 2;
		isSpeakerMute = false;
		currentStation = null;
		band = "FM";
		favStationList = user.getStations();
		availableStationList = new ArrayList<Station>();
		this.car = car;
		location = 0;
		populateStations();
	}
	
	public String getSpeakerVol() {
		if(radioOn) {
			if(!isSpeakerMute) {
				if(speakerVol == MAX_VOL)
					return "MAX";
				if(speakerVol == MIN_VOL)
					return "MIN";
				return String.format("%.1f", speakerVol);
			}
			else
				return "MUTE";
		}
		else
			return "Off";
	}
	
	public Station getCurrentStation() {
		return currentStation;
	}
	
	public ArrayList<Station> getFavStationList() {
		return favStationList;
	}
	
	public ArrayList<Station> getStationList() {
		return availableStationList;
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
	
	public void speakerMute() {
		isSpeakerMute = true;
	}
	
	public void speakerUnmute() {
		isSpeakerMute = false;
	}
	
	private void populateStations() {
		try {
			File fin = new File("src/stationdata.txt");
			JSONParser parse = new JSONParser();
			JSONArray stations = (JSONArray) parse.parse(new FileReader(fin));
			for(int i = 0; i < stations.size(); i++) {
				JSONObject stationdata = (JSONObject) stations.get(i);
				String stationname = (String) stationdata.get("name");
				float stationfreq = new Float((String) stationdata.get("frequency"));
				String stationband = (String) stationdata.get("band");
				int stationloc = new Integer((String) stationdata.get("location"));
				Station station = new Station(stationname, stationfreq, stationband, stationloc);
				availableStationList.add(station);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBand(String band) {
		this.band = band;
	}
	
	public void setNextStation() {
		int loc;
		if(currentStation != null)
			loc = availableStationList.indexOf(currentStation);
		else
			loc = 0;		
		for(int i = 1; i <= availableStationList.size(); i++) {
			if(isValidStation(availableStationList.get((loc + i) % availableStationList.size()))) {
				currentStation = availableStationList.get((loc + i) % availableStationList.size());
				return;
			}
		}
		currentStation = null;
	}
	
	public void setPrevStation() {
		int loc;
		if(currentStation != null)
			loc = availableStationList.indexOf(currentStation) + availableStationList.size();
		else
			loc = 0 + availableStationList.size();
		for(int i = 1; i <= availableStationList.size(); i++) {
			if(isValidStation(availableStationList.get((loc - i) % availableStationList.size()))) {
				currentStation = availableStationList.get((loc - i) % availableStationList.size());
				return;
			}
		}
		currentStation = null;
	}
	
	private boolean isValidStation(Station station) {
		if(station != null)
			return (station.getLocation() == location) && (station.getBand().equals(band));
		else
			return false;
	}
	
	public void updateLocation() {
		location = car.getRoute().getLocation();
	}
	
	public void setStation(Station station) {
		if(isValidStation(station))
			currentStation = station;
		else
			currentStation = null;
	}
	
	public boolean isOn() {
		return radioOn;
	}
	
	public void validateCurrentStation() {
		if(!isValidStation(currentStation))
			currentStation = null;
	}
	
	public void setPower(boolean power) {
		if(power && (currentStation == null))
			setNextStation();
		radioOn = power;
	}
}
