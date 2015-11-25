package radio;

import user.*;

import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import car.CarController;
import logging.RadioLog;
import logging.StationLog;

public class Radio {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private boolean radioOn; 
	private float speakerVol; //1:10 controls the volume 
	private boolean isSpeakerMute;
	private Station currentStation;
	private ArrayList<Station> availableStationList;
	private ArrayList<Station> favStationList;
	private ArrayList<String> radioLogs;
	private CarController car;
	private String band;
	private int location;
	private JSONObject cardata;
	private Date startDate;
	private long radioStartTime;
	private Date stationStartDate;
	private long stationStartTime;
	private User user;
	
	public Radio(User user, CarController car, ArrayList<String> radioLogs,  JSONObject cardata) {
		radioOn = false;
		speakerVol = MAX_VOL / 2;
		isSpeakerMute = false;
		currentStation = null;
		band = "FM";
		favStationList = user.getStations();
		availableStationList = new ArrayList<Station>();
		this.radioLogs = radioLogs;
		location = 0;
		populateStations();
		this.cardata = cardata;
		this.car = car;
		this.user = user;
		startDate = null;
		radioStartTime = 0;
		stationStartDate = null;
		stationStartTime = 0;
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
			File fin = new File("./stationdata.txt");
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
		setNextStation();
	}
	
	public void setNextStation() {
		int loc;
		if(currentStation != null)
			loc = availableStationList.indexOf(currentStation);
		else
			loc = 0;		
		for(int i = 1; i <= availableStationList.size(); i++) {
			if(isValidStation(availableStationList.get((loc + i) % availableStationList.size()))) {
				setStation(availableStationList.get((loc + i) % availableStationList.size()));
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
				setStation(availableStationList.get((loc - i) % availableStationList.size()));
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
		if(currentStation != station)
			makeStationLog();
		if(isValidStation(station)) {
			currentStation = station;
			stationStartDate = new Date();
			stationStartTime = System.currentTimeMillis();
		}
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
		if(power) { 
			if(currentStation == null)
				setNextStation();
			startDate = new Date();
			radioStartTime = System.currentTimeMillis();
		}
		else {
			makeStationLog();
			long durration = System.currentTimeMillis() - radioStartTime;
			addRadioLog(new RadioLog(user.getUserName(), startDate, durration));
			startDate = null;
			radioStartTime = 0;
		}
		radioOn = power;
	}
	
	@SuppressWarnings("unchecked")
	private void addRadioLog(RadioLog radioLog) {
		JSONArray radioLogsJSON = (JSONArray) cardata.get("radiologs");
		radioLogsJSON.add(radioLog.getJSONRadioLog());
		radioLogs.add(radioLog.toString());
		updateRadioLogs();
	}
	
	private void updateRadioLogs() {
		try {
			FileWriter fout = new FileWriter("./cardata.txt");
			fout.write(cardata.toString());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void makeStationLog() {
		if(currentStation != null) {
			long durration = System.currentTimeMillis() - stationStartTime;
			user.addStationLog(new StationLog(currentStation, stationStartDate, durration));
			stationStartDate = null;
			stationStartTime = 0;
		}
	}
}
