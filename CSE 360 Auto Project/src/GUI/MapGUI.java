package GUI;

import map.*;
import car.CarController;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import org.json.simple.JSONArray;

@SuppressWarnings("serial")
public class MapGUI extends JPanel {
	private ArrayList<Route> routes;
	private Route currentRoute;
	private CarController car;
	private JLabel routeNameLabel;
	private JLabel routeDistLabel;
	private JLabel routeTraveledLabel;
	private JList<Route> routesJList;
	private JButton selectButton;
	
	public MapGUI(CarController car) {
		this.car = car;
		routes = car.getRoutes();
		currentRoute = car.getCurrentRoute();
		setPreferredSize(new Dimension(605, 467));
		setLayout(new BorderLayout(0, 0));
		
		DefaultListModel<Route> routeListModel = new DefaultListModel<Route>();
		for(Route route : routes) {
			routeListModel.addElement(route);
		}
		
		JPanel currentRoutePanel = new JPanel();
		currentRoutePanel.setPreferredSize(new Dimension(10, 50));
		add(currentRoutePanel, BorderLayout.NORTH);
		currentRoutePanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		routeNameLabel = new JLabel(currentRoute.getName());
		routeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		routeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentRoutePanel.add(routeNameLabel);
		
		routeDistLabel = new JLabel(String.format("%.0fmiles", currentRoute.getDistance()));
		routeDistLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		routeDistLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentRoutePanel.add(routeDistLabel);
		
		routeTraveledLabel = new JLabel(String.format("%.1fmiles", currentRoute.getTraveled()));
		routeTraveledLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		routeTraveledLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentRoutePanel.add(routeTraveledLabel);
		
		JPanel selectRoutePanel = new JPanel();
		selectRoutePanel.setBorder(new EmptyBorder(5, 0, 0, 0));
		add(selectRoutePanel, BorderLayout.CENTER);
		selectRoutePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel subPanel = new JPanel();
		subPanel.setPreferredSize(new Dimension(300, 300));
		selectRoutePanel.add(subPanel);
		
		JLabel routesLabel = new JLabel("Routes");
		routesLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		subPanel.add(routesLabel);
		routesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		routesJList = new JList<Route>(routeListModel);
		routesJList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		routesJList.setBorder(new LineBorder(new Color(0, 0, 0)));
		subPanel.add(routesJList);
		routesJList.setPreferredSize(new Dimension(300, 265));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(10, 100));
		add(controlPanel, BorderLayout.SOUTH);
		
		selectButton = new JButton("Select Route");
		selectButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		controlPanel.add(selectButton);
		
		updateRoute();
		selectRoute();
	}
	
	private void updateRoute() {
		Timer interval = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRoute.updateTravled(car.getSpeed());
				routeNameLabel.setText(currentRoute.getName());
				routeDistLabel.setText(String.format("%.0fmiles", currentRoute.getDistance()));
				routeTraveledLabel.setText(String.format("%.1fmiles", currentRoute.getTraveled()));
			}
		});
		interval.start();
	}
	
	private void selectRoute() {
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRoute = routesJList.getSelectedValue();
				car.setRoute(currentRoute);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void getRoutesJSON() {
		JSONArray routesJSON = new JSONArray();
		for(Route route : routes) {
			routesJSON.add(route.getJSONRoute());
		}
		car.setRoutesJSON(routesJSON);
	}
}
