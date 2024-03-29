package GUI;

import user.*;

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
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LoginGUI extends JPanel {
	
	private User user;
	private boolean passflag;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	
	public LoginGUI() {
		user = null;
		passflag = false;
		
		setPreferredSize(new Dimension(650, 550));
		setLayout(new BorderLayout(0, 0));
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLogin, BorderLayout.NORTH);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(loginPanel);
		
		JPanel fieldsPanel = new JPanel();
		loginPanel.add(fieldsPanel);
		fieldsPanel.setPreferredSize(new Dimension(250, 170));
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
		
		passwordTextField = new JPasswordField();
		passwordPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JPanel validatePanel = new JPanel();
		validatePanel.setPreferredSize(new Dimension(240, 85));
		fieldsPanel.add(validatePanel);
		validatePanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel logintextLabel = new JLabel("Please login");
		logintextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		logintextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		validatePanel.add(logintextLabel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(120, 55));
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		validatePanel.add(buttonPanel);
		
		loginButton = new JButton("Login");
		buttonPanel.add(loginButton);
		loginButton.setPreferredSize(new Dimension(70, 35));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTextField.getText();
				@SuppressWarnings("deprecation")
				String password = passwordTextField.getText();
				LoginValidator logger = new LoginValidator();
				passflag = logger.validate(username, password);
				if(!passflag)
					logintextLabel.setText("login/password incorrect - Try again");
				else {
					user = new User(username);
				}
			}
		});
	}
	
	public boolean getPassed() {
		return passflag;
	}
	
	public User getUser() {
		return user;
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}
}
