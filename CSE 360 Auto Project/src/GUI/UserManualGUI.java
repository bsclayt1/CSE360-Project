package GUI;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.io.FileReader;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

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
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JTextPane manualTextPanel = new JTextPane();
		manualTextPanel.setAutoscrolls(false);
		manualTextPanel.setEditable(false);
		manualTextPanel.setText(manualText);
		manualTextPanel.setCaretPosition(0);
		scrollPane.setViewportView(manualTextPanel);
		//add(manualTextPanel);

	}

}
