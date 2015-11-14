class Engine {
   private final double MAXSPEED = 120;
   private double _currentSpeed;
   private double _accelRate;

   public class Engine() {
      _currentSpeed = 0;
      _accelRate = .5; //5mph per second (assuming .1s updates)
   }

   public double getSpeed() {
      return _currentSpeed;
   }

   //update: 1 for accelerate, 0 for coast, -1 for decelerate
   public void updateSpeed( int update ) { 
      //If MAXSPEED is reached, speed remains the same
      if( _currentSpeed != MAXSPEED && update == 1 ) { 
         _currentSpeed = _currentSpeed + _accelRate*update;
      }
   }

}