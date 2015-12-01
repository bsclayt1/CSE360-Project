package car;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import logging.CarLog;
import logging.RadioLog;
import map.Route;
import phone.Phone;
import radio.Radio;

public class CarController {
	private int updateNumb;
	private double distance;
	private Engine engine;
	private FuelTank fuelTank;
	private Radio radio;
	private Phone phone;
   	private String state;
   	private double accuspeed;
   	private double avgspeed;
   	private double maxspeed;
   	private Route currentRoute;
   	private JSONObject cardata;
   	private JSONArray carLogsJSON;
   	private JSONArray routesJSON;
   	private JSONArray radioLogsJSON;
   	private ArrayList<String> carLogs;
   	private ArrayList<Route> routes;
   	private ArrayList<String> radioLogs;

   	public CarController() {
	   	double tanksize = 0;
	   	double fuellevel = 0;
	   	radio = null;
	   	phone = null;
	   	try {
	   		File fin = new File("./cardata.txt");
			JSONParser parse = new JSONParser();
			cardata = (JSONObject) parse.parse(new FileReader(fin));
			tanksize = new Double((String) cardata.get("tanksize"));
			fuellevel = new Double((String) cardata.get("fuellevel"));
			distance = new Double((String) cardata.get("distance"));
			carLogsJSON = (JSONArray) cardata.get("carlogs");
			routesJSON = (JSONArray) cardata.get("routes");
			radioLogsJSON = (JSONArray) cardata.get("radiologs");
			parseCurrentRoute();
	   	}
		catch(Exception e) {
			e.printStackTrace();
		}
	   	carLogs = new ArrayList<String>();
		routes = new ArrayList<Route>();
		radioLogs = new ArrayList<String>();
		fillCarLogs();
		fillRoutes();
		fillRadioLogs();
	   	updateNumb = 0;
		engine = new Engine();
		fuelTank = new FuelTank(tanksize, fuellevel);
		state = "Park";
		accuspeed = 0;
		avgspeed = 0;
		maxspeed = 0;
   	}

   	public double getSpeed() {
	   	double speed = engine.getSpeed();
	   	if(speed > maxspeed)
		   	maxspeed = speed;
	   	return engine.getSpeed();
   	}

   	public double getDistance() {
	   	return distance;
   	}

   	public double getFuel() {
	   	return fuelTank.getFuel();
   	}
   
   	public double getTankSize() {
	   	return fuelTank.getTankSize();
   	}

   	public String getState() {
	   	return state;
   	}
   
   	public double getMaxSpeed() {
   		return maxspeed;
   	}
   
   	public double getAvgSpeed() {
	   	return avgspeed;
   	}
   
   	public ArrayList<String> getCarLogs() {
	   	return carLogs;
   	}
   
   	public ArrayList<String> getRadioLogs() {
	   	return radioLogs;
   	}
   
   	public ArrayList<Route> getRoutes() {
	   	return routes;
   	}
   	
   	public Radio getRadio() {
   		return radio;
   	}
   	
   	public Phone getPhone() {
   		return phone;
   	}
   
   	public void setDrive() {
	   	this.state = "Drive";
   	}
   
   	public void setPark() {
	   	this.state = "Park";
   	}
   	
   	public void setRadio(Radio radio) {
   		this.radio = radio;
   	}
   	
   	public void setPhone(Phone phone) {
   		this.phone = phone;
   	}
   	
   	public void setRoutesJSON(JSONArray routesJSON) {
   		this.routesJSON = routesJSON;
   	}
   
   	@SuppressWarnings("unchecked")
	public void logout() {
   		cardata.replace("fuellevel", String.format("%.02f", getFuel()));
		cardata.replace("distance", String.format("%.02f", getDistance()));
		cardata.replace("routes", routesJSON);
		cardata.replace("currentroute", currentRoute.getJSONRoute());
	   	accuspeed = 0;
	   	avgspeed = 0;
	   	maxspeed = 0;
   	}	
   
   	public void updateEngine(int update) {
	   	updateNumb++;
      	engine.updateSpeed(update, state);
      	distance += engine.getSpeed() / 36000;
      	accuspeed += engine.getSpeed();
  		fuelTank.updateFuel(distance);
      	avgspeed = accuspeed / (double) updateNumb;
   	}
   
   	public void setRoute(Route newRoute) {
   		currentRoute = newRoute;
   	}
   
   	public Route getCurrentRoute() {
   		return currentRoute;
   	}
   
   	@SuppressWarnings("unchecked")
	public void addRadioLog(RadioLog radioLog) {
		radioLogsJSON.add(radioLog.getJSONRadioLog());
		radioLogs.add(radioLog.toString());
		updateCarData();
	}
   	
   	@SuppressWarnings("unchecked")
	public void addCarLog(CarLog carLog) {
   		carLogsJSON.add(carLog.getJSONCarLog());
   		carLogs.add(carLog.toString());
   		updateCarData();
   	}
   	
   	public void fillCarLogs() {
		for(int i = 0; i < carLogsJSON.size(); i++) {
			JSONObject carlog = (JSONObject) carLogsJSON.get(i);
			String user = (String) carlog.get("user");
			String maxspeed = (String) carlog.get("maxspeed");
			String avgspeed = (String) carlog.get("avgspeed");
			String date = (String) carlog.get("date");
			String durration = (String) carlog.get("durration");
			String carlogString = "User: " + user + ", Start Time: " + date + " for " + durration
			+ ", Max Speed: " + maxspeed + ", Average Speed: " + avgspeed;
			carLogs.add(carlogString);
		}
	}
	
	public void fillRoutes() {
		for(int i = 0; i < routesJSON.size(); i++) {
			JSONObject route = (JSONObject) routesJSON.get(i);
			String name = (String) route.get("name");
			int location = new Integer((String) route.get("location"));
			double distance = new Double((String) route.get("distance"));
			double traveled = new Double((String) route.get("traveled"));
			Route routeList = new Route(name, location, distance, traveled);
			routes.add(routeList);
		}
	}
	
	public void fillRadioLogs() {
		for(int i = 0; i < radioLogsJSON.size(); i++) {
			JSONObject radioLog = (JSONObject) radioLogsJSON.get(i);
			String user = (String) radioLog.get("user");
			String date = (String) radioLog.get("date");
			String durration = (String) radioLog.get("durration");
			String radioLogString = "User: " + user + " turned on radio at " + date + " for " + durration;
			radioLogs.add(radioLogString);
		}
	}

   	public void updateCarData() {
		try {
			FileWriter fout = new FileWriter("./cardata.txt");
			fout.write(cardata.toString());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   	
   	public void clearCarLogs() {
   		carLogsJSON.clear();
   		fillCarLogs();
   		updateCarData();
   	}
   	
	public void clearRadioLogs() {
   		radioLogsJSON.clear();
   		fillCarLogs();
   		updateCarData();
   	}
   	
   	private void parseCurrentRoute() {
   		JSONObject route = (JSONObject) cardata.get("currentroute");
   		String name = (String) route.get("name");
		int location = new Integer((String) route.get("location"));
		double distance = new Double((String) route.get("distance"));
		double traveled = new Double((String) route.get("traveled"));
		Route currentRoute = new Route(name, location, distance, traveled);
   		this.currentRoute = currentRoute;
   	}
}