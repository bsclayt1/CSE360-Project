import GUI.*;
import car.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String args[]) {
		changeLook();
		CarController car = new CarController();
		MainFrame guiframe = new MainFrame(car);
		SwingUtilities.updateComponentTreeUI(guiframe);
		guiframe.setVisible(true);
	}
	
	private static void changeLook() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}