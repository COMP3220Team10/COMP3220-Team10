import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class ServiceRequestPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField DateTextField;
	private JTextField AddrTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceRequestPage frame = new ServiceRequestPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceRequestPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Service Request");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(113, 0, 194, 31);
		contentPane.add(titleLabel);
		
		JLabel ServiceTypeLabel = new JLabel("Serivce Type");
		ServiceTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ServiceTypeLabel.setBounds(80, 42, 81, 14);
		contentPane.add(ServiceTypeLabel);
		
		JComboBox ServicecomboBox = new JComboBox();
		ServicecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Drainage Service Request", "Other"}));
		ServicecomboBox.setSelectedIndex(2);
		ServicecomboBox.setBounds(120, 67, 187, 22);
		contentPane.add(ServicecomboBox);
		
		JLabel DateLabel = new JLabel("Date/Time");
		DateLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DateLabel.setBounds(80, 102, 81, 14);
		contentPane.add(DateLabel);
		
		DateTextField = new JTextField();
		DateTextField.setBounds(120, 129, 187, 20);
		contentPane.add(DateTextField);
		DateTextField.setColumns(10);
		
		JLabel AddrLabel = new JLabel("Address");
		AddrLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AddrLabel.setBounds(80, 160, 46, 14);
		contentPane.add(AddrLabel);
		
		AddrTextField = new JTextField();
		AddrTextField.setBounds(120, 185, 187, 20);
		contentPane.add(AddrTextField);
		AddrTextField.setColumns(10);
		
		JButton SubmitButton = new JButton("Submit");
		SubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		SubmitButton.setBounds(163, 227, 89, 23);
		contentPane.add(SubmitButton);
	}
}
