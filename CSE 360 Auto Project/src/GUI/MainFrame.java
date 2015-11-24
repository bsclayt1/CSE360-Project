package GUI;

import GUI.LoginGUI;
import GUI.MainGUI;
import car.CarController;
import logging.CarLog;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;
	private CarController car;
	private Date startDate;
	private long carStartTime;
	private JSONObject cardata;
	private JSONArray carLogs;
	private JSONArray routes;
	private JSONArray radioLogs;

	public MainFrame(CarController car) {
		setResizable(false);
		this.car = car;
		parseCarData();
		carLogs = (JSONArray) cardata.get("carlogs");
		routes = (JSONArray) cardata.get("routes");
		radioLogs = (JSONArray) cardata.get("radiologs");
		startDate = null;
		carStartTime = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 640);
		loginPanel = new LoginGUI();
		loginPanel.setPreferredSize(new Dimension(750, 590));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.out.println("Exit Task Performed");
				if(!mainGUIPanel.getLogout()) {
					logoutTask();
				}
			}
		});
		
		getContentPane().add(loginPanel);
		switchOnLogin();
	}
	
	private void switchOnLogin() {
		Timer interval = new Timer(100, null);
		interval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginPanel.getPassed()) {
					startDate = new Date();
					carStartTime = System.currentTimeMillis();
					mainGUIPanel = new MainGUI(car, loginPanel.getUser(), carLogs, routes, radioLogs, cardata);
					mainGUIPanel.setPreferredSize(new Dimension(750, 590));
					getContentPane().removeAll();
					getContentPane().add(mainGUIPanel);
					revalidate();
					interval.stop();
					switchOnLogout();
				}
			}
		});
		interval.start();
	}
	
	private void switchOnLogout() {
		Timer interval = new Timer(100, null);
		interval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainGUIPanel.getLogout() && car.getState().equals("Park")) {
					logoutTask();
					loginPanel = new LoginGUI();
					loginPanel.setPreferredSize(new Dimension(750, 590));
					getContentPane().removeAll();
					getContentPane().add(loginPanel);
					revalidate();
					interval.stop();
					switchOnLogin();
				}
			}
		});
		interval.start();
	}
	
	@SuppressWarnings("unchecked")
	private void logoutTask() {
		long durration = System.currentTimeMillis() - carStartTime;
		CarLog carLog = new CarLog(mainGUIPanel.getUser(), car.getMaxSpeed(), car.getAvgSpeed(), startDate, durration);
		carLogs.add(carLog.getJSONCarLog());
		cardata.replace("fuellevel", String.format("%.02f", car.getFuel()));
		cardata.replace("distance", String.format("%.02f", car.getDistance()));
		cardata.replace("routes", mainGUIPanel.getRoutesJSON());
		try {
			FileWriter fout = new FileWriter("./cardata.txt");
			fout.write(cardata.toString());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parseCarData();
		startDate = null;
		carStartTime = 0;
		car.loginReset();
	}
	
	private void parseCarData() {
		try {
			File fin = new File("./cardata.txt");
			JSONParser parse = new JSONParser();
			cardata = (JSONObject) parse.parse(new FileReader(fin));
			carLogs = (JSONArray) cardata.get("carlogs");
			routes = (JSONArray) cardata.get("routes");
			radioLogs = (JSONArray) cardata.get("radiologs");
			//clearLogs();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void clearLogs() {
		carLogs.clear();
		radioLogs.clear();
	}
}
