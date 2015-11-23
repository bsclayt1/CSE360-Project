import GUI.*;
import car.*;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;

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
		
		car = new CarController(tanksize, fuellevel, distance);
		MainFrame guiframe = new MainFrame(car);
		guiframe.setVisible(true);
	}

}