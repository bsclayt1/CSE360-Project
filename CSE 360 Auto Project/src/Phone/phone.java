package phone;

import user.*;

import java.util.ArrayList;

public class Phone {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private String phoneNumber;
	private float speakerVol;
	private float micVol;
	private ArrayList<Contact> contactList;
	private long callStartTime;
	private long callEndTime;
	
	public Phone(User user) {
		phoneNumber = user.getPhoneNumber();
		speakerVol = MAX_VOL / 2;
		micVol = MAX_VOL / 2;
		contactList = user.getContacts();
		callStartTime = 0;
		callEndTime = 0;
	}
	
	public String getPhoneNum() {
		return phoneNumber;
	}

	public float getSpeakerVol() {
		return speakerVol;
	}
	
	public float getMicVol() {
		return micVol;
	}
	
	public ArrayList<Contact> getContactList() {
		return contactList;
	}

	public void incSpeakerVol() {
		if(speakerVol < MAX_VOL)
			speakerVol += 0.5;
	}
	
	public void decSpeakerVol() {
		if(speakerVol > MIN_VOL)
			speakerVol -= 0.5;
	}
	
	public void incMicVol() {
		if(micVol < MAX_VOL)
			micVol += 0.5;
	}
	
	public void decMicVol() {
		if(micVol > MIN_VOL)
			micVol -= 0.5;
	}
	
	private String makeCall(String newPhoneNum){
		//this needs to validate a number and start the timer.
		//timer may not work in this class
		//timer may need to be in phone GUI
		phoneNumber = newPhoneNum;	
		
		return "Calling " + phoneNumber;
	}
}	
