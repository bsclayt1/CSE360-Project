package GUI;

import DriveClassLloyd.CarController;

import GUI.LoginGUI;
import GUI.MainGUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;
	private CarController car;

	public MainFrame(CarController car) {
		this.car = car;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		mainPanel = new JPanel();
		loginPanel = new LoginGUI();
		mainGUIPanel = new MainGUI(car);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		waitForLogin();
		
	}
	
	private void waitForLogin() {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginPanel.getPassed()) {
					System.out.print("Login Passed");
					
					mainGUIPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
					mainGUIPanel.setLayout(new BorderLayout(0, 0));
					setContentPane(mainGUIPanel);
					repaint();
				}
				else
					System.out.println("Not Logged in");
			}
		});
		interval.start();
	}

}
