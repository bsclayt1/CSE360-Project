package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class MapGUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public MapGUI() {
		setLayout(null);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setBounds(192, 138, 56, 16);
		add(lblMap);

	}

}
