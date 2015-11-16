package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PhoneGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public PhoneGUI() {
		setLayout(null);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(191, 142, 56, 16);
		add(lblPhone);

	}

}
