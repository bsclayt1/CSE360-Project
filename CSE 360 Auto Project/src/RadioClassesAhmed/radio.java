package RadioClassesAhmed;

//Created by Ahmed Moussa on 10/25/2015 12:30PM
//import station.java
public class radio {

	int volume;//1:10 controls the volume 
	station currentStation;
	station stations[];
	station favorites[];
	int sizeOfFavorites;
	
	public radio(){
		volume = 5;
		sizeOfFavorites = 0;
		stations = new station[30];
		favorites = new station[10];
		currentStation = new station();
		for(int i = 0 ; i < 30; i ++){
			stations[i] = new station();
		}
		for(int i = 0 ; i < 10; i ++){
			favorites[i] = new station();
		}
	}

	public void addToFav(station station){
		favorites[sizeOfFavorites++] = station;
	}
	public void changeStation(station station){
		currentStation = station;
	}
	
	//getters + setters
	public int getVolume() {
		return volume;
	}

	//this setter (setVolume) edits the volume by accepting either 1 or -1 and adding/subtracting the volume
	public void setVolume(int volumeEdit) {
		this.volume += volumeEdit;
	}

	public station getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(station currentStation) {
		this.currentStation = currentStation;
	}

	public station[] getStations() {
		return stations;
	}

	public void setStations(station[] stations) {
		this.stations = stations;
	}

	public station[] getFavorites() {
		return favorites;
	}

	public void setFavorites(station[] favorites) {
		this.favorites = favorites;
	}
	
}
