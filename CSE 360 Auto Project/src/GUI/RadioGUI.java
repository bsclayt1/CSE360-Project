package GUI;

import radio.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

@SuppressWarnings("serial")
public class RadioGUI extends JPanel {
	
	private Radio radio;
	private ArrayList<Station> availStations;

	public RadioGUI(Radio radio) {
		this.radio = radio;
		availStations = radio.getStationList();
		setPreferredSize(new Dimension(605, 467));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		DefaultListModel<Station> stationListModel = new DefaultListModel<Station>();
		for(Station station : radio.getFavStationList()) {
			stationListModel.addElement(station);
		}
		
		JPanel radioPanel = new JPanel();
		radioPanel.setPreferredSize(new Dimension(324, 467));
		add(radioPanel);
		
		JPanel controlsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) controlsPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		controlsPanel.setPreferredSize(new Dimension(280, 467));
		add(controlsPanel);
		
		JPanel volumePanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) volumePanel.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(0);
		volumePanel.setPreferredSize(new Dimension(280, 155));
		controlsPanel.add(volumePanel);
		
		JPanel speakerVolPanel = new JPanel();
		speakerVolPanel.setPreferredSize(new Dimension(155, 155));
		volumePanel.add(speakerVolPanel);
		speakerVolPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel speakerLabel = new JLabel("Speaker");
		speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		speakerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		speakerVolPanel.add(speakerLabel, BorderLayout.NORTH);
		
		JPanel spVolSubPanel = new JPanel();
		speakerVolPanel.add(spVolSubPanel, BorderLayout.CENTER);
		spVolSubPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel spVolLabel = new JLabel("Volume: Off");
		spVolLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spVolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		spVolSubPanel.add(spVolLabel, BorderLayout.NORTH);
		
		JPanel spVolButtonsPanel = new JPanel();
		spVolButtonsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout_1 = (FlowLayout) spVolButtonsPanel.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(20);
		spVolSubPanel.add(spVolButtonsPanel, BorderLayout.CENTER);
		
		JButton spVolUpButton = new JButton("+");
		spVolUpButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spVolUpButton.setMargin(new Insets(0, 0, 0, 0));
		spVolUpButton.setPreferredSize(new Dimension(35, 35));
		spVolButtonsPanel.add(spVolUpButton);
		
		JButton spVolDownButton = new JButton("-");
		spVolDownButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spVolDownButton.setPreferredSize(new Dimension(35, 35));
		spVolDownButton.setMargin(new Insets(0, 0, 0, 0));
		spVolButtonsPanel.add(spVolDownButton);
		
		JToggleButton spVolMuteButton = new JToggleButton("Mute");
		spVolMuteButton.setPreferredSize(new Dimension(70, 35));
		spVolMuteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spVolMuteButton.setMargin(new Insets(0, 0, 0, 0));
		spVolButtonsPanel.add(spVolMuteButton);
		
		JPanel stationsPanel = new JPanel();
		stationsPanel.setPreferredSize(new Dimension(280, 312));
		controlsPanel.add(stationsPanel);
		stationsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel stationsHeaderPanel = new JPanel();
		stationsPanel.add(stationsHeaderPanel, BorderLayout.NORTH);
		
		JLabel stationsHeaderLabel = new JLabel("Favorite Stations");
		stationsHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		stationsHeaderPanel.add(stationsHeaderLabel);
		
		JPanel stationsListPanel = new JPanel();
		stationsListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		stationsPanel.add(stationsListPanel, BorderLayout.CENTER);
		stationsListPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane contactsScrollPane = new JScrollPane();
		stationsListPanel.add(contactsScrollPane);
		contactsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JList<Station> contactsJList = new JList<Station>(stationListModel);
		contactsJList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contactsScrollPane.setViewportView(contactsJList);
		
		JPanel stationsControlPanel = new JPanel();
		stationsPanel.add(stationsControlPanel, BorderLayout.SOUTH);
		
		volumeHandler(spVolUpButton, spVolDownButton, spVolMuteButton);
		labelUpdater(spVolLabel);
	}
	
	private void labelUpdater(JLabel spVol) {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spVol.setText("Volume: " + radio.getSpeakerVol());
			}
		});
		interval.start();
	}
	
	private void volumeHandler(JButton spVolUp, JButton spVolDwn, JToggleButton spMute) {
		spVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!spMute.getModel().isSelected())
					radio.incSpeakerVol();
			}
		});
		spVolDwn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!spMute.getModel().isSelected())
					radio.decSpeakerVol();
			}
		});
		spMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(spMute.getModel().isSelected()) {
					radio.speakerMute();
					spMute.setText("Unmute");
				}
				else {
					radio.speakerUnmute();
					spMute.setText("Mute");
				}
			}
		});
	}
}
