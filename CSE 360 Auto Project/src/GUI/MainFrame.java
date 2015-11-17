package GUI;

import DriveClassLloyd.CarController;

import GUI.LoginGUI;
import GUI.MainGUI;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;
	private CarController car;

	public MainFrame(CarController car) {
		setResizable(false);
		this.car = car;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 645);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(750, 590));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//CardLayout cards = new CardLayout(0, 0);
		//mainPanel.setLayout(cards);
		setContentPane(mainPanel);
		loginPanel = new LoginGUI();
		loginPanel.setPreferredSize(new Dimension(750, 590));
		mainPanel.add(loginPanel, "Login");
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
					FlowLayout flowLayout = (FlowLayout) mainGUIPanel.getLayout();
					flowLayout.setVgap(0);
					flowLayout.setHgap(0);
					mainPanel.remove(loginPanel);
					repaint();
					mainPanel.add(mainGUIPanel);
					repaint();
					//System.out.print("Logged In\n");
					//cards.show(mainPanel, "Main");
					interval.stop();
				}
			}
		});
		interval.start();
	}
}
