import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField SearchtextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage frame = new SearchPage();
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
	public SearchPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel SearchTitle = new JLabel("Search");
		SearchTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		SearchTitle.setBounds(188, 11, 70, 14);
		contentPane.add(SearchTitle);
		
		SearchtextField = new JTextField();
		SearchtextField.setToolTipText("Search");
		SearchtextField.setBounds(52, 95, 226, 20);
		contentPane.add(SearchtextField);
		SearchtextField.setColumns(10);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.setBounds(299, 94, 89, 23);
		contentPane.add(SearchButton);
	}
}
