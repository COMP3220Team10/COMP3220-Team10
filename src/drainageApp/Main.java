package drainageApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Home");
		setBounds(100, 100, 611, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("upload");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				UploadFile newWindow = new UploadFile();
				newWindow.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 42, 564, 93);
		contentPane.add(btnNewButton);
		
		JButton btnDownoad = new JButton("downoad ");
		btnDownoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				SpecificPage newWindow = new SpecificPage();
				newWindow.setVisible(true);
			}
		});
		btnDownoad.setBounds(10, 156, 564, 93);
		contentPane.add(btnDownoad);
		
		JButton btnDashboard = new JButton("dashboard");
		btnDashboard.setBounds(10, 267, 564, 93);
		contentPane.add(btnDashboard);
		
		JButton btnApi = new JButton("API");
		btnApi.setBounds(10, 371, 564, 93);
		contentPane.add(btnApi);
	}

}
