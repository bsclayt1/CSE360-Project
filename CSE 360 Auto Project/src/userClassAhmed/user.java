package helloworld;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.File;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class user {
	String currentName;
	String names[];
	String passwords[];//4 digits?
	station stations[][];
	
	
	public user(){
		
		JSONParser parser = new JSONParser();
		File f = new File("myFile.txt");
  	  if(f.exists()){
	      try {
	    	  
	    	  Object obj = parser.parse(new FileReader(f));
	    	  JSONArray root = (JSONArray) obj;

	  		passwords = new String[root.size()];
	  		names = new String[root.size()];
	  		
	    	  for(int i = 0 ; i < root.size(); i++){
	    		  names[i] = (String) ((JSONObject) root.get(i)).get("name");
	    		  System.out.println((String) ((JSONObject) root.get(i)).get("name"));
	    		  //System.out.println((String) ((JSONObject) root.get(i)).get("pswd"));
	    		  passwords[i] =  (String) ((JSONObject) root.get(i)).get("pswd");
	    		  
	    	  }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	      }else{
	    	  names = null;
	    	  passwords = null;
	      }
		currentName = names[0];
		stations = null;
	}


	public boolean login(String name, String pswd){
		if(names != null && passwords != null){
			for(int i = 0 ; i < names.length ; i ++){
				if(name.equals(names[i])){
					if(pswd.equals(passwords[i])){
						currentName = name;
						
						return true;
					}
				}
			}
		}
		
		
		return false;
	}
	
	public void writeToFile(){
		JSONObject obj = new JSONObject();

	      obj.put("name", "ahmed");
	      obj.put("pswd", "5566");
	     JSONObject obj7 = new JSONObject();

	      obj7.put("name", "coakley");
	      obj7.put("pswd", "1234");
	      
	      JSONObject obj8 = new JSONObject();

	      obj8.put("name", "ben");
	      obj8.put("pswd", "1234");
	      JSONObject obj9 = new JSONObject();

	      obj9.put("name", "robert");
	      obj9.put("pswd", "1234");

	      JSONArray root = new JSONArray();
	      root.add(obj);
	      root.add(obj7);
	      root.add(obj8);
	      root.add(obj9);
	      
	      try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("myFile.txt"), "utf-8"))) {
	    	    writer.write(root.toJSONString());
	    	} catch (IOException ex) {
	    	    // handle me
	    	}  
	}
	public String getCurrentName() {
		return currentName;
	}


	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}


	public String[] getNames() {
		return names;
	}


	public void setNames(String[] names) {
		this.names = names;
	}


	public String[] getPasswords() {
		return passwords;
	}


	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}


	public station[][] getStations() {
		return stations;
	}


	public void setStations(station[][] stations) {
		this.stations = stations;
	}

}
