package drainageApp;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;



public class DataPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel iconPanel;
	private File selectedFile;

	public DataPage() {
		List<File> files = FileConfig.getFiles();
		selectedFile = files.isEmpty() ? null : files.get(0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Display and Download Dataset");
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		contentPane.add(panel);
	
		JPanel introPanel = new JPanel();
		introPanel.setBounds(20, 11, 412, 147);
		introPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(introPanel);
		introPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Drainage_YTD");
		nameLabel.setBounds(10, 0, 166, 23);
		introPanel.add(nameLabel);
		
		JPanel sumPanel = new JPanel();
		sumPanel.setBounds(10, 27, 392, 109);
		introPanel.add(sumPanel);
		sumPanel.setLayout(null);
		
		JLabel sumLabel = new JLabel("<html>Maintenance pertaining to culvert/ditch drainage and odours.<br> The dataset contains information on customer initiated service requests<br> entered into the City of Windsor 311 system from various channels (phone, email, online self-serve, text, mobile app).<br>311 manages the service request data for City of Windsor departments and divisions represented in this dataset.<br>As such, 311 consulted the participating divisions and subject matter experts for this release. This data set is extracted electronically from the 311 customer request management system.</html>");
		sumLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sumLabel.setBounds(0, -12, 392, 121);
		sumPanel.add(sumLabel);
		
		JPanel desPanel = new JPanel();
		desPanel.setBounds(20, 143, 187, 194);
		desPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(desPanel);
		desPanel.setLayout(null);
		
		JLabel desLable_1 = new JLabel("Last updated: Nov 20,2024");
		desLable_1.setBounds(10, 26, 177, 14);
		desPanel.add(desLable_1);
		
		JLabel desLable_2 = new JLabel("Release Date: June 6, 2023");
		desLable_2.setBounds(10, 63, 177, 14);
		desPanel.add(desLable_2);
		
		JLabel desLable_3 = new JLabel("Topic: Culvert");
		desLable_3.setBounds(10, 96, 130, 14);
		desPanel.add(desLable_3);
		
		JLabel desLable_4 = new JLabel("Formats: txt/csv/xlsx");
		desLable_4.setBounds(10, 132, 130, 14);
		desPanel.add(desLable_4);
		
		JLabel desLable_5 = new JLabel("Contact: windsor@gmail.com");
		desLable_5.setBounds(10, 169, 177, 14);
		desPanel.add(desLable_5);
		
		JPanel apiPanel = new JPanel();
		apiPanel.setBounds(190, 143, 242, 194);
		apiPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(apiPanel);
		apiPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("API");
		lblNewLabel_1.setBounds(108, 11, 115, 28);
		apiPanel.add(lblNewLabel_1);
		
		JTextArea txtrCodesHere = new JTextArea();
		txtrCodesHere.setText("codes here");
		txtrCodesHere.setBounds(29, 34, 194, 152);
		apiPanel.add(txtrCodesHere);
		
		iconPanel = new JPanel();
        iconPanel.setBounds(442, 0, 121, 103);
        contentPane.add(iconPanel);
		
		JButton btnPreview = new JButton("preview");
		btnPreview.setBounds(454, 96, 89, 23);
		contentPane.add(btnPreview);
		
		JLabel iconLabel = new JLabel(selectedFile.getName());
		iconLabel.setBounds(443, 11, 90, 91);
		
		JButton btnDownload = new JButton("download");
		btnDownload.setBounds(454, 234, 89, 23);
		contentPane.add(btnDownload);
		
		JButton btnReturn = new JButton("return");
		btnReturn.setBounds(454, 275, 89, 23);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Main newWindow = new Main();
				newWindow.setVisible(true);
			}
		});
		contentPane.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Select formats");
		lblNewLabel.setBounds(454, 147, 89, 14);
		contentPane.add(lblNewLabel);

        JComboBox<String> formatComboBox = new JComboBox<>(new String[]{"CSV", "TXT", "XLSX"});
        formatComboBox.setBounds(454, 165, 91, 23);
        contentPane.add(formatComboBox);
        
        btnPreview.addActionListener(e -> displayFileContent(selectedFile));
        btnDownload.addActionListener(e -> {
            String selectedFormat = (String) formatComboBox.getSelectedItem();
            downloadFile(selectedFile, selectedFormat);
        });
    	addFileIcon(selectedFile);
        
        
        iconPanel.setLayout(null);
        JLabel fileIconLabel = new JLabel();
        fileIconLabel.setBounds(0, 11, 110, 81);
        iconPanel.add(fileIconLabel);
        fileIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon"));
        fileIconLabel.setText(selectedFile.getName());
        fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        contentPane.revalidate();
        contentPane.repaint();
	}

	private void addFileIcon(File file) {
		iconPanel.revalidate();
		iconPanel.repaint();
	}

	private void displayFileContent(File file) {
        JFrame displayFrame = new JFrame("File Viewer - " + file.getName());
        displayFrame.setSize(600, 400);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] columnNames = null;
            List<String[]> data = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (columnNames == null) {
                    columnNames = values; // Assume first line contains column names
                } else {
                    data.add(values);
                }
            }

            if (columnNames != null) {
                JTable table = new JTable(data.toArray(new String[0][]), columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                displayFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        displayFrame.setVisible(true);
    }

    private void downloadFile(File file, String format) {
        JFileChooser fileChooser = new JFileChooser();
        String baseName = file.getName().substring(0, file.getName().lastIndexOf('.'));
        fileChooser.setSelectedFile(new File(baseName + "." + format.toLowerCase())); // Pre-fill with chosen format

        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File targetFile = fileChooser.getSelectedFile();
            try {
                if ("CSV".equalsIgnoreCase(format)) {
                    copyFile(file, targetFile);
                } else if ("TXT".equalsIgnoreCase(format)) {
                    convertToTxt(file, targetFile);
                } else if ("XLSX".equalsIgnoreCase(format)) {
                    convertToXlsx(file, targetFile);
                }
                JOptionPane.showMessageDialog(this, "File downloaded successfully as " + format + "!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    private void convertToTxt(File source, File target) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(new FileWriter(target))) {
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line.replace(",", "\t")); 
            }
        }
    }

    private void convertToXlsx(File source, File target) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(new FileWriter(target))) {
            pw.println("This is a simple XLSX placeholder file. Real XLSX conversion requires libraries like Apache POI.");
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line); // Simple mimic of XLSX content
            }
        }
    }
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataPage frame = new DataPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
