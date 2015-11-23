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

public class User {
	private String username;
	private String phoneNumber;
	private ArrayList<Contact> contacts;
	private ArrayList<Station> stations;
	private ArrayList<String> callLogs;
	private int userloc;
	private JSONArray users;
	private JSONObject userdata;
	
	public User(String username) {
		JSONParser parse = new JSONParser();
		File fin = new File("src/userdata.txt");
		if(fin.exists()) {
			try {
				users = (JSONArray) parse.parse(new FileReader(fin));
				userloc = 0;
				boolean found = false;
				do {
					userdata = (JSONObject) users.get(userloc);
					String fname = (String) userdata.get("username");
					if(username.equals(fname)) {
						found = true;
						this.username = username;
						phoneNumber = (String) userdata.get("phonenumber");
						
						this.contacts = new ArrayList<Contact>();
						JSONArray contacts = (JSONArray) userdata.get("contacts");
						for(int j = 0; j < contacts.size(); j++) {
							JSONObject contactdata = (JSONObject) contacts.get(j);
							String contactname = (String) contactdata.get("name");
							String contactnumber = (String) contactdata.get("phonenumber");
							Contact contact = new Contact(contactname, contactnumber);
							this.contacts.add(contact);
						}
						
						this.stations = new ArrayList<Station>();
						JSONArray stations = (JSONArray) userdata.get("stations");
						for(int j = 0; j < stations.size(); j++) {
							JSONObject stationdata = (JSONObject) stations.get(j);
							String stationname = (String) stationdata.get("name");
							float stationfreq = new Float((String) stationdata.get("frequency"));
							String stationband = (String) stationdata.get("band");
							int stationloc = new Integer((String) stationdata.get("location"));
							Station station = new Station(stationname, stationfreq, stationband, stationloc);
							this.stations.add(station);
						}
						
						this.callLogs = new ArrayList<String>();
						JSONArray callLogs = (JSONArray) userdata.get("calllogs");
						for(int j = 0; j < callLogs.size(); j++) {
							JSONObject callLogData = (JSONObject) callLogs.get(j);
							String number = (String) callLogData.get("numbercalled");
							String date = (String) callLogData.get("date");
							String durration = (String) callLogData.get("durration");
							String callLog = "Called Number: " + number + " on " + date + " for " + durration;
							this.callLogs.add(callLog);
						}
					}
					userloc++;
				} while(userloc < users.size() && !found);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("user file not found!");
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
		return stations;
	}
	
	public ArrayList<String> getCallLogs() {
		return callLogs;
	}
	
	@SuppressWarnings("unchecked")
	public void addCallLog(CallLog callLog) {
		JSONArray callLogsJSON = (JSONArray) userdata.get("calllogs");
		callLogsJSON.add(callLog.getJSONCallLog());
		callLogs.add(callLog.toString());
		updateUserData();
	}
	
	private void updateUserData() {
		try {
			FileWriter fout = new FileWriter("src/userdata.txt");
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
		s += "Stations: " + stations.size() + '\n';
		return s;
	}
}
