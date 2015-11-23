package radio;

import user.*;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
//import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Radio {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private boolean radioOn; 
	private float speakerVol; //1:10 controls the volume 
	private boolean isSpeakerMute;
	private Station currentStation;
	private ArrayList<Station> availableStationList;
	private ArrayList<Station> favStationList;
	
	public Radio(User user){
		radioOn = true;
		speakerVol = MAX_VOL / 2;
		isSpeakerMute = false;
		currentStation = null;
		favStationList = user.getStations();
		availableStationList = new ArrayList<Station>();
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
}
