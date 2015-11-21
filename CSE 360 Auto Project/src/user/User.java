package user;

import phone.Contact;
import radio.Station;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class User {
	private String username;
	private String phoneNumber;
	private ArrayList<Contact> contacts;
	private ArrayList<Station> stations;
	
	public User(String name) {
		JSONParser parse = new JSONParser();
		File fin = new File("src/userdata.txt");
		if(fin.exists()) {
			try {
			JSONArray users = (JSONArray) parse.parse(new FileReader(fin));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Login file not found!");
		this.username = name;
		phoneNumber = "480-555-1234";
		contacts = null;
		stations = null;
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
}
