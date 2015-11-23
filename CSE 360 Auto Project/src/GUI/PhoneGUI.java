package GUI;

import phone.*;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import java.awt.Image;

import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class PhoneGUI extends JPanel {

	Phone phone;
	
	public PhoneGUI(Phone phone) {
		this.phone = phone;
		
		//Test with setting to Contact instead of String
		DefaultListModel<Contact> contactListModel = new DefaultListModel<Contact>();
		for(Contact contact : phone.getContactList()) {
			contactListModel.addElement(contact);
		}
		
		setPreferredSize(new Dimension(605, 467));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		JPanel phonePanel = new JPanel();
		add(phonePanel);
		CardLayout cards = new CardLayout(0, 0);
		phonePanel.setLayout(cards);
		
		JPanel dialerPanel = new JPanel();
		phonePanel.add(dialerPanel, "Dialer");
		dialerPanel.setPreferredSize(new Dimension(354, 467));
		dialerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel dialerDisplayPanel = new JPanel();
		dialerDisplayPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		dialerPanel.add(dialerDisplayPanel, BorderLayout.NORTH);
		
		JLabel numberLabel = new JLabel();
		dialerDisplayPanel.add(numberLabel);
		numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		numberLabel.setPreferredSize(new Dimension(74, 30));
		numberLabel.setText("Number: ");
		JTextField numberTextField = new JTextField();
		numberTextField.setHorizontalAlignment(SwingConstants.CENTER);
		numberTextField.setBorder(new LineBorder(Color.BLACK));
		numberTextField.setEditable(false);
		dialerDisplayPanel.add(numberTextField);
		numberTextField.setPreferredSize(new Dimension(200, 30));
		numberTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel dialpadPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) dialpadPanel.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		dialpadPanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		dialerPanel.add(dialpadPanel, BorderLayout.CENTER);
		
		JPanel dialpadButtonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) dialpadButtonsPanel.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		dialpadButtonsPanel.setPreferredSize(new Dimension(200, 250));
		dialpadPanel.add(dialpadButtonsPanel);
		
		JButton oneButton = new JButton("1");
		oneButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		oneButton.setPreferredSize(new Dimension(45, 45));
		oneButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(oneButton);
		
		JButton twoButton = new JButton("2");
		twoButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		twoButton.setPreferredSize(new Dimension(45, 45));
		twoButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(twoButton);
		
		JButton threeButton = new JButton("3");
		threeButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		threeButton.setPreferredSize(new Dimension(45, 45));
		threeButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(threeButton);
		
		JButton fourButton = new JButton("4");
		fourButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		fourButton.setPreferredSize(new Dimension(45, 45));
		fourButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(fourButton);
		
		JButton fiveButton = new JButton("5");
		fiveButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		fiveButton.setPreferredSize(new Dimension(45, 45));
		fiveButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(fiveButton);
		
		JButton sixButton = new JButton("6");
		sixButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		sixButton.setPreferredSize(new Dimension(45, 45));
		sixButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(sixButton);
		
		JButton sevenButton = new JButton("7");
		sevenButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		sevenButton.setPreferredSize(new Dimension(45, 45));
		sevenButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(sevenButton);
		
		JButton eightButton = new JButton("8");
		eightButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		eightButton.setPreferredSize(new Dimension(45, 45));
		eightButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(eightButton);
		
		JButton nineButton = new JButton("9");
		nineButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		nineButton.setPreferredSize(new Dimension(45, 45));
		nineButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(nineButton);
		
		JButton starButton = new JButton("*");
		starButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		starButton.setPreferredSize(new Dimension(45, 45));
		starButton.setMargin(new Insets(6, 0, 0, 0));
		dialpadButtonsPanel.add(starButton);
		
		JButton zeroButton = new JButton("0");
		zeroButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		zeroButton.setPreferredSize(new Dimension(45, 45));
		zeroButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(zeroButton);
		
		JButton poundButton = new JButton("#");
		poundButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		poundButton.setPreferredSize(new Dimension(45, 45));
		poundButton.setMargin(new Insets(0, 0, 0, 0));
		dialpadButtonsPanel.add(poundButton);
		
		JPanel dialerControlsPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) dialerControlsPanel.getLayout();
		flowLayout_4.setHgap(25);
		dialerControlsPanel.setPreferredSize(new Dimension(354, 110));
		dialerPanel.add(dialerControlsPanel, BorderLayout.SOUTH);
		
		JButton callButton = new JButton("Call");
		callButton.setMargin(new Insets(0, 0, 0, 0));
		callButton.setPreferredSize(new Dimension(60, 35));
		callButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dialerControlsPanel.add(callButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(60, 35));
		clearButton.setMargin(new Insets(0, 0, 0, 0));
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dialerControlsPanel.add(clearButton);
		
		JPanel onCallPanel = new JPanel();
		onCallPanel.setPreferredSize(new Dimension(354, 467));
		phonePanel.add(onCallPanel, "OnCall");
		
		JLabel callLabel = new JLabel("Calling: N/A");
		callLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		onCallPanel.add(callLabel);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setPreferredSize(new Dimension(300, 300));
		onCallPanel.add(imagePanel);
		imagePanel.setLayout(new BorderLayout(0, 0));
		
		BufferedImage phoneicon = null;
		ImageIcon icon = null;
		JLabel iconLabel;
		File picin = new File("src/phone_icon.png");
		if(picin.exists()) {
			try {
				phoneicon = ImageIO.read(picin);
				icon = new ImageIcon(phoneicon.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			iconLabel = new JLabel(icon);
		}
		else
			iconLabel = new JLabel("");
		
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imagePanel.add(iconLabel, BorderLayout.CENTER);
		
		JButton endCallButton = new JButton("End Call");
		endCallButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		onCallPanel.add(endCallButton);
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(300, 5));
		onCallPanel.add(emptyPanel);
		
		JLabel durrationLabel = new JLabel("Durration: N/A");
		durrationLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		onCallPanel.add(durrationLabel);
		
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
		
		JButton spVolDownButton = new JButton("-");
		spVolDownButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spVolDownButton.setPreferredSize(new Dimension(35, 35));
		spVolDownButton.setMargin(new Insets(0, 0, 0, 0));
		spVolButtonsPanel.add(spVolDownButton);
		
		JButton spVolUpButton = new JButton("+");
		spVolUpButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spVolUpButton.setMargin(new Insets(0, 0, 0, 0));
		spVolUpButton.setPreferredSize(new Dimension(35, 35));
		spVolButtonsPanel.add(spVolUpButton);
		
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
		
		JButton micVolDownButton = new JButton("-");
		micVolDownButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		micVolDownButton.setPreferredSize(new Dimension(35, 35));
		micVolDownButton.setMargin(new Insets(0, 0, 0, 0));
		micVolButtonsPanel.add(micVolDownButton);

		JButton micVolUpButton = new JButton("+");
		micVolUpButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		micVolUpButton.setMargin(new Insets(0, 0, 0, 0));
		micVolUpButton.setPreferredSize(new Dimension(35, 35));
		micVolButtonsPanel.add(micVolUpButton);

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
		JList<Contact> contactsJList = new JList<Contact>(contactListModel);
		contactsJList.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contactsScrollPane.setViewportView(contactsJList);
		
		JPanel contactControlsPanel = new JPanel();
		contactControlsPanel.setPreferredSize(new Dimension(250, 45));
		contactsPanel.add(contactControlsPanel, BorderLayout.SOUTH);
		
		JButton callContactButton = new JButton("Call Contact");
		callContactButton.setPreferredSize(new Dimension(140, 35));
		callContactButton.setMargin(new Insets(0, 0, 0, 0));
		callContactButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contactControlsPanel.add(callContactButton);
		
		JButton[] dialpad = {zeroButton, oneButton, twoButton, 
				 threeButton, fourButton, fiveButton, 
				 sixButton, sevenButton, eightButton, 
				 nineButton, starButton, poundButton};
		
		volumeHandler(spVolUpButton, spVolDownButton, spVolMuteButton, micVolUpButton, micVolDownButton, micVolMuteButton);
		labelUpdater(spVolLabel, micVolLabel, durrationLabel);
		dialpadHandler(numberTextField, dialpad, clearButton);
		callHandler(callButton, endCallButton, numberTextField, callLabel, phonePanel, cards);
		callSavedHandler(contactsJList, callContactButton, endCallButton, callLabel, phonePanel, cards);
	}
	
	private void labelUpdater(JLabel spVol, JLabel micVol, JLabel durration) {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spVol.setText("Volume: " + phone.getSpeakerVol());
				micVol.setText("Volume: " + phone.getMicVol());
				if(phone.getOnCall()) {
					durration.setText("Durration: " + phone.getCallDuration());
				}
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
				if(spMute.getModel().isSelected()) {
					phone.speakerMute();
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
	
	private void dialpadHandler(JTextField number, JButton[] numberpad, JButton clear) {
		//All numbers correspond, '*' = index 10, '#' = index 11
		numberpad[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[0].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[0].getText();
				else
					phonenumber += numberpad[0].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[1].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[1].getText();
				else
					phonenumber += numberpad[1].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[2].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[2].getText();
				else
					phonenumber += numberpad[2].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[3].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[3].getText();
				else
					phonenumber += numberpad[3].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[4].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[4].getText();
				else
					phonenumber += numberpad[4].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[5].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[5].getText();
				else
					phonenumber += numberpad[5].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[6].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[6].getText();
				else
					phonenumber += numberpad[6].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[7].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[7].getText();
				else
					phonenumber += numberpad[7].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[8].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[8].getText();
				else
					phonenumber += numberpad[8].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[9].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[9].getText();
				else
					phonenumber += numberpad[9].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[10].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[10].getText();
				else
					phonenumber += numberpad[10].getText();
				number.setText(phonenumber);
			}
		});
		numberpad[11].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phonenumber = number.getText();
				if(phonenumber.length() == 3)
					phonenumber += "-" + numberpad[11].getText();
				else if(phonenumber.length() == 7)
					phonenumber += "-" + numberpad[11].getText();
				else
					phonenumber += numberpad[11].getText();
				number.setText(phonenumber);
			}
		});
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				number.setText("");
			}
		});
	}

	private void callHandler(JButton call, JButton endcall, JTextField numberCalled, JLabel callingNumber, JPanel dialer, CardLayout cards) {
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String number = numberCalled.getText();
				if(!number.isEmpty()) {
					phone.makeCall(number);
					callingNumber.setText("Calling: " + phone.getNumberCalled());
					cards.show(dialer, "OnCall");
				}
			}
		});
		endcall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phone.endCall();
				cards.show(dialer, "Dialer");
			}
		});
	}
	
	public void callSavedHandler(JList<Contact> contacts, JButton call, JButton endcall, JLabel callingNumber, JPanel dialer, CardLayout cards) {
		call.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!phone.getOnCall()) {
					String number = contacts.getSelectedValue().getNumber();
					phone.makeCall(number);
					callingNumber.setText("Calling: " + phone.getNumberCalled());
					cards.show(dialer, "OnCall");
				}
			}
		});
		endcall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phone.endCall();
				cards.show(dialer, "Dialer");
			}
		});
		
	}
}
