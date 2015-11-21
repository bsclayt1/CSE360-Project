package car;

public class FuelTank {
   private final double CONSUME = 20; //consumption in mpg
   private double tankSize;
   private double fuelLevel; //initial fuel level in gallons
   
   public FuelTank(double fuel) {
      fuelLevel = fuel;
      tankSize = fuel;
   }
   
   public double getFule() {
	   return fuelLevel;
   }
   
   public void updateFuel(double distance) {
      fuelLevel = tankSize - distance/CONSUME;
   }
}