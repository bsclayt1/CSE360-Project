package GUI;

import phone.*;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class PhoneGUI extends JPanel {

	Phone phone;
	
	public PhoneGUI(Phone phone) {
		this.phone = phone;
		setPreferredSize(new Dimension(605, 467));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel dialerPanel = new JPanel();
		dialerPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) dialerPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		dialerPanel.setPreferredSize(new Dimension(354, 467));
		add(dialerPanel);
		
		JLabel numberLabel = new JLabel();
		numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		numberLabel.setPreferredSize(new Dimension(74, 30));
		numberLabel.setText("Number: ");
		dialerPanel.add(numberLabel);
		JTextField numberTextField = new JTextField();
		numberTextField.setPreferredSize(new Dimension(200, 30));
		numberTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dialerPanel.add(numberTextField);
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setPreferredSize(new Dimension(250, 467));
		add(controlsPanel);
		controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel volumePanel = new JPanel();
		volumePanel.setPreferredSize(new Dimension(250, 155));
		controlsPanel.add(volumePanel);
		volumePanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel speakerVolPanel = new JPanel();
		volumePanel.add(speakerVolPanel);
		speakerVolPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel speakerLabel = new JLabel("Speaker");
		speakerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		speakerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		speakerVolPanel.add(speakerLabel, BorderLayout.NORTH);
		
		JPanel spVolSubPanel = new JPanel();
		speakerVolPanel.add(spVolSubPanel, BorderLayout.CENTER);
		spVolSubPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel spVolLabel = new JLabel("Volume: 5.0");
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
		
		JPanel micVolPanel = new JPanel();
		volumePanel.add(micVolPanel);
		micVolPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel micLabel = new JLabel("Microphone");
		micLabel.setHorizontalAlignment(SwingConstants.CENTER);
		micLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		micVolPanel.add(micLabel, BorderLayout.NORTH);

		JPanel micVolSubPanel = new JPanel();
		micVolPanel.add(micVolSubPanel, BorderLayout.CENTER);
		micVolSubPanel.setLayout(new BorderLayout(0, 0));

		JLabel micVolLabel = new JLabel("Volume: 5.0");
		micVolLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		micVolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		micVolSubPanel.add(micVolLabel, BorderLayout.NORTH);

		JPanel micVolButtonsPanel = new JPanel();
		micVolButtonsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		FlowLayout flowLayout_2 = (FlowLayout) micVolButtonsPanel.getLayout();
		flowLayout_2.setVgap(15);
		flowLayout_2.setHgap(20);
		micVolSubPanel.add(micVolButtonsPanel, BorderLayout.CENTER);

		JButton micVolUpButton = new JButton("+");
		micVolUpButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		micVolUpButton.setMargin(new Insets(0, 0, 0, 0));
		micVolUpButton.setPreferredSize(new Dimension(35, 35));
		micVolButtonsPanel.add(micVolUpButton);

		JButton micVolDownButton = new JButton("-");
		micVolDownButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		micVolDownButton.setPreferredSize(new Dimension(35, 35));
		micVolDownButton.setMargin(new Insets(0, 0, 0, 0));
		micVolButtonsPanel.add(micVolDownButton);

		JToggleButton micVolMuteButton = new JToggleButton("Mute");
		micVolMuteButton.setPreferredSize(new Dimension(70, 35));
		micVolMuteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		micVolMuteButton.setMargin(new Insets(0, 0, 0, 0));
		micVolButtonsPanel.add(micVolMuteButton);
		
		JPanel contactsPanel = new JPanel();
		controlsPanel.add(contactsPanel);
		contactsPanel.setPreferredSize(new Dimension(250, 312));
		contactsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel contactHeaderPanel = new JPanel();
		contactsPanel.add(contactHeaderPanel, BorderLayout.NORTH);
		
		JLabel contactHeaderLabel = new JLabel("Contacts");
		contactHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contactHeaderPanel.add(contactHeaderLabel);
		
		JPanel contactListPanel = new JPanel();
		contactListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contactsPanel.add(contactListPanel, BorderLayout.CENTER);
		contactListPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane contactsScrollPane = new JScrollPane();
		contactListPanel.add(contactsScrollPane);
		contactsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contactsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Test with setting to Contact instead of String
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Contact contact : phone.getContactList()) {
			listModel.addElement(contact.getName());
		}
		JList<String> contactsList = new JList<String>(listModel);
		contactsList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contactsScrollPane.setViewportView(contactsList);
		
		labelUpdater(spVolLabel, micVolLabel);
		volumeHandler(spVolUpButton, spVolDownButton, spVolMuteButton, micVolUpButton, micVolDownButton, micVolMuteButton);
	}
	
	private void labelUpdater(JLabel spVol, JLabel micVol) {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spVol.setText("Volume: " + phone.getSpeakerVol());
				micVol.setText("Volume: " + phone.getMicVol());
			}
		});
		interval.start();
	}
	
	private void volumeHandler(JButton spVolUp, JButton spVolDwn, JToggleButton spMute, JButton micVolUp, JButton micVolDwn, JToggleButton micMute) {
		spVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!spMute.getModel().isSelected())
					phone.incSpeakerVol();
			}
		});
		spVolDwn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!spMute.getModel().isSelected())
					phone.decSpeakerVol();
			}
		});
		spMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(micMute.getModel().isSelected()) {
					phone.micMute();
					spMute.setText("Unmute");
				}
				else {
					phone.speakerUnmute();
					spMute.setText("Mute");
				}
			}
		});
		micVolUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!micMute.getModel().isSelected())
					phone.incMicVol();
			}
		});
		micVolDwn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!micMute.getModel().isSelected())
					phone.decMicVol();
			}
		});
		micMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(micMute.getModel().isSelected()) {
					phone.micMute();
					micMute.setText("Unmute");
				}
				else {
					phone.micUnmute();
					micMute.setText("Mute");
				}
			}
		});
	}
}
