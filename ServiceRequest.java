import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
/**
* @author WindowBuilder
* Description: The serviceRequest file is created for the Service Request form submission page
*/
public class ServiceRequest {

	private JFrame frame;
	private JTextField datetextField;
	private JTextField addrtextField_2;

	/**
	 * Create the application.
	 */
	public ServiceRequest() {
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
		//A title is created
		JLabel titleLabel = new JLabel("Service Request");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(133, 0, 162, 36);
		frame.getContentPane().add(titleLabel);
		//Type label is created here
		JLabel typeLabel = new JLabel("Service Type");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setBounds(63, 36, 83, 23);
		frame.getContentPane().add(typeLabel);
		//comboBox for types selection
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Drainage Service", "Other"}));
		comboBox.setBounds(140, 60, 155, 22);
		frame.getContentPane().add(comboBox);
		//date label is created
		JLabel dateLabel = new JLabel("Date/Time");
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setBounds(63, 93, 74, 14);
		frame.getContentPane().add(dateLabel);
		//text field for date
		datetextField = new JTextField();
		datetextField.setBounds(140, 113, 155, 20);
		frame.getContentPane().add(datetextField);
		datetextField.setColumns(10);
		//Address label is created
		JLabel AddrLabel = new JLabel("Address");
		AddrLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddrLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AddrLabel.setBounds(63, 144, 74, 14);
		frame.getContentPane().add(AddrLabel);
		//text field for address
		addrtextField_2 = new JTextField();
		addrtextField_2.setColumns(10);
		addrtextField_2.setBounds(140, 163, 155, 20);
		frame.getContentPane().add(addrtextField_2);
		
		// In the next iteration, this should have functionality to store to a database 
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//date, addr variables are created for date input and address input
				String date = datetextField.getText();
				String addr = addrtextField_2.getText();
				//if one of the text fields is empty, the Message Dialog prompt is out for fail notification.Otherwise, the message dialog prompt is out for success notice. 
				if (date.isBlank() || addr.isBlank()) {
					JOptionPane.showMessageDialog(frame, "Please fill all the blanks.");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Your request is submitted!");
				}
	
			}
		});
		submitButton.setBounds(173, 210, 89, 23);
		frame.getContentPane().add(submitButton);
		// button for return 
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(36, 210, 89, 23);
		btnReturn.addActionListener(e -> {
            frame.getContentPane().setVisible(false);
            Main newWindow = new Main();
            newWindow.setVisible(true);
        });
		frame.getContentPane().add(btnReturn);
		// button for exit the application
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(312, 210, 89, 23);
		btnExit.addActionListener(e -> System.exit(0)); // Close the application
        frame.getContentPane().add(btnExit);
	}

	/**
	 * Makes the Report window visible.
	 */
	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}
}
