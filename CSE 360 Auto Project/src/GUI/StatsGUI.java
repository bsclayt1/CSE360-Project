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

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatsGUI extends JPanel {
	private User user;
	private ArrayList<String> callLogs;
	
	private DefaultListModel<String> callLogList;
	private JList<String> callLogJList;
	
	public StatsGUI(User user) {
		this.user = user;
		callLogs = user.getCallLogs();
		callLogList = new DefaultListModel<String>();
		for(String callLog : callLogs) {
			callLogList.addElement(callLog);
		}
		
		setPreferredSize(new Dimension(605, 467));
		setLayout(new BorderLayout(0, 0));
		
		JPanel callLogPanel = new JPanel();
		callLogPanel.setPreferredSize(new Dimension(300, 300));
		add(callLogPanel);
		callLogPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CallLogLabel = new JLabel("Call Logs");
		callLogPanel.add(CallLogLabel, BorderLayout.NORTH);
		
		JScrollPane callLogScrollPane = new JScrollPane();
		callLogScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		callLogScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		callLogPanel.add(callLogScrollPane);
		
		callLogJList = new JList<String>(callLogList);
		callLogScrollPane.setViewportView(callLogJList);
		
		updtateLogs();
	}

	private void updtateLogs() {
		Timer interval = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callLogs = user.getCallLogs();
				for(String callLog : callLogs) {
					if(!callLogList.contains(callLog))
						callLogList.addElement(callLog);
				}
				callLogJList.setModel(callLogList);
			}
		});
		interval.start();
	}
}
