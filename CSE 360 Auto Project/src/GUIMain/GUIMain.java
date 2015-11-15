package GUIMain;

import DriveClassLloyd.CarController;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;

public class GUIMain extends JFrame {

	private JPanel mainPanel;
	
	private String drivestate;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain guiframe = new GUIMain();
					guiframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GUIMain(CarController car) {
		
		drivestate = "Park";
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		mainPanel = new JPanel();
		mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel indicatorPanel = new JPanel();
		indicatorPanel.setBorder(new EmptyBorder(5, 0, 15, 0));
		mainPanel.add(indicatorPanel, BorderLayout.NORTH);
		indicatorPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel speedLabel = new JLabel("Speed: " + car.getSpeed());
		speedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(speedLabel);
		
		JLabel fuelpercLabel = new JLabel("Fuel Level: " + String.format("%.0f", car.getFuel() / car.getTankSize() * 100) + "%");
		fuelpercLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(fuelpercLabel);
		
		JLabel drivestateLabel = new JLabel(drivestate);
		drivestateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(drivestateLabel);
		
		JLabel odometerLabel = new JLabel("Miles: " + car.getDistance());
		odometerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		indicatorPanel.add(odometerLabel);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);
		controlsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton stopButton = new JButton("STOP");
		controlsPanel.add(stopButton);
		
		JButton gasButton = new JButton("Accelerate");
		controlsPanel.add(gasButton);
				
		JButton brakeButton = new JButton("Brake");
		controlsPanel.add(brakeButton);
		
		JPanel windowselectleftPanel = new JPanel();
		mainPanel.add(windowselectleftPanel, BorderLayout.WEST);
		windowselectleftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JToggleButton radiowindowToggleButton = new JToggleButton("Radio");
		windowselectleftPanel.add(radiowindowToggleButton);
		
		JToggleButton phonewindowToggleButton = new JToggleButton("Phone");
		windowselectleftPanel.add(phonewindowToggleButton);
		
		JPanel windowselectrightPanel = new JPanel();
		mainPanel.add(windowselectrightPanel, BorderLayout.EAST);
		windowselectrightPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JToggleButton usermanualwindowToggleButton = new JToggleButton("User Manual");
		windowselectrightPanel.add(usermanualwindowToggleButton);
		
		JToggleButton mapwindowToggleButton = new JToggleButton("Map");
		windowselectrightPanel.add(mapwindowToggleButton);
		
		speedhandler(car, gasButton, brakeButton, speedLabel);
		labelupdater(car, speedLabel, odometerLabel, fuelpercLabel);
		stophandler(car, stopButton);
	}
	
	private void speedhandler(CarController car, JButton gasButton, JButton brakeButton, JLabel speedLabel) {
		//Handles Acceleration, click & pressed	
		Timer gastrigger = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(1);
			}
		});
		//gastrigger.setCoalesce(true);
		//gastrigger.setRepeats(true);
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
		//braketrigger.setCoalesce(true);
		//braketrigger.setRepeats(true);
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
	
	private void labelupdater(CarController car, JLabel speedLabel, JLabel odometerLabel, JLabel fuelpercLabel) {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(0);
				speedLabel.setText("Speed: " + car.getSpeed() + " mph");
				odometerLabel.setText("Miles: " + String.format("%.1f", car.getDistance()));
				fuelpercLabel.setText("Fuel Level: " + String.format("%.2f", car.getFuel() / car.getTankSize() * 100) + "%");
			}
		});
		interval.start();
	}
	
	private void stophandler(CarController car, JButton stopButton) {
		Timer stoptimer = new Timer(100, null); 
		stoptimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.updateEngine(-1);
				if(car.getSpeed() == 0)
					stoptimer.stop();
			}
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(car.getSpeed() > 0)
					stoptimer.start();
			}
		});
	}
	
	private void buttonToggler(JToggleButton radio, JToggleButton phone, JToggleButton manual, JToggleButton, map) {
		radio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
			}
		});
	}
}
