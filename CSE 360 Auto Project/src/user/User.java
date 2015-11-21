package user;

import phone.Contact;
import radio.Station;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.lang.Number;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class User {
	private String username;
	private String phoneNumber;
	private ArrayList<Contact> contacts;
	private ArrayList<Station> stations;
	
	public User(String username) {
		JSONParser parse = new JSONParser();
		File fin = new File("src/userdata.txt");
		if(fin.exists()) {
			try {
				JSONArray users = (JSONArray) parse.parse(new FileReader(fin));
				int i = 0;
				boolean found = false;
				do {
					JSONObject userdata = (JSONObject) users.get(i);
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
					}
					i++;
				} while(i < users.size() && !found);
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
	
	public String toString() {
		String s = "";
		s += "User: " + username + '\n';
		s += "Phone Number: " + phoneNumber + '\n';
		s += "Contacts: " + contacts.size() + '\n';
		s += "Stations: " + stations.size() + '\n';
		return s;
	}
}
