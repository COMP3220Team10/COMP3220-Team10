import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadFile extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel uploadedFilePanel;
    private JPanel buttonPanel;
    private List<File> uploadedFiles;
    private JButton btnExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UploadFile frame = new UploadFile();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public UploadFile() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Upload Dataset");
        setBounds(100, 100, 547, 390);
        getContentPane().setLayout(new BorderLayout());

        // Main content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Initialize uploaded files list
        uploadedFiles = new ArrayList<>();

        // Panel to display uploaded files
        addUploadedFilePanel();

        // Panel for action buttons
        addButtonPanel();
    }

    private void addUploadedFilePanel() {
        uploadedFilePanel = new JPanel();
        uploadedFilePanel.setBounds(31, 31, 462, 183);
        // Grid layout for file icons
        uploadedFilePanel.setLayout(new GridLayout(0, 4, 10, 10)); 
        contentPane.add(uploadedFilePanel);

        JLabel lblUploadedFiles = new JLabel("Uploaded files:");
        lblUploadedFiles.setBounds(21, 11, 100, 14);
        uploadedFilePanel.add(lblUploadedFiles);
    }

    private void addButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBounds(35, 273, 458, 51);
        buttonPanel.setLayout(null);
        contentPane.add(buttonPanel);

        // Upload Button
        JButton btnUpload = new JButton("Upload File");
        btnUpload.setBounds(174, 11, 108, 29);
        btnUpload.addActionListener(e -> uploadFiles());
        buttonPanel.add(btnUpload);

        // Return Button
        JButton btnReturn = new JButton("Return");
        btnReturn.setBounds(43, 11, 108, 29);
        btnReturn.addActionListener(e -> {
            contentPane.setVisible(false);
            Main newWindow = new Main();
            newWindow.setVisible(true);
        });
        buttonPanel.add(btnReturn);
        
        btnExit = new JButton("Exit");
        btnExit.setBounds(317, 11, 108, 29);
        btnExit.addActionListener(e -> System.exit(0)); // Close the application
        buttonPanel.add(btnExit);
    }

    private void uploadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        // Allow multiple file selection
        fileChooser.setMultiSelectionEnabled(true); 
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                if (!uploadedFiles.contains(file)) {
                	// Add file to the list if not already uploaded
                    uploadedFiles.add(file); 
                    addFileIcon(file);
                }
            }
        }
    }

    private void addFileIcon(File file) {
        JLabel fileIconLabel = new JLabel();
        // Default file icon
        fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon")); 
        fileIconLabel.setText(file.getName());
        fileIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Hand cursor for file icons
        fileIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        // Refresh panel to display new icons
        uploadedFilePanel.add(fileIconLabel);
        uploadedFilePanel.revalidate(); 
        uploadedFilePanel.repaint();
    }
}
