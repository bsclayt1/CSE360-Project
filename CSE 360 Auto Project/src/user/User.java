package user;

import phone.Contact;
import radio.Station;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logging.CallLog;
import logging.StationLog;

public class User {
	private String username;
	private String phoneNumber;
	private ArrayList<Contact> contacts;
	private ArrayList<Station> favStations;
	private ArrayList<String> callLogs;
	private ArrayList<String> stationLogs;
	private JSONArray users;
	private JSONObject userdata;
	private JSONArray callLogsJSON;
   	private JSONArray stationLogsJSON;
	
	public User(String username) {
		JSONParser parse = new JSONParser();
		try {
			File fin = new File("./userdata.txt");
			users = (JSONArray) parse.parse(new FileReader(fin));
			int i = 0;
			boolean found = false;
			do {
				userdata = (JSONObject) users.get(i);
				String fname = (String) userdata.get("username");
				if(username.equals(fname)) {
					found = true;
					this.username = username;
					phoneNumber = (String) userdata.get("phonenumber");
					callLogs = new ArrayList<String>();
					stationLogs = new ArrayList<String>();
					contacts = new ArrayList<Contact>();
					favStations = new ArrayList<Station>();
					fillCallLogs();
					fillStationLogs();
					fillContactsAndFavStations();
				}
				i++;
			} while(i < users.size() && !found);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	
	public ArrayList<Station> getStations() {
		return favStations;
	}
	
	public ArrayList<String> getCallLogs() {
		return callLogs;
	}
	
	public ArrayList<String> getStationLogs() {
		return stationLogs;
	}
	
	@SuppressWarnings("unchecked")
	public void addCallLog(CallLog callLog) {
		JSONArray callLogsJSON = (JSONArray) userdata.get("calllogs");
		callLogsJSON.add(callLog.getJSONCallLog());
		callLogs.add(callLog.toString());
		updateUserData();
	}
	
	@SuppressWarnings("unchecked")
	public void addStationLog(StationLog stationLog) {
		JSONArray stationLogsJSON = (JSONArray) userdata.get("stationlogs");
		stationLogsJSON.add(stationLog.getJSONStationLog());
		stationLogs.add(stationLog.toString());
		updateUserData();
	}
	
	private void updateUserData() {
		try {
			FileWriter fout = new FileWriter("./userdata.txt");
			fout.write(users.toString());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "";
		s += "User: " + username + '\n';
		s += "Phone Number: " + phoneNumber + '\n';
		s += "Contacts: " + contacts.size() + '\n';
		s += "Stations: " + favStations.size() + '\n';
		return s;
	}
	
	private void fillCallLogs() {
		callLogsJSON = (JSONArray) userdata.get("calllogs");
		for(int j = 0; j < callLogsJSON.size(); j++) {
			JSONObject callLogData = (JSONObject) callLogsJSON.get(j);
			String number = (String) callLogData.get("numbercalled");
			String date = (String) callLogData.get("date");
			String durration = (String) callLogData.get("durration");
			String callLog = "Called Number: " + number + " on " + date + " for " + durration;
			callLogs.add(callLog);
		}
	}
	
	private void fillStationLogs() {
		stationLogsJSON = (JSONArray) userdata.get("stationlogs");
		for(int j = 0; j < stationLogsJSON.size(); j++) {
			JSONObject stationLogData = (JSONObject) stationLogsJSON.get(j);
			String name = (String) stationLogData.get("name");
			String date = (String) stationLogData.get("date");
			String durration = (String) stationLogData.get("durration");
			String stationLog = "Listened to " + name + " on " + date + " for " + durration;
			stationLogs.add(stationLog);
		}
	}
	
	private void fillContactsAndFavStations() {
		JSONArray contactsJSON = (JSONArray) userdata.get("contacts");
		for(int j = 0; j < contactsJSON.size(); j++) {
			JSONObject contactdata = (JSONObject) contactsJSON.get(j);
			String contactname = (String) contactdata.get("name");
			String contactnumber = (String) contactdata.get("phonenumber");
			Contact contact = new Contact(contactname, contactnumber);
			contacts.add(contact);
		}
		JSONArray favStationsJSON = (JSONArray) userdata.get("stations");
		for(int j = 0; j < favStationsJSON.size(); j++) {
			JSONObject stationdata = (JSONObject) favStationsJSON.get(j);
			String stationname = (String) stationdata.get("name");
			float stationfreq = new Float((String) stationdata.get("frequency"));
			String stationband = (String) stationdata.get("band");
			int stationloc = new Integer((String) stationdata.get("location"));
			Station station = new Station(stationname, stationfreq, stationband, stationloc);
			favStations.add(station);
		}
	}
	
	public void clearCallLogs() {
		callLogsJSON.clear();
		fillCallLogs();
		updateUserData();
	}
	
	public void clearStationLogs() {
		stationLogsJSON.clear();
		fillStationLogs();
		updateUserData();
	}
}
