class FuelTank {
   private final double CONSUME = 20; //consumption in mpg
   private double _fuelLevel; //initial fuel level
   
   public class FuelTank( double fuel ) {
      _fuelLevel = fuel;
   }
   
   public double getFuel( double distance ) {
      return _fuelLevel - distance/CONSUME;
   }
}