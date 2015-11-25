package GUI;

import user.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.Timer;

import car.CarController;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Insets;

@SuppressWarnings("serial")
public class StatsGUI extends JPanel {
	private User user;
	private CarController car;
	
	private ArrayList<String> callLogs;
	private ArrayList<String> carLogs;
	private ArrayList<String> radioLogs;
	private ArrayList<String> stationLogs;
	
	private DefaultListModel<String> callLogList;
	private DefaultListModel<String> carLogList;
	private DefaultListModel<String> radioLogList;
	private DefaultListModel<String> stationLogList;
	private JList<String> callLogJList;
	private JList<String> carLogJList;
	private JList<String> radioLogJList;
	private JList<String> stationLogJList;
	
	public StatsGUI(User user, CarController car) {
		this.user = user;
		this.car = car;
		callLogs = user.getCallLogs();
		stationLogs = user.getStationLogs();
		carLogs = car.getCarLogs();
		radioLogs = car.getRadioLogs();
		callLogList = new DefaultListModel<String>();
		carLogList = new DefaultListModel<String>();
		radioLogList = new DefaultListModel<String>();
		stationLogList = new DefaultListModel<String>();
		for(String callLog : callLogs) {
			callLogList.addElement(callLog);
		}
		for(String carLog : this.carLogs) {
			carLogList.addElement(carLog);
		}
		for(String radioLog : this.radioLogs) {
			radioLogList.addElement(radioLog);
		}
		for(String stationLog : this.stationLogs) {
			stationLogList.addElement(stationLog);
		}
		
		setPreferredSize(new Dimension(605, 467));
		setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel carLogPanel = new JPanel();
		carLogPanel.setPreferredSize(new Dimension(300, 150));
		add(carLogPanel);
		carLogPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel carLogHeaderPanel = new JPanel();
		carLogPanel.add(carLogHeaderPanel, BorderLayout.NORTH);
		carLogHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblCarLogs = new JLabel("Car Logs");
		carLogHeaderPanel.add(lblCarLogs, BorderLayout.CENTER);
		lblCarLogs.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton clearCarLogsButton = new JButton("");
		clearCarLogsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		clearCarLogsButton.setPreferredSize(new Dimension(16, 16));
		clearCarLogsButton.setMargin(new Insets(0, 0, 0, 0));
		carLogHeaderPanel.add(clearCarLogsButton, BorderLayout.EAST);
		
		JScrollPane carLogScrollPane = new JScrollPane();
		carLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		carLogPanel.add(carLogScrollPane, BorderLayout.CENTER);
		
		carLogJList = new JList<String>();
		carLogScrollPane.setViewportView(carLogJList);
		carLogJList.setModel(carLogList);
		
		JPanel radioLogPanel = new JPanel();
		add(radioLogPanel);
		radioLogPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel radioLogHeaderPanel = new JPanel();
		radioLogPanel.add(radioLogHeaderPanel, BorderLayout.NORTH);
		radioLogHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel radioLogLabel = new JLabel("Radio Logs");
		radioLogHeaderPanel.add(radioLogLabel, BorderLayout.CENTER);
		radioLogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton radioClearLogsButton = new JButton("");
		radioClearLogsButton.setMargin(new Insets(0, 0, 0, 0));
		radioClearLogsButton.setPreferredSize(new Dimension(16, 16));
		radioLogHeaderPanel.add(radioClearLogsButton, BorderLayout.EAST);
		
		JScrollPane radioLogScrollPane = new JScrollPane();
		radioLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		radioLogPanel.add(radioLogScrollPane);
		
		radioLogJList = new JList<String>();
		radioLogScrollPane.setViewportView(radioLogJList);
		radioLogJList.setModel(radioLogList);
		
		JPanel callLogPanel = new JPanel();
		callLogPanel.setPreferredSize(new Dimension(300, 150));
		add(callLogPanel);
		callLogPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane callLogScrollPane = new JScrollPane();
		callLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		callLogPanel.add(callLogScrollPane);
		
		callLogJList = new JList<String>(callLogList);
		callLogScrollPane.setViewportView(callLogJList);
		callLogJList.setModel(callLogList);
		
		JPanel callLogHeaderPanel = new JPanel();
		callLogPanel.add(callLogHeaderPanel, BorderLayout.NORTH);
		callLogHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CallLogLabel = new JLabel(capitalize(user.getUserName()) + "'s Call Logs");
		callLogHeaderPanel.add(CallLogLabel, BorderLayout.CENTER);
		CallLogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton callClearLogsButton = new JButton("");
		callClearLogsButton.setMargin(new Insets(0, 0, 0, 0));
		callClearLogsButton.setPreferredSize(new Dimension(16, 16));
		callLogHeaderPanel.add(callClearLogsButton, BorderLayout.EAST);
		
		JPanel stationLogPanel = new JPanel();
		add(stationLogPanel);
		stationLogPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane stationLogsScrollPane = new JScrollPane();
		stationLogsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		stationLogPanel.add(stationLogsScrollPane);
		
		stationLogJList = new JList<String>(stationLogList);
		stationLogsScrollPane.setViewportView(stationLogJList);
		stationLogJList.setModel(stationLogList);
		
		JPanel stationLogHeaderPanel = new JPanel();
		stationLogPanel.add(stationLogHeaderPanel, BorderLayout.NORTH);
		stationLogHeaderPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel stationLogsLabel = new JLabel(capitalize(user.getUserName()) + "'s Station Logs");
		stationLogHeaderPanel.add(stationLogsLabel, BorderLayout.CENTER);
		stationLogsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton stationClearLogsButton = new JButton("");
		stationClearLogsButton.setMargin(new Insets(0, 0, 0, 0));
		stationClearLogsButton.setPreferredSize(new Dimension(16, 16));
		stationLogHeaderPanel.add(stationClearLogsButton, BorderLayout.EAST);
		
		updtateLogs();
		clearLogsHandler(clearCarLogsButton, radioClearLogsButton, callClearLogsButton, stationClearLogsButton);
	}

	private void updtateLogs() {
		Timer interval = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(String carLog : carLogs) {
					if(!carLogList.contains(carLog)) {
						carLogList.addElement(carLog);
						carLogJList.setModel(carLogList);
					}
				}
				for(String callLog : callLogs) {
					if(!callLogList.contains(callLog)) {
						callLogList.addElement(callLog);
						callLogJList.setModel(callLogList);
					}
				}
				for(String radioLog : radioLogs) {
					if(!radioLogList.contains(radioLog)) {
						radioLogList.addElement(radioLog);
						radioLogJList.setModel(radioLogList);
					}
				}
				for(String stationLog : stationLogs) {
					if(!stationLogList.contains(stationLog)) {
						stationLogList.addElement(stationLog);
						stationLogJList.setModel(stationLogList);
					}
				}
			}
		});
		interval.start();
	}
	
	private String capitalize(String name) {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}
	
	private void clearLogsHandler(JButton carLogsClear, JButton radioLogClear, JButton callLogClear, JButton stationLogClear) {
		carLogsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carLogs.clear();
				carLogList.clear();
				car.clearCarLogs();
			}
		});
		radioLogClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioLogs.clear();
				radioLogList.clear();
				car.clearRadioLogs();
			}
		});
		callLogClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callLogs.clear();
				callLogList.clear();
				user.clearCallLogs();
			}
		});
		stationLogClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stationLogs.clear();
				stationLogList.clear();
				user.clearStationLogs();
			}
		});
	}
}
