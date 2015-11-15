import DriveClassLloyd.*;
import GUIMain.*;

public class Main {
	
	public static CarController car;
	
	public static void main(String args[]) {
		
		car = new CarController(0);
		
		GUIMain guiframe = new GUIMain(car);
		guiframe.setVisible(true);
	}

}
