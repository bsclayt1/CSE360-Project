package DriveClassLloyd;

public class CarController {
   private int updateNumb;
   private double distance;
   private Engine engine;
   private FuelTank fuelTank;

   public CarController( double fuel ) {
      updateNumb = 0;
      distance = 0;
      engine = new Engine();
      fuelTank = new FuelTank(fuel);
   }

   public double getSpeed() {
      return engine.getSpeed();
   }

   public double getDistance() {
      return distance;
   }

   public double getFuel() {
      return fuelTank.getFuel( distance );
   }

   public double getDuration() {
      return updateNumb*.1;  //assumes .1s updates
   }

   public double updateDistance( int update ) {
      updateNumb++;
      engine.updateSpeed( update );
      distance += engine.getSpeed() / 36000;
      
      return distance;
   }
}