package drainageApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class UploadFile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel uploadedFilePanel;
	private JPanel buttonPanel;
	private List<File> uploadedFiles;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadFile frame = new UploadFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UploadFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Upload Dataset");
		setBounds(100, 100, 547, 390);
		setLayout(new BorderLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		uploadedFiles = new ArrayList<>();
		uploadedFilePanel = new JPanel();
		uploadedFilePanel.setBounds(31, 31, 462, 183);
		contentPane.add(uploadedFilePanel);
		uploadedFilePanel.setLayout(new GridLayout(0, 4, 10, 10));

		JLabel lblNewLabel = new JLabel("uploaded files:");
		lblNewLabel.setBounds(21, 11, 70, 14);
		uploadedFilePanel.add(lblNewLabel);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(35, 273, 458, 51);
		contentPane.add(buttonPanel);

		JButton btnUpload = new JButton("upload file");
		btnUpload.setBounds(62, 11, 108, 29);
		btnUpload.addActionListener(e -> uploadFiles());
		buttonPanel.setLayout(null);
		buttonPanel.add(btnUpload);

		JButton btnReturn = new JButton("return");
		btnReturn.setBounds(292, 11, 108, 29);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Main newWindow = new Main();
				newWindow.setVisible(true);
			}
		});
		buttonPanel.add(btnReturn);
	}

	private void uploadFiles() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true); 
		int returnValue = fileChooser.showOpenDialog(this);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();
			for (File file : selectedFiles) {
				if (!uploadedFiles.contains(file)) {
					uploadedFiles.add(file);
					addFileIcon(file);
				}
			}
		}
	}

	private void addFileIcon(File file) {
		JLabel fileIconLabel = new JLabel();
		fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon"));
		fileIconLabel.setText(file.getName());
		fileIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		fileIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

		uploadedFilePanel.add(fileIconLabel);
		uploadedFilePanel.revalidate();
		uploadedFilePanel.repaint();
	}
}