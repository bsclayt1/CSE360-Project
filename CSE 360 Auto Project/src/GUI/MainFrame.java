package GUI;

import DriveClassLloyd.CarController;

import GUI.LoginGUI;
import GUI.MainGUI;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	//private JPanel mainPanel;
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;
	private CarController car;

	public MainFrame(CarController car) {
		setResizable(false);
		this.car = car;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 640);
		loginPanel = new LoginGUI();
		loginPanel.setPreferredSize(new Dimension(750, 590));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//CardLayout cards = new CardLayout(0, 0);
		//mainPanel.setLayout(cards);
		getContentPane().add(loginPanel);
		//loginPanel = new LoginGUI();
		//loginPanel.setPreferredSize(new Dimension(750, 590));
		//mainPanel.add(loginPanel, "Login");
		//mainPanel.add(mainGUIPanel, "Main");
		//cards.show(mainPanel, "Login");
		switchOnLogin();
		
	}
	
	private void switchOnLogin() {
		Timer interval = new Timer(100, null);
		interval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginPanel.getPassed()) {
					mainGUIPanel = new MainGUI(car, "Robert");
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
				if(mainGUIPanel.getLogout()) {
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
}
