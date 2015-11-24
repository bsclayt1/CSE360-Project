import GUI.*;
import car.*;

import java.io.FileReader;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.io.File;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	public static void main(String args[]) {
		CarController car;
		double tanksize = 0, fuellevel = 0, distance = 0;
		
		try {
			File fin = new File("src/cardata.txt");
			JSONParser parse = new JSONParser();
			JSONObject cardata = (JSONObject) parse.parse(new FileReader(fin));
			tanksize = new Double((String) cardata.get("tanksize"));
			fuellevel = new Double((String) cardata.get("fuellevel"));
			distance = new Double((String) cardata.get("distance"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		changeLook();
		car = new CarController(tanksize, fuellevel, distance);
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