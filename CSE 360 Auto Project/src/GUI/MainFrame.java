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

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;
	private CarController car;
	private Date startDate;
	private long carStartTime;

	public MainFrame(CarController car) {
		setResizable(false);
		this.car = car;
		startDate = null;
		carStartTime = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 640);
		loginPanel = new LoginGUI();
		mainGUIPanel = null;
		loginPanel.setPreferredSize(new Dimension(750, 590));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.out.println("Exit Task Performed");
				if(mainGUIPanel != null && !mainGUIPanel.getLogout()) {
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
					mainGUIPanel = new MainGUI(car, loginPanel.getUser());
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
	
	private void logoutTask() {
		car.addCarLog(new CarLog(mainGUIPanel.getUser(), car.getMaxSpeed(), car.getAvgSpeed(), startDate, carStartTime));
		car.logout();
		car.updateCarData();
		startDate = null;
		carStartTime = 0;
	}
}
