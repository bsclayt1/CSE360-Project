package user;

import java.io.FileReader;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LoginValidator {
	private File fin;
	private JSONArray logs;
	
	public LoginValidator() {
		JSONParser parse = new JSONParser();
		fin = new File("./logins.txt");
		if(fin.exists()) {
			try {
				logs = (JSONArray) parse.parse(new FileReader(fin));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Login file not found!");
	}
	
	public boolean validate(String name, String pass) {
		String fname = "";
		String fpass = "";
		for(int i = 0; i < logs.size(); i++) {
			fname = (String) ((JSONObject) logs.get(i)).get("username");
			fpass = (String) ((JSONObject) logs.get(i)).get("password");
			if(name.equals(fname) && pass.equals(fpass)) {
				return true;
			}
		}
		return false;
	}
}
