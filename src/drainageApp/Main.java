package drainageApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

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
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnUploadButton = new JButton("upload");
		btnUploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				UploadFile newWindow = new UploadFile();
				newWindow.setVisible(true);
			}
		});
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(btnUploadButton);
		
		JButton btnDownoad = new JButton("downoad ");
		btnDownoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Search newWindow = new Search();
				newWindow.setVisible(true);
			}
		});
		contentPane.add(btnDownoad);
		
		JButton btnDashboard = new JButton("dashboard");
		contentPane.add(btnDashboard);
		
		JButton btnApi = new JButton("API");
		contentPane.add(btnApi);
	}

}
