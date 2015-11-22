package phone;

import user.*;
import logging.CallLog;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Phone {
	private final float MAX_VOL = 10;
	private final float MIN_VOL = 0;
	
	private User user;
	private String phoneNumber;
	private String numberCalled;
	private boolean onCall;
	private float speakerVol;
	private float micVol;
	private boolean isSpeakerMute;
	private boolean isMicMute;
	private ArrayList<Contact> contactList;
	private long callStartTime;
	
	public Phone(User user) {
		this.user = user;
		phoneNumber = user.getPhoneNumber();
		numberCalled = "";
		onCall = false;
		speakerVol = MAX_VOL / 2;
		micVol = MAX_VOL / 2;
		isSpeakerMute = false;
		isMicMute = false;
		contactList = user.getContacts();
		callStartTime = 0;
	}
	
	public String getPhoneNum() {
		return phoneNumber;
	}
	
	public String getNumberCalled() {
		return numberCalled;
	}
	
	public boolean getOnCall() {
		return onCall;
	}

	public String getSpeakerVol() {
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
	
	public String getMicVol() {
		if(!isMicMute) {
			if(micVol == MAX_VOL)
				return "MAX";
			if(micVol == MIN_VOL)
				return "MIN";
			return String.format("%.1f", micVol);
		}
		else
			return "MUTE";
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
	
	public void speakerMute() {
		isSpeakerMute = true;
	}
	
	public void speakerUnmute() {
		isSpeakerMute = false;
	}
	
	public void micMute() {
		isMicMute = true;
	}
	
	public void micUnmute() {
		isMicMute = false;
	}
	
	public void makeCall(String numberCalled) {
		this.numberCalled = numberCalled;
		callStartTime = System.currentTimeMillis();
		onCall = true;
	}
	
	public String getCallDuration() {
		if (onCall) {
			long millis = System.currentTimeMillis() - callStartTime;
			String durration = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
					TimeUnit.MILLISECONDS.toSeconds(millis)
					- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
			return durration;
		}
		else
			return "N/A";
	}
	
	public void endCall() {
		if(onCall) {
			long durration = System.currentTimeMillis() - callStartTime;
			user.addCallLog(new CallLog(numberCalled, new Date(), durration));
			numberCalled = "";
			onCall = false;
		}
	}
}	
