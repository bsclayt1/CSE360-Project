package GUI;

import map.*;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MapGUI extends JPanel {
	private ArrayList<Route> routes;
	

	
	public MapGUI() {
		setPreferredSize(new Dimension(605, 467));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMap);

	}

}
