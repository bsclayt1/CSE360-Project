import DriveClassLloyd.*;
import GUI.*;

public class Main {
	
	public static CarController car;
	
	public static void main(String args[]) {
		
		
		
		car = new CarController(16, 0);
		
		MainGUI guiframe = new MainGUI(car);
		guiframe.setVisible(true);
	}

}
