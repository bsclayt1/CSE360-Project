package car;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import map.Route;

public class CarController {
   private int updateNumb;
   private double distance;
   private Engine engine;
   private FuelTank fuelTank;
   private String state;
   private double accuspeed;
   private double avgspeed;
   private double maxspeed;
   private Route currentRoute;

   public CarController() {
	   double tanksize = 0;
	   double fuellevel = 0;
	   try {
			File fin = new File("./cardata.txt");
			JSONParser parse = new JSONParser();
			JSONObject cardata = (JSONObject) parse.parse(new FileReader(fin));
			tanksize = new Double((String) cardata.get("tanksize"));
			fuellevel = new Double((String) cardata.get("fuellevel"));
			distance = new Double((String) cardata.get("distance"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	   	updateNumb = 0;
		engine = new Engine();
		fuelTank = new FuelTank(tanksize, fuellevel);
		state = "Park";
		accuspeed = 0;
		avgspeed = 0;
		maxspeed = 0;
		currentRoute = null;
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

   public double getDuration() {
	   return updateNumb*.1;  //assumes .1s updates
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
   
   public void setDrive() {
	   this.state = "Drive";
   }
   
   public void setPark() {
	   this.state = "Park";
   }
   
   public void loginReset() {
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
   
   public Route getRoute() {
	   return currentRoute;
   }
}