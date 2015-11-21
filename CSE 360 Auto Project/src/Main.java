import GUI.*;
import car.*;

public class Main {
	
	public static CarController car;
	
	public static void main(String args[]) {
		
		
		
		car = new CarController(16, 0);
		
		MainFrame guiframe = new MainFrame(car);
		guiframe.setVisible(true);
	}

}
