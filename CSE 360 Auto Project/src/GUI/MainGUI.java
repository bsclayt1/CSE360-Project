package GUI;

//awt & swing imports
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import car.CarController;

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

	private JPanel manualPanel;
	private JPanel radioPanel;
	private JPanel phonePanel;
	private JPanel mapPanel;
	private boolean logout;
	
	public MainGUI(CarController car, String user) {
		setPreferredSize(new Dimension(750, 585));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel userPanel = new JPanel();
		userPanel.setPreferredSize(new Dimension(750, 35));
		add(userPanel);
		userPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel emptyPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) emptyPanel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		userPanel.add(emptyPanel);
		
		JButton statsButton = new JButton("Stats");
		statsButton.setMargin(new Insets(2, 5, 2, 5));
		statsButton.setPreferredSize(new Dimension(65, 25));
		emptyPanel.add(statsButton);
		
		JLabel userLabel = new JLabel(user);
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userPanel.add(userLabel);
		
		JPanel exitbuttonPanel = new JPanel();
		userPanel.add(exitbuttonPanel);
		exitbuttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
		
		JButton logoutButton = new JButton("Logout");
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
		
		mainPanel.setPreferredSize(new Dimension(750, 550));
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel indicatorPanel = new JPanel();
		indicatorPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
		mainPanel.add(indicatorPanel, BorderLayout.NORTH);
		indicatorPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel speedLabel = new JLabel("Speed: 0.0 mph"); //("Speed: " + car.getSpeed());
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(speedLabel);
		
		JLabel fuelpercLabel = new JLabel("Fuel Level: 100.00%"); //("Fuel Level: " + String.format("%.0f", car.getFuel() / car.getTankSize() * 100) + "%");
		fuelpercLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(fuelpercLabel);
		
		JLabel drivestateLabel = new JLabel("Park"); //(car.getState());
		drivestateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(drivestateLabel);
		
		JLabel odometerLabel = new JLabel("Miles: 0.0"); //("Miles: " + car.getDistance());
		odometerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(odometerLabel);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);
		controlsPanel.setLayout(new GridLayout(1, 0, 5, 0));
		
		JButton stopButton = new JButton("STOP");
		stopButton.setForeground(Color.RED);
		stopButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		stopButton.setPreferredSize(new Dimension(70, 25));
		stopButton.setHorizontalTextPosition(SwingConstants.CENTER);
		stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlsPanel.add(stopButton);
		
		JButton gasButton = new JButton("Accelerate");
		controlsPanel.add(gasButton);
				
		JButton brakeButton = new JButton("Brake");
		controlsPanel.add(brakeButton);
		
		JPanel windowselectleftPanel = new JPanel();
		windowselectleftPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		mainPanel.add(windowselectleftPanel, BorderLayout.WEST);
		windowselectleftPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JToggleButton radiowindowToggleButton = new JToggleButton("Radio");
		radiowindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		windowselectleftPanel.add(radiowindowToggleButton);
		
		JToggleButton phonewindowToggleButton = new JToggleButton("Phone");
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
		manualPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		radioPanel = new RadioGUI();
		radioPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		phonePanel = new PhoneGUI();
		phonePanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		mapPanel = new MapGUI();
		mapPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 2), new LineBorder(new Color(0, 0, 0), 2)));
		
		centerPanel.add(manualPanel, "Manual");
		centerPanel.add(radioPanel, "Radio");
		centerPanel.add(phonePanel, "Phone");
		centerPanel.add(mapPanel, "Map");
		
		JPanel windowselectrightPanel = new JPanel();
		windowselectrightPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		mainPanel.add(windowselectrightPanel, BorderLayout.EAST);
		windowselectrightPanel.setLayout(new GridLayout(0, 1, 0, 5));
		
		JToggleButton usermanualwindowToggleButton = new JToggleButton("Manual");
		usermanualwindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		usermanualwindowToggleButton.setPreferredSize(new Dimension(65, 25));
		usermanualwindowToggleButton.getModel().setSelected(true);
		cards.show(centerPanel, manualPanel.getName());
		windowselectrightPanel.add(usermanualwindowToggleButton);
		
		JToggleButton mapwindowToggleButton = new JToggleButton("Map");
		mapwindowToggleButton.setMargin(new Insets(2, 5, 2, 5));
		mapwindowToggleButton.setPreferredSize(new Dimension(65, 25));
		windowselectrightPanel.add(mapwindowToggleButton);
		
		JPanel statePanel = new JPanel();
		controlsPanel.add(statePanel);
		
		JRadioButton parkRadioButton = new JRadioButton("Park");
		parkRadioButton.getModel().setSelected(true);
		statePanel.setLayout(new BorderLayout(0, 0));
		statePanel.add(parkRadioButton, BorderLayout.NORTH);
		parkRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		JRadioButton driveRadioButton = new JRadioButton("Drive");
		statePanel.add(driveRadioButton, BorderLayout.SOUTH);
		driveRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		speedhandler(car, gasButton, brakeButton, speedLabel);
		labelupdater(car, speedLabel, odometerLabel, fuelpercLabel, drivestateLabel, parkRadioButton, driveRadioButton);
		stophandler(car, stopButton, parkRadioButton, driveRadioButton);
		buttonToggler(radiowindowToggleButton, phonewindowToggleButton, usermanualwindowToggleButton, mapwindowToggleButton, centerPanel, cards);
		stateHandler(car, parkRadioButton, driveRadioButton);
		logoutHandler(logoutButton);
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
				speed.setText("Speed: " + car.getSpeed() + " mph");
				odometer.setText("Miles: " + String.format("%.1f", car.getDistance()));
				fuelperc.setText("Fuel Level: " + String.format("%.2f", car.getFuel() / car.getTankSize() * 100) + "%");
				state.setText(car.getState());
				if(car.getSpeed() > 0)
					park.getModel().setEnabled(false);
				else
					park.getModel().setEnabled(true);
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
	
	private void buttonToggler(JToggleButton radio, JToggleButton phone, JToggleButton manual, JToggleButton map, JPanel center, CardLayout cards) {
		radio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(radio.getModel().isSelected()) {
					phone.getModel().setSelected(false);
					manual.getModel().setSelected(false);
					map.getModel().setSelected(false);
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
					cards.show(center, "Map");
				}
			}
		});
	}
	
	private void stateHandler(CarController car, JRadioButton park, JRadioButton drive) {
		park.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setPark();
				drive.getModel().setSelected(false);
			}
		});
		drive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setDrive();
				park.getModel().setSelected(false);
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
		return logout;
	}
}
