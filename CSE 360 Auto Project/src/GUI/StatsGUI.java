package GUI;

import user.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ListDataListener;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StatsGUI extends JPanel {
	private ArrayList<String> callLogs;
	private ArrayList<String> carLogs;
	
	private DefaultListModel<String> callLogList;
	private DefaultListModel<String> carLogList;
	private JList<String> callLogJList;
	private JList<String> carLogJList;
	
	public StatsGUI(User user, ArrayList<String> carLogs) {
		callLogs = user.getCallLogs();
		this.carLogs = carLogs;
		callLogList = new DefaultListModel<String>();
		carLogList = new DefaultListModel<String>();
		for(String callLog : callLogs) {
			callLogList.addElement(callLog);
		}
		for(String carLog : this.carLogs) {
			carLogList.addElement(carLog);
		}
		
		setPreferredSize(new Dimension(605, 467));
		setLayout(new BorderLayout(0, 0));
		
		JPanel carLogPanel = new JPanel();
		carLogPanel.setPreferredSize(new Dimension(300, 150));
		add(carLogPanel, BorderLayout.NORTH);
		carLogPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCarLogs = new JLabel("Car Logs");
		lblCarLogs.setHorizontalAlignment(SwingConstants.CENTER);
		carLogPanel.add(lblCarLogs, BorderLayout.NORTH);
		
		JScrollPane carLogScrollPane = new JScrollPane();
		carLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		carLogPanel.add(carLogScrollPane, BorderLayout.CENTER);
		
		carLogJList = new JList<String>();
		carLogScrollPane.setViewportView(carLogJList);
		
		JPanel callLogPanel = new JPanel();
		callLogPanel.setPreferredSize(new Dimension(300, 150));
		add(callLogPanel);
		callLogPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CallLogLabel = new JLabel(capitalize(user.getUserName()) + "'s Call Logs");
		CallLogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		callLogPanel.add(CallLogLabel, BorderLayout.NORTH);
		
		JScrollPane callLogScrollPane = new JScrollPane();
		callLogScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		callLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		callLogPanel.add(callLogScrollPane);
		
		callLogJList = new JList<String>(callLogList);
		callLogScrollPane.setViewportView(callLogJList);
		carLogJList.setModel(carLogList);
		
		updtateLogs();
	}

	private void updtateLogs() {
		Timer interval = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(String callLog : callLogs) {
					if(!callLogList.contains(callLog)) {
						callLogList.addElement(callLog);
						callLogJList.setModel(callLogList);
					}
				}
			}
		});
		interval.start();
	}
	
	private String capitalize(String name) {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
}
