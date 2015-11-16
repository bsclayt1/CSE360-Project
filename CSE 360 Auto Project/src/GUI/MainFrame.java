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

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel mainPanel;
	private LoginGUI loginPanel;
	private MainGUI mainGUIPanel;

	public MainFrame(CarController car) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		CardLayout cards = new CardLayout(0, 0);
		mainPanel.setLayout(cards);
		setContentPane(mainPanel);
		loginPanel = new LoginGUI();
		mainGUIPanel = new MainGUI(car);
		mainPanel.add(loginPanel, "Login");
		mainPanel.add(mainGUIPanel, "Main");
		cards.show(mainPanel, "Login");
		switchOnLogin(mainPanel, cards);
		
	}
	
	private void switchOnLogin(JPanel main, CardLayout cards) {
		Timer interval = new Timer(100, null);
		interval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginPanel.getPassed()) {
					//System.out.print("Logged In\n");
					cards.show(main, "Main");
					interval.stop();
				}
			}
		});
		interval.start();
	}
}
