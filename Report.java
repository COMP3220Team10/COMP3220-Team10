import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Report {

	private JFrame frame;
	private JTextField DestextField;
	private JTextField pagetextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report window = new Report();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Report() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel titleLabel = new JLabel("Report/Feedback");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(135, 0, 164, 22);
		frame.getContentPane().add(titleLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		typeLabel.setBounds(76, 39, 46, 14);
		frame.getContentPane().add(typeLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Bug", "Inaccurancy", "Login", "Others"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(135, 64, 164, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel descripLabel = new JLabel("Description");
		descripLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		descripLabel.setBounds(76, 97, 68, 14);
		frame.getContentPane().add(descripLabel);
		
		DestextField = new JTextField();
		DestextField.setBounds(135, 122, 164, 20);
		frame.getContentPane().add(DestextField);
		DestextField.setColumns(10);
		
		JLabel pageLabel = new JLabel("Page");
		pageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pageLabel.setBounds(76, 148, 46, 22);
		frame.getContentPane().add(pageLabel);
		
		pagetextField = new JTextField();
		pagetextField.setBounds(135, 175, 164, 20);
		frame.getContentPane().add(pagetextField);
		pagetextField.setColumns(10);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String description =DestextField.getText();
				String page = pagetextField.getText();
				
				if (description.isBlank() || page.isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please fill all the blanks.");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Your report/feedback is submitted!");
				}
			}
		});
		submitButton.setBounds(169, 227, 89, 23);
		frame.getContentPane().add(submitButton);
	}
}
