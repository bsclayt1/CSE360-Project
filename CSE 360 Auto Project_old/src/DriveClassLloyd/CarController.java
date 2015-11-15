package DriveClassLloyd;

public class CarController {
   private int updateNumb;
   private double distance;
   private Engine engine;
   private FuelTank fuelTank;
   private double tankSize;

   public CarController(double fuel, double distance) {
      updateNumb = 0;
      this.distance = distance;
      engine = new Engine();
      fuelTank = new FuelTank(fuel);
      tankSize = fuel;
   }

   public double getSpeed() {
      return engine.getSpeed();
   }

   public double getDistance() {
      return distance;
   }

   public double getFuel() {
      return fuelTank.getFule();
   }

   public double getTankSize() {
	   return tankSize;
   }
   
   public double getDuration() {
      return updateNumb*.1;  //assumes .1s updates
   }

   public void updateEngine(int update) {
      updateNumb++;
      engine.updateSpeed(update);
      distance += engine.getSpeed() / 36000;
      fuelTank.updateFuel(distance);
      
   }
}