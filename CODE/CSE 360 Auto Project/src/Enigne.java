
class Engine { 
	private double speed;
	private double accel;
	
	public Engine() {
		speed = 0;
		accel = 0;
	}
	
	public void setspeed(double newspeed) {
		speed = newspeed;
	}
	
	public void setaccel(double newaccel) {
		accel = newaccel;
	}
	
	public double getspeed() {
		return speed;
	}
	
	public double getaccel() {
		return accel;
	}
}
