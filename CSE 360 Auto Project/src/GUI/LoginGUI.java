package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JPanel {
	
	private boolean pass;
	

	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		pass = false;
		
		setLayout(null);
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(205, 133, 56, 16);
		add(lblLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(170, 209, 97, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass = true;
			}
		});
		add(btnLogin);

	}
	
	public boolean getPassed() {
		return pass;
	}
}
