import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel iconPanel;
    private File selectedFile;

    public DataPage() {
        // Initialize selected file
        List<File> files = FileConfig.getFiles(); // Assuming FileConfig.getFiles() is implemented
        selectedFile = files.isEmpty() ? null : files.get(0);

        // Set up frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Display and Download Dataset");
        setBounds(100, 100, 600, 450); // Increased height for buttons
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Introduction Panel
        addIntroPanel();

        // Metadata Panel
        addMetadataPanel();

        // API and Download Panel
        addApiAndDownloadPanel();

        // File Preview and Icon
        addFilePreviewPanel();

        // Add Exit and Return Buttons
        addExitAndReturnButtons();

        // Refresh the layout
        contentPane.revalidate();
        contentPane.repaint();
    }

    // Method to add the introductory information panel
    private void addIntroPanel() {
        JPanel introPanel = new JPanel();
        introPanel.setBounds(20, 11, 412, 147); // Set bounds for the intro panel 
        introPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(introPanel); // Add panel to content pane 
        introPanel.setLayout(null); // Layout manager for the intro panel 

        // Add dataset name and description to the panel 
        JLabel nameLabel = new JLabel("Drainage_YTD");
        nameLabel.setBounds(10, 0, 166, 23);
        introPanel.add(nameLabel);

        JLabel summaryLabel = new JLabel("<html>Maintenance pertaining to culvert/ditch drainage and odours.<br>"
                + "The dataset contains information on customer-initiated service requests entered<br>"
                + "into the City of Windsor 311 system from various channels (phone, email, etc.).</html>");
        summaryLabel.setFont(new Font("Tahoma", Font.PLAIN, 11)); // Set font for description 
        summaryLabel.setBounds(10, 27, 392, 109);
        introPanel.add(summaryLabel); // Add description label 
    }

    // Method to add metadata panel showing dataset details 
    private void addMetadataPanel() {
        JPanel metadataPanel = new JPanel();
        metadataPanel.setBounds(20, 143, 187, 194); // Set bounds for metadata panel 
        metadataPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(metadataPanel); // Add metadata panel to content pane 
        metadataPanel.setLayout(null); // Use null layout for absolute positioning 

        // Add metadata labels to the panel 
        metadataPanel.add(createMetadataLabel("Last updated: Nov 20, 2024", 10, 26));
        metadataPanel.add(createMetadataLabel("Release Date: June 6, 2023", 10, 63));
        metadataPanel.add(createMetadataLabel("Topic: Culvert", 10, 96));
        metadataPanel.add(createMetadataLabel("Formats: txt/csv/xlsx", 10, 132));
        metadataPanel.add(createMetadataLabel("Contact: windsor@gmail.com", 10, 169));
    }

    // Helper method to create metadata labels for specific points 
    private JLabel createMetadataLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 177, 14);
        return label;
    }

    // Method to add API code and file download panel 
    private void addApiAndDownloadPanel() {
        JPanel apiPanel = new JPanel();
        apiPanel.setBounds(190, 143, 242, 194); // Set bounds for API panel 
        apiPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(apiPanel); // Add API panel to content pane 
        apiPanel.setLayout(null); // Layout manager for API panel 

        // Add API label and code display area to the panel 
        JLabel apiLabel = new JLabel("API");
        apiLabel.setBounds(108, 11, 115, 28); 
        apiPanel.add(apiLabel);

        JTextArea apiCodeArea = new JTextArea("API codes here");
        apiCodeArea.setBounds(29, 34, 194, 152); // Set area bounds for displaying API code 
        apiPanel.add(apiCodeArea);

        // File format selection and download
        JLabel formatLabel = new JLabel("Select formats:");
        formatLabel.setBounds(454, 147, 89, 14);
        contentPane.add(formatLabel);

        // ComboBox for format selection
        JComboBox<String> formatComboBox = new JComboBox<>(new String[]{"CSV", "TXT", "XLSX"});
        formatComboBox.setBounds(454, 165, 91, 23); // Set bounds for combo box 
        contentPane.add(formatComboBox);

        // Add download button that triggers the file download 
        JButton downloadButton = new JButton("Download");
        downloadButton.setBounds(454, 234, 95, 23); // Set button position and size 
        contentPane.add(downloadButton);

        // Action listener for the download button 
        downloadButton.addActionListener(e -> {
            String selectedFormat = (String) formatComboBox.getSelectedItem();
            downloadFile(selectedFile, selectedFormat); // Call download method when clicked 
        });
    }

    // Method to add file preview button and file icon 
    private void addFilePreviewPanel() {
        // File preview
        JButton previewButton = new JButton("Preview");
        previewButton.setBounds(454, 96, 89, 23);
        contentPane.add(previewButton);
        previewButton.addActionListener(e -> displayFileContent(selectedFile));

        // File icon
        iconPanel = new JPanel();
        iconPanel.setBounds(442, 0, 121, 103); // Set bounds for icon panel 
        contentPane.add(iconPanel);
        iconPanel.setLayout(null); // Set layout for icon panel 

        // Label to display file icon or name 
        JLabel fileIconLabel = new JLabel();
        fileIconLabel.setBounds(0, 11, 110, 81); // Set bounds for file icon label 
        fileIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon")); // Display default file icon
        fileIconLabel.setText(selectedFile != null ? selectedFile.getName() : "No File"); // Show file name or "No file"
        fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM); // Position text under icon
        iconPanel.add(fileIconLabel); // Add the icon label to the panel 
    }

    // Method to add exit and return buttons 
    private void addExitAndReturnButtons() {
        
        JButton btnExit = new JButton("Exit"); // Exit button to close application 
        btnExit.setBounds(454, 268, 95, 23);
        btnExit.addActionListener(e -> System.exit(0)); // Close the application
        contentPane.add(btnExit);

        // Return button to navigate back to the main page 
        JButton btnReturn = new JButton("Return");
        btnReturn.setBounds(454, 302, 95, 23);
        btnReturn.addActionListener(e -> {
            contentPane.setVisible(false); // Hide current page 
            Main newWindow = new Main(); // Create and show the main window 
            newWindow.setVisible(true);
        });
        contentPane.add(btnReturn); // Add return button to the content pane 
    }

    // Method to display file content in a new frame 
    private void displayFileContent(File file) {
        JFrame displayFrame = new JFrame("File Viewer - " + file.getName());
        displayFrame.setSize(600, 400); // Set size of preview window 

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] columnNames = null;
            List<String[]> data = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split by commas
                if (columnNames == null) {
                    columnNames = values; // Assume first line contains column names
                } else {
                    data.add(values); // Add subsequent rows as data 
                }
            }

            if (columnNames != null) {
                JTable table = new JTable(data.toArray(new String[0][]), columnNames); // Create a table to display file content 
                displayFrame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER); // Adds table to frame 
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Handle errors 
        }

        displayFrame.setVisible(true); // Make file viewer window visible 
    }

    // Method to download the file in the selected format (CSV, TXT, XLSX)
    private void downloadFile(File file, String format) {
        JFileChooser fileChooser = new JFileChooser();
        String baseName = file.getName().substring(0, file.getName().lastIndexOf('.')); // Get base name of the file 
        fileChooser.setSelectedFile(new File(baseName + "." + format.toLowerCase())); // Set default file name with selected format 

        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File targetFile = fileChooser.getSelectedFile(); // Get target file selected by user 
            try {
                // Depending on selected format, call the appropriate conversion method 
                if ("CSV".equalsIgnoreCase(format)) {
                    copyFile(file, targetFile); // Copy as CSV
                } else if ("TXT".equalsIgnoreCase(format)) {
                    convertToTxt(file, targetFile); // Copy to TXT
                } else if ("XLSX".equalsIgnoreCase(format)) {
                    convertToXlsx(file, targetFile); // Convert to XLSX 
                }
                JOptionPane.showMessageDialog(this, "File downloaded successfully as " + format + "!", "Success", JOptionPane.INFORMATION_MESSAGE); // Notify user that it is successful 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Handle errors 
            }
        }
    }

    // Helper method to copy file to new location 
    private void copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead); // Copy data from source to target 
            }
        }
    }

    // Method to convert CSV to TXT by replacing commas with tabs 
    private void convertToTxt(File source, File target) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(new FileWriter(target))) {
            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line.replace(",", "\t")); // Convert commas to tabs for TXT format 
            }
        }
    }

    // Method to simulate XLSX conversion (real conversion needs libraries)
    private void convertToXlsx(File source, File target) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(target))) {
            pw.println("This is a simple XLSX placeholder file. Real XLSX conversion requires libraries like Apache POI.");
        }
    }

}
