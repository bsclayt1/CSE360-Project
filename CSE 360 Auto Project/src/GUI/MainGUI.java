package GUI;

import car.CarController;
import user.*;
import phone.*;
import radio.*;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class MainGUI extends JPanel {
	private User user;
	private String carState;
	private UserManualGUI manualPanel;
	private RadioGUI radioPanel;
	private PhoneGUI phonePanel;
	private StatsGUI statsPanel;
	private MapGUI mapPanel;
	private boolean logout;
	
	public MainGUI(CarController car, User user) {
		this.user = user;
		carState = car.getState();
		Phone phone = new Phone(user, car);
		Radio radio = new Radio(user, car);
		car.setRadio(radio);
		car.setPhone(phone);
		
		setPreferredSize(new Dimension(750, 600));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel userPanel = new JPanel();
		userPanel.setPreferredSize(new Dimension(750, 35));
		add(userPanel);
		userPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel emptyPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) emptyPanel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		userPanel.add(emptyPanel);
		
		JToggleButton statswindowToggleButton = new JToggleButton("Stats");
		statswindowToggleButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		statswindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		statswindowToggleButton.setPreferredSize(new Dimension(65, 25));
		emptyPanel.add(statswindowToggleButton);
		
		JLabel userLabel = new JLabel(capitalize(user.getUserName()));
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPanel.add(userLabel);
		
		JLabel clockLabel = new JLabel("00:00:00");
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPanel.add(clockLabel);
		
		JPanel exitbuttonPanel = new JPanel();
		userPanel.add(exitbuttonPanel);
		exitbuttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		logout = false;
		logoutButton.setMargin(new Insets(2, 5, 2, 5));
		logoutButton.setPreferredSize(new Dimension(65, 25));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitbuttonPanel.add(logoutButton);
		
		JPanel mainPanel = new JPanel();
		
		add(mainPanel);
		
		mainPanel.setPreferredSize(new Dimension(750, 565));
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel indicatorPanel = new JPanel();
		indicatorPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
		mainPanel.add(indicatorPanel, BorderLayout.NORTH);
		indicatorPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel speedLabel = new JLabel("Speed: 0.0 mph"); //("Speed: " + car.getSpeed());
		speedLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(speedLabel);
		
		JLabel fuelpercLabel = new JLabel("Fuel Level: 100.00%"); //("Fuel Level: " + String.format("%.0f", car.getFuel() / car.getTankSize() * 100) + "%");
		fuelpercLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fuelpercLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(fuelpercLabel);
		
		JLabel drivestateLabel = new JLabel("Park"); //(car.getState());
		drivestateLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		drivestateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(drivestateLabel);
		
		JLabel odometerLabel = new JLabel("Miles: 0.0"); //("Miles: " + car.getDistance());
		odometerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		odometerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(odometerLabel);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);
		controlsPanel.setLayout(new GridLayout(1, 0, 5, 0));
		
		//Radio Button Image Import
		BufferedImage stopImage = null;
		ImageIcon stopIcon = null;
		JButton stopButton;
		InputStream stopIS = this.getClass().getClassLoader().getResourceAsStream("images/stopsign_icon.png");
		try {
			stopImage = ImageIO.read(stopIS);
			stopIcon = new ImageIcon(stopImage.getScaledInstance(40,40, Image.SCALE_SMOOTH));
			stopButton = new JButton(stopIcon);
		}
		catch(Exception e) {
			e.printStackTrace();
			stopButton = new JButton("STOP");
			stopButton.setForeground(Color.RED);
			stopButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		
		stopButton.setPreferredSize(new Dimension(70, 25));
		stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlsPanel.add(stopButton);
				
		JButton brakeButton = new JButton("Brake");
		brakeButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		controlsPanel.add(brakeButton);
		
		JPanel windowselectleftPanel = new JPanel();
		windowselectleftPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		mainPanel.add(windowselectleftPanel, BorderLayout.WEST);
		windowselectleftPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		//Radio Button Image Import
		BufferedImage radioImage = null;
		ImageIcon radioIcon = null;
		JToggleButton radiowindowToggleButton;
		InputStream radioIS = this.getClass().getClassLoader().getResourceAsStream("images/radio_icon.png");
		try {
			radioImage = ImageIO.read(radioIS);
			radioIcon = new ImageIcon(radioImage.getScaledInstance(40,40, Image.SCALE_SMOOTH));
			radiowindowToggleButton = new JToggleButton(radioIcon);
		}
		catch(Exception e) {
			e.printStackTrace();
			radiowindowToggleButton = new JToggleButton("Radio");
			
		}
		
		radiowindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		windowselectleftPanel.add(radiowindowToggleButton);
		
		//Phone Button Image Import
		BufferedImage phoneImage = null;
		ImageIcon phoneIcon = null;
		JToggleButton phonewindowToggleButton;
		InputStream phoneIS = this.getClass().getClassLoader().getResourceAsStream("images/phone_icon.png");
		try {
			phoneImage = ImageIO.read(phoneIS);
			phoneIcon = new ImageIcon(phoneImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			phonewindowToggleButton = new JToggleButton(phoneIcon);
		}
		catch(Exception e) {
			e.printStackTrace();
			phonewindowToggleButton = new JToggleButton("Phone");
			
		}
		
		phonewindowToggleButton.setMinimumSize(new Dimension(65, 25));
		phonewindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		phonewindowToggleButton.setHorizontalTextPosition(SwingConstants.CENTER);
		phonewindowToggleButton.setPreferredSize(new Dimension(65, 25));
		windowselectleftPanel.add(phonewindowToggleButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		CardLayout cards = new CardLayout(0, 0);
		centerPanel.setLayout(cards);
		
		manualPanel = new UserManualGUI();
		manualPanel.setPreferredSize(new Dimension(0, 0));
		manualPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		radioPanel = new RadioGUI(car, radio);
		radioPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		phonePanel = new PhoneGUI(phone);
		phonePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		mapPanel = new MapGUI(car);
		mapPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		statsPanel = new StatsGUI(user, car);
		statsPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		
		centerPanel.add(manualPanel, "Manual");
		centerPanel.add(radioPanel, "Radio");
		centerPanel.add(phonePanel, "Phone");
		centerPanel.add(mapPanel, "Map");
		centerPanel.add(statsPanel, "Stats");
		
		JPanel windowselectrightPanel = new JPanel();
		windowselectrightPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		mainPanel.add(windowselectrightPanel, BorderLayout.EAST);
		windowselectrightPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		//Manual Button Image Import
		BufferedImage manualImage = null;
		ImageIcon manualIcon = null;
		JToggleButton usermanualwindowToggleButton;
		InputStream manualIS = this.getClass().getClassLoader().getResourceAsStream("images/manual_icon.png");
		try {
			manualImage = ImageIO.read(manualIS);
			manualIcon = new ImageIcon(manualImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			usermanualwindowToggleButton = new JToggleButton(manualIcon);
		}
		catch(Exception e) {
			e.printStackTrace();
			usermanualwindowToggleButton = new JToggleButton("Manual");
			
		}
		
		usermanualwindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		usermanualwindowToggleButton.setPreferredSize(new Dimension(65, 25));
		windowselectrightPanel.add(usermanualwindowToggleButton);
		
		//Map Button Image Import
		BufferedImage mapImage = null;
		ImageIcon mapIcon = null;
		JToggleButton mapwindowToggleButton;
		InputStream mapIS = this.getClass().getClassLoader().getResourceAsStream("images/map_icon.png");
		try {
			mapImage = ImageIO.read(mapIS);
			mapIcon = new ImageIcon(mapImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
			mapwindowToggleButton = new JToggleButton(mapIcon);
		}
		catch(Exception e) {
			e.printStackTrace();
			mapwindowToggleButton = new JToggleButton("Map");
			
		}
		
		mapwindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		mapwindowToggleButton.setPreferredSize(new Dimension(65, 25));
		windowselectrightPanel.add(mapwindowToggleButton);
		
		radiowindowToggleButton.getModel().setSelected(true);
		cards.show(centerPanel, "Radio");
		
		JButton gasButton = new JButton("Accelerate");
		gasButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		controlsPanel.add(gasButton);
		
		speedhandler(car, gasButton, brakeButton, speedLabel);
		
		JPanel statePanel = new JPanel();
		controlsPanel.add(statePanel);
		
		JRadioButton parkRadioButton = new JRadioButton("Park");
		parkRadioButton.setMargin(new Insets(2, 2, 2, 9));
		parkRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		parkRadioButton.getModel().setSelected(true);
		parkRadioButton.getModel().setEnabled(false);
		statePanel.setLayout(new BorderLayout(0, 0));
		statePanel.add(parkRadioButton, BorderLayout.NORTH);
		parkRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		JRadioButton driveRadioButton = new JRadioButton("Drive");
		driveRadioButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		statePanel.add(driveRadioButton, BorderLayout.SOUTH);
		driveRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		labelupdater(car, speedLabel, odometerLabel, fuelpercLabel, drivestateLabel, parkRadioButton, driveRadioButton);
		stophandler(car, stopButton, parkRadioButton, driveRadioButton);
		buttonToggler(radiowindowToggleButton, phonewindowToggleButton, usermanualwindowToggleButton, mapwindowToggleButton, statswindowToggleButton, centerPanel, cards);
		stateHandler(car, parkRadioButton, driveRadioButton);
		logoutHandler(logoutButton);
		updateClock(clockLabel);
	}
	
	public String getUser() {
		return user.getUserName();
	}
	
	private String capitalize(String name) {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	private void speedhandler(CarController car, JButton gasButton, JButton brakeButton, JLabel speedLabel) {
		//Handles Acceleration, click & pressed	
		Timer gastrigger = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(1);
			}
		});
		gasButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(gasButton.getModel().isPressed())
					gastrigger.start();
				else
					gastrigger.stop();
			}
		});
		gasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(1);
			}
		});
		
		//Handles Braking, click & pressed
		Timer braketrigger = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(-1);
			}
		});
		brakeButton.getModel().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(brakeButton.getModel().isPressed())
					braketrigger.start();
				else
					braketrigger.stop();
			}
		});
		brakeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(-1);
			}
		});
	}
	
	private void labelupdater(CarController car, JLabel speed, JLabel odometer, JLabel fuelperc, JLabel state, JRadioButton park, JRadioButton drive) {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(0);
				carState = car.getState();
				speed.setText("Speed: " + car.getSpeed() + " mph");
				odometer.setText("Miles: " + String.format("%.1f", car.getDistance()));
				fuelperc.setText("Fuel Level: " + String.format("%.2f", car.getFuel() / car.getTankSize() * 100) + "%");
				state.setText(car.getState());
				if(car.getSpeed() > 0)
					park.getModel().setEnabled(false);
				else {
					if(park.getModel().isSelected())
						park.getModel().setEnabled(false);
					else
						park.getModel().setEnabled(true);
				}
			}
		});
		interval.start();
	}
	
	private void stophandler(CarController car, JButton stopButton, JRadioButton park, JRadioButton drive) {
		Timer stoptimer = new Timer(100, null); 
		stoptimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(-1);
				if(car.getSpeed() == 0) {
					stoptimer.stop();
					car.setPark();
					park.getModel().setSelected(true);
					park.getModel().setEnabled(true);
					drive.getModel().setSelected(false);
					drive.getModel().setEnabled(true);
				}
			}
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(car.getSpeed() > 0)
					stoptimer.start();
			}
		});
	}
	
	private void buttonToggler(JToggleButton radio, JToggleButton phone, JToggleButton manual, JToggleButton map, JToggleButton stats, JPanel center, CardLayout cards) {
		radio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(radio.getModel().isSelected()) {
					phone.getModel().setSelected(false);
					manual.getModel().setSelected(false);
					map.getModel().setSelected(false);
					stats.getModel().setSelected(false);
					
					phone.getModel().setEnabled(true);
					manual.getModel().setEnabled(true);
					map.getModel().setEnabled(true);
					stats.getModel().setEnabled(true);
					
					radio.getModel().setEnabled(false);
					cards.show(center, "Radio");
				}
			}
		});
		phone.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(phone.getModel().isSelected()) {
					radio.getModel().setSelected(false);
					manual.getModel().setSelected(false);
					map.getModel().setSelected(false);
					stats.getModel().setSelected(false);
					
					radio.getModel().setEnabled(true);
					manual.getModel().setEnabled(true);
					map.getModel().setEnabled(true);
					stats.getModel().setEnabled(true);
					
					phone.getModel().setEnabled(false);
					cards.show(center, "Phone");
				}
			}
		});
		manual.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(manual.getModel().isSelected()) {
					radio.getModel().setSelected(false);
					phone.getModel().setSelected(false);
					map.getModel().setSelected(false);
					stats.getModel().setSelected(false);
					
					radio.getModel().setEnabled(true);
					phone.getModel().setEnabled(true);
					map.getModel().setEnabled(true);
					stats.getModel().setEnabled(true);
					
					manual.getModel().setEnabled(false);
					cards.show(center, "Manual");
				}
			}
		});
		map.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(map.getModel().isSelected()) {
					radio.getModel().setSelected(false);
					phone.getModel().setSelected(false);
					manual.getModel().setSelected(false);
					stats.getModel().setSelected(false);
					
					radio.getModel().setEnabled(true);
					phone.getModel().setEnabled(true);
					manual.getModel().setEnabled(true);
					stats.getModel().setEnabled(true);
					
					map.getModel().setEnabled(false);
					cards.show(center, "Map");
				}
			}
		});
		stats.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(stats.getModel().isSelected()) {
					radio.getModel().setSelected(false);
					phone.getModel().setSelected(false);
					manual.getModel().setSelected(false);
					map.getModel().setSelected(false);
					
					radio.getModel().setEnabled(true);
					phone.getModel().setEnabled(true);
					manual.getModel().setEnabled(true);
					map.getModel().setEnabled(true);
					
					stats.getModel().setEnabled(false);
					cards.show(center, "Stats");
				}
			}
		});
	}
	
	private void stateHandler(CarController car, JRadioButton park, JRadioButton drive) {
		park.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setPark();
				drive.getModel().setSelected(false);
				drive.getModel().setEnabled(true);
			}
		});
		drive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setDrive();
				park.getModel().setSelected(false);
				drive.getModel().setEnabled(false);
			}
		});
	}
	
	private void logoutHandler(JButton logoutbutton) {
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout = true;
			}
		});
	}
	
	public boolean getLogout() {
		if(carState.equals("Drive"))
				logout = false;
		return logout;
	}
	
	private void updateClock(JLabel clock) {
		Timer interval = new Timer(1000, new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				clock.setText(String.format("%02d:%02d:%02d", date.getHours(), date.getMinutes(), date.getSeconds()));
			}
		});
		interval.start();
	}
}
