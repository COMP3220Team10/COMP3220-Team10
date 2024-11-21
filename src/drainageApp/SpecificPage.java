package drainageApp;

import java.awt.EventQueue;
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
import java.awt.Cursor;

import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;

public class SpecificPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private File predefinedFile;
	private JPanel iconPanel;

	public SpecificPage() {
		this.predefinedFile = new File("C:\\Users\\szq20\\OneDrive\\Desktop\\Drainage_YTD.csv");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Display and Download Dataset");
		setBounds(100, 100, 577, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
	
		JPanel introPanel = new JPanel();
		introPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		introPanel.setBounds(20, 11, 412, 118);
		contentPane.add(introPanel);
		introPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Drainage_YTD DataSet");
		nameLabel.setBounds(10, 0, 166, 34);
		introPanel.add(nameLabel);
		
		JLabel sumLabel = new JLabel("This is a summary of the dataset");
		sumLabel.setBounds(10, 45, 392, 65);
		introPanel.add(sumLabel);
		
		JPanel desPanel = new JPanel();
		desPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		desPanel.setBounds(20, 128, 161, 209);
		contentPane.add(desPanel);
		desPanel.setLayout(null);
		
		JLabel desLable_1 = new JLabel("Last updated: Nov 20,2024");
		desLable_1.setBounds(10, 26, 141, 14);
		desPanel.add(desLable_1);
		
		JLabel desLable_2 = new JLabel("Release Date: June 6, 2023");
		desLable_2.setBounds(10, 63, 151, 14);
		desPanel.add(desLable_2);
		
		JLabel desLable_3 = new JLabel("Topic: Culvert");
		desLable_3.setBounds(10, 96, 130, 14);
		desPanel.add(desLable_3);
		
		JLabel desLable_4 = new JLabel("Formats: txt/csv/xlsx");
		desLable_4.setBounds(10, 132, 130, 14);
		desPanel.add(desLable_4);
		
		JLabel desLable_5 = new JLabel("Contact: windsor@gmail.com");
		desLable_5.setBounds(10, 169, 165, 14);
		desPanel.add(desLable_5);
		
		JPanel apiPanel = new JPanel();
		apiPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		apiPanel.setBounds(180, 128, 252, 209);
		contentPane.add(apiPanel);
		apiPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("API");
		lblNewLabel_1.setBounds(108, 11, 115, 28);
		apiPanel.add(lblNewLabel_1);
		
		JTextArea txtrCodesHere = new JTextArea();
		txtrCodesHere.setText("codes here");
		txtrCodesHere.setBounds(10, 34, 232, 152);
		apiPanel.add(txtrCodesHere);
		
		JButton btnPreview = new JButton("preview");
		btnPreview.setBounds(442, 106, 89, 23);
		contentPane.add(btnPreview);
		
		iconPanel = new JPanel();
		iconPanel.setBounds(442, 25, 121, 67);
		
		JLabel iconLabel = new JLabel("predefinedFile.getName()");
		iconLabel.setBounds(443, 11, 90, 91);
		
		JButton btnDownload = new JButton("download");
		btnDownload.setBounds(442, 238, 89, 23);
		contentPane.add(btnDownload);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				Main newWindow = new Main();
				newWindow.setVisible(true);
			}
		});
		btnReturn.setBounds(442, 272, 89, 23);
		contentPane.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Select formats");
		lblNewLabel.setBounds(454, 147, 89, 14);
		contentPane.add(lblNewLabel);

        JComboBox<String> formatComboBox = new JComboBox<>(new String[]{"CSV", "TXT", "XLSX"});
        formatComboBox.setBounds(452, 164, 91, 23);
        contentPane.add(formatComboBox);
        
        btnPreview.addActionListener(e -> displayCsvContent(predefinedFile));
        btnDownload.addActionListener(e -> {
            String selectedFormat = (String) formatComboBox.getSelectedItem();
            downloadFile(predefinedFile, selectedFormat);
        });
    	addFileIcon(predefinedFile);

        contentPane.add(iconPanel);
        contentPane.revalidate();
        contentPane.repaint();
	}

	private void addFileIcon(File file) {
		iconPanel.setLayout(null);
		JLabel fileIconLabel = new JLabel();
		fileIconLabel.setBounds(10, 5, 101, 51);
		fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon"));
		fileIconLabel.setText(file.getName());
		fileIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		iconPanel.add(fileIconLabel);
		iconPanel.revalidate();
		iconPanel.repaint();
	}

    private void displayCsvContent(File file) {
        JFrame displayFrame = new JFrame("CSV Viewer - " + file.getName());
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
					SpecificPage frame = new SpecificPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
