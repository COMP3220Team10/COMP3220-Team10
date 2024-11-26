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
        setLayout(new BorderLayout());

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
        uploadedFilePanel.setLayout(new GridLayout(0, 4, 10, 10)); // Grid layout for file icons
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
        btnUpload.setBounds(62, 11, 108, 29);
        btnUpload.addActionListener(e -> uploadFiles());
        buttonPanel.add(btnUpload);

        // Return Button
        JButton btnReturn = new JButton("Return");
        btnReturn.setBounds(292, 11, 108, 29);
        btnReturn.addActionListener(e -> {
            contentPane.setVisible(false);
            Main newWindow = new Main(); // Assuming Main is another class in your project
            newWindow.setVisible(true);
        });
        buttonPanel.add(btnReturn);
    }

    private void uploadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true); // Allow multiple file selection
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                if (!uploadedFiles.contains(file)) {
                    uploadedFiles.add(file); // Add file to the list if not already uploaded
                    addFileIcon(file);
                }
            }
        }
    }

    private void addFileIcon(File file) {
        JLabel fileIconLabel = new JLabel();
        fileIconLabel.setIcon(UIManager.getIcon("FileView.fileIcon")); // Default file icon
        fileIconLabel.setText(file.getName());
        fileIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        fileIconLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        fileIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor for file icons

        uploadedFilePanel.add(fileIconLabel);
        uploadedFilePanel.revalidate(); // Refresh panel to display new icons
        uploadedFilePanel.repaint();
    }
}
