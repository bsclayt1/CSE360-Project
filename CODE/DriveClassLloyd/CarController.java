class CarController {
   private int _updateNumb;
   private double _distance;
   private Engine _engine;
   private FuelTank _fuelTank;

   public CarController( double fuel ) {
      _updateNumb = 0;
      _distance = 0;
      _engine = new Engine();
      _fuelTank = new FuelTank( fuel );
   }

   public double getSpeed() {
      return _engine.getSpeed();
   }

   public double getDistance() {
      return _distance;
   }

   public double getFuel() {
      return _fuelTank.getFuel( _distance );
   }

   public double getDuration() {
      return _updateNumb*.1;  //assumes .1s updates
   }

   public double updateDistance( int update ) {
      _updateNumb++;
      _engine.updateSpeed( update );
      _distance += _engine.getSpeed() / 36000;
   }
}