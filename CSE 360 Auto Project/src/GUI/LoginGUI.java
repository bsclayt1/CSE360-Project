package GUI;

//import userClassAhmed.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Component;

@SuppressWarnings("serial")
public class LoginGUI extends JPanel {
	
	private boolean pass;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	

	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		setPreferredSize(new Dimension(650, 550));
		pass = false;
		setLayout(new BorderLayout(0, 0));
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogin, BorderLayout.NORTH);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(loginPanel);
		
		JPanel fieldsPanel = new JPanel();
		loginPanel.add(fieldsPanel);
		fieldsPanel.setPreferredSize(new Dimension(250, 145));
		fieldsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel usernamePanel = new JPanel();
		fieldsPanel.add(usernamePanel);
		usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblUserName = new JLabel("User Name");
		usernamePanel.add(lblUserName);
		lblUserName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		usernameTextField = new JTextField();
		usernamePanel.add(usernameTextField);
		usernameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		usernameTextField.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		fieldsPanel.add(passwordPanel);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setPreferredSize(new Dimension(63, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordPanel.add(lblNewLabel);
		
		passwordTextField = new JTextField();
		passwordPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JPanel validatePanel = new JPanel();
		validatePanel.setPreferredSize(new Dimension(240, 60));
		fieldsPanel.add(validatePanel);
		validatePanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel logintextLabel = new JLabel("Please login");
		logintextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		logintextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		validatePanel.add(logintextLabel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		validatePanel.add(buttonPanel);
		
		JButton btnLogin = new JButton("Login");
		buttonPanel.add(btnLogin);
		btnLogin.setPreferredSize(new Dimension(70, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = usernameTextField.getText();
				String passw = passwordTextField.getText();
				//System.out.print("Entered Username: " + name + "\n");
				//System.out.print("Entered Password: " + passw + "\n");
				
				if(name.equals("robert") && passw.equals("1234")) {
					pass = true;
				}
				else {
					pass = false;
					logintextLabel.setText("login/password incorrect - Try again");
					
				}
				
				
			}
		});

	}
	
	public boolean getPassed() {
		return pass;
	}
}
