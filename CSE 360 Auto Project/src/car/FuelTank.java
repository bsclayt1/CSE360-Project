package car;

public class FuelTank {
   private final double CONSUME = 20; //consumption in mpg
   private double tankSize;
   private double fuelLevel; //initial fuel level in gallons
   
   public FuelTank(double tanksize, double fuel) {
      fuelLevel = fuel;
      this.tankSize = tanksize;
   }
   
   public double getFuel() {
	   return fuelLevel;
   }
   
   public double getTankSize() {
	   return tankSize;
   }
   
   public void updateFuel(double distance) {
      fuelLevel = tankSize - distance/CONSUME;
   }
}