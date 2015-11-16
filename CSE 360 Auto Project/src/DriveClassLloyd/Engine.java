package DriveClassLloyd;

public class Engine {
   private final double MAXSPEED = 120;
   private double currentSpeed;
   private double accelRate;

   public Engine() {
      currentSpeed = 0;
      accelRate = 0.5; //5mph per second (assuming .1s updates)
   }

   public double getSpeed() {
      return currentSpeed;
   }

   //update: 1 for accelerate, 0 for coast, -1 for decelerate
   public void updateSpeed(int update, String state) { 
      //If MAXSPEED is reached, speed remains the same
	   if(state.equals("Drive")) {
	      if(currentSpeed < MAXSPEED && update == 1)
	    		  currentSpeed += accelRate;
		  else if(currentSpeed > 0 && update == -1)
			  currentSpeed -= accelRate;
	   }
   }
}