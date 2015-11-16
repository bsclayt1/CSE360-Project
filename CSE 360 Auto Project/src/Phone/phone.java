package Phone;

public class phone {
	int phoneNum = 0;
	int volSpeaker = 0;
	int volMic = 0;
	int[] contactList = new int[10];
	int callDuration = 0;
	final int maxVol = 10;
	final int minVol = 0;
	int arrayLength = 0;
	
	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getVolSpeaker() {
		return volSpeaker;
	}

	public void setVolSpeaker(int volSpeaker) {
		this.volSpeaker = volSpeaker;
	}

	public int[] getContactList() {
		return contactList;
	}

	public void setContactList(int[] contactList) {
		this.contactList = contactList;
	}
	
	private String makeCall(int newPhoneNum){
		
		phoneNum = newPhoneNum;	
		
		return "Calling " + phoneNum;
	}
	
	private int incMicVol(){
		if(volMic == maxVol)
		{
			return volMic;
			
		}
		else
		{
			volMic++;
			return volMic;
		}
	}
	
	private int decMicVol(){
		if(volMic == minVol)
		{
			return volMic;
			
		}
		else
		{
			volMic--;
			return volMic;
		}
	}

	private int incSpeakVol(){
		if(volSpeaker == maxVol)
		{
			return volSpeaker;
		}
		else
		{
			volSpeaker++;
			return volSpeaker;
		}
	
	}
	
	private int decSpeakVol(){
		
		if(volSpeaker == minVol)
		{
			return volSpeaker;
		}
		else
		{
			volSpeaker--;
			return volSpeaker;
		}
		
	}
	
	public int[] contacts(){
		
		return contactList;
	}
	
	private void addContact(int addNumber){
		
		contactList[arrayLength] = addNumber;
		arrayLength++;		
		
	}
	
	private void delContact(int delNumber){ 
		
		for(int i = 0; i <= arrayLength; i++)
		{
			if(contactList[i] == delNumber)
			{
				contactList[i]= contactList[arrayLength];
				arrayLength--;				
			}
		}	
	}
	
	private int endCall(){
		return callDuration;
		
	}

}
