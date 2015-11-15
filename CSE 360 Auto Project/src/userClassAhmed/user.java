package userClassAhmed;

import RadioClassesAhmed.*;

public class user {
	String currentName;
	String names[];
	int passwords[];//4 digits?
	station stations[][];
	
	
	public user(){
		passwords = null;
		names = null;
		currentName = "sample User Name";
		stations = null;
	}


	public boolean login(String name, int pswd){
		if(names != null && passwords != null){
			for(int i = 0 ; i < names.length ; i ++){
				if(name == names[i]){
					if(pswd == passwords[i]){
						currentName = name;
						
						return true;
					}
				}
			}
		}
		
		
		return false;
	}
	
	public String getCurrentName() {
		return currentName;
	}


	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}


	public String[] getNames() {
		return names;
	}


	public void setNames(String[] names) {
		this.names = names;
	}


	public int[] getPasswords() {
		return passwords;
	}


	public void setPasswords(int[] passwords) {
		this.passwords = passwords;
	}


	public station[][] getStations() {
		return stations;
	}


	public void setStations(station[][] stations) {
		this.stations = stations;
	}

}
