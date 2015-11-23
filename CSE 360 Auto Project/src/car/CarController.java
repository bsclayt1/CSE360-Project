package car;

public class CarController {
   private int updateNumb;
   private double distance;
   private Engine engine;
   private FuelTank fuelTank;
   private String state;
   private double accuspeed;
   private double avgspeed;
   private double maxspeed;

   public CarController(double tanksize, double fuel, double distance) {
      updateNumb = 0;
      this.distance = distance;
      engine = new Engine();
      fuelTank = new FuelTank(tanksize, fuel);
      state = "Park";
      accuspeed = 0;
      avgspeed = 0;
      maxspeed = 0;
   }

   public double getSpeed() {
	   double speed = engine.getSpeed();
	   accuspeed += speed;
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
      fuelTank.updateFuel(distance);
      avgspeed = accuspeed / (double) updateNumb;
   }
}