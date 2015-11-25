package GUI;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class UserManualGUI extends JPanel {
	@SuppressWarnings("resource")
	public UserManualGUI() {
		String manualText = "";
		
		try {
			FileReader fr = new FileReader("./usermanual.txt");
		    StringBuffer sb = new StringBuffer();
		    char[] b = new char[1000];
		    int n = 0;
		    while ((n = fr.read(b)) > 0) 
		         sb.append(b, 0, n);    
		    manualText = sb.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JTextPane manualTextPanel = new JTextPane();
		manualTextPanel.setEditable(false);
		manualTextPanel.setText(manualText);
		add(manualTextPanel);

	}

}
