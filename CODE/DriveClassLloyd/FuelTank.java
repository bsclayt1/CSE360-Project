package DriveClassLloyd;

public class FuelTank {
   private final double CONSUME = 20; //consumption in mpg
   private double fuelLevel; //initial fuel level
   
   public FuelTank(double fuel) {
      fuelLevel = fuel;
   }
   
   public double getFuel(double distance) {
      return fuelLevel - distance/CONSUME;
   }
}