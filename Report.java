import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Report {

	private JFrame frame;
	private JTextField DestextField; // Field for description input
	private JTextField pagetextField; // Field for page input

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report window = new Report(); // Create an instance of Report
					window.frame.setVisible(true); // Make the frame visible
				} catch (Exception e) {
					e.printStackTrace(); // Print any exceptions to the console
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Report() {
		initialize(); // Initialize the frame components
	}
	
	/**
	 * Makes the Report window visible.
	 */
	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible); // Show or hide the frame
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame setup
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Title label
		JLabel titleLabel = new JLabel("Report/Feedback");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(135, 0, 164, 22);
		frame.getContentPane().add(titleLabel);
		
		// Type label and combo box
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		typeLabel.setBounds(76, 39, 46, 14);
		frame.getContentPane().add(typeLabel);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Bug", "Inaccurancy", "Login", "Others"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(135, 64, 164, 22);
		frame.getContentPane().add(comboBox);
		
		// Description label and text field
		JLabel descripLabel = new JLabel("Description");
		descripLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		descripLabel.setBounds(76, 97, 68, 14);
		frame.getContentPane().add(descripLabel);

		DestextField = new JTextField();
		DestextField.setBounds(135, 122, 164, 20);
		frame.getContentPane().add(DestextField);
		DestextField.setColumns(10);
		
		// Page label and text field
		JLabel pageLabel = new JLabel("Page");
		pageLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pageLabel.setBounds(76, 148, 46, 22);
		frame.getContentPane().add(pageLabel);

		pagetextField = new JTextField();
		pagetextField.setBounds(135, 175, 164, 20);
		frame.getContentPane().add(pagetextField);
		pagetextField.setColumns(10);
		
		// Submit button with action listener
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String description = DestextField.getText();
				String page = pagetextField.getText();
				
				// Validate input fields
				if (description.isBlank() || page.isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please fill all the blanks.");
				} else {
					JOptionPane.showMessageDialog(frame, "Your report/feedback is submitted!");
				}
			}
		});
		submitButton.setBounds(181, 214, 89, 23);
		frame.getContentPane().add(submitButton);
		
		// Return button to navigate back to Main
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(56, 214, 89, 23);
		btnReturn.addActionListener(e -> {
			frame.getContentPane().setVisible(false);
			Main newWindow = new Main();
			newWindow.setVisible(true);
		});
		frame.getContentPane().add(btnReturn);
		
		// Exit button to close the application
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(295, 214, 89, 23);
		btnExit.addActionListener(e -> System.exit(0));
		frame.getContentPane().add(btnExit);
	}
}
