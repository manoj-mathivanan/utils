package outlook;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class gui {
	static JLabel lfrom = new JLabel("from");
	static JLabel lto = new JLabel("to");
	static JLabel lsubject = new JLabel("subject");
	static JLabel lmessage = new JLabel("message");
	static JTextField from = new JTextField(25);
	static JTextField to = new JTextField(25);
	static JTextField subject = new JTextField(25);
	// JTextField message = new JTextField(25);
	static JButton send = new JButton("Send Mail");
	static JPanel panel = new JPanel();
	static JTextArea message = new JTextArea();
	public static void main(String[] args) {
		//fetch all the records
		load_ui();
	}
	
	public static void load_ui()
	{
		JFrame guiFrame = new JFrame();
		guiFrame.setLayout(new FlowLayout());
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Operations");
        guiFrame.setSize(310,430);
        guiFrame.setLocationRelativeTo(null);
        JScrollPane scroll = new JScrollPane (message, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(260, 180));
        panel.add(scroll);
        panel.setVisible(true);
        send.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Outlook outlook = new Outlook("inumber", "password");
				outlook.sendMail(from.getText(), to.getText(), subject.getText(), message.getText());
				
				
			}
		});
        guiFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        guiFrame.add(lfrom);
        guiFrame.add(from);
        guiFrame.add(lto);
        guiFrame.add(to);
        guiFrame.add(lsubject);
        guiFrame.add(subject);
        guiFrame.add(lmessage);
        guiFrame.add(panel);
        guiFrame.add(send);
        guiFrame.setVisible(true);
	}
	
}
