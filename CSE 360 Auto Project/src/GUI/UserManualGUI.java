package GUI;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

public class UserManualGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserManualGUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTextPane manualTextPanel = new JTextPane();
		manualTextPanel.setEditable(false);
		manualTextPanel.setText("User Manual\r\n\r\nPut car in Drive to Accelerate\r\n");
		add(manualTextPanel);

	}

}
