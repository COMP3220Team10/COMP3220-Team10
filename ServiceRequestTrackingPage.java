import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequestTrackingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JTextField searchTextField;

    public ServiceRequestTrackingPage() {
        // Frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Track Service Request");
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(new BorderLayout());

        // Main content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Initialize components
        addTitleLabel();
        addSearchPanel();
        addResultTable();
        addButtonPanel();
    }

    private void addTitleLabel() {
        JLabel titleLabel = new JLabel("Service Request Tracking and Status");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBounds(220, 11, 400, 30);
        contentPane.add(titleLabel);
    }

    private void addSearchPanel() {
        JLabel searchLabel = new JLabel("Enter Street Name:");
        searchLabel.setBounds(30, 60, 150, 25);
        contentPane.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setBounds(180, 60, 400, 25);
        contentPane.add(searchTextField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(600, 60, 100, 25);
        searchButton.addActionListener(e -> performSearch());
        contentPane.add(searchButton);
    }

    private void addResultTable() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 98, 682, 344);
        contentPane.add(scrollPane);

        resultTable = new JTable();
        tableModel = new DefaultTableModel(
                new Object[]{"Description", "Department", "Block/Address", "Street", "Ward", "Received By", "Created Date"},
                0
        );
        resultTable.setModel(tableModel);
        scrollPane.setViewportView(resultTable);
    }

    private void addButtonPanel() {

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(403, 468, 150, 25);
        contentPane.add(btnExit);
        
                JButton btnReturn = new JButton("Return");
                btnReturn.setBounds(177, 468, 144, 25);
                contentPane.add(btnReturn);
                btnReturn.addActionListener(e -> {
                    dispose(); // Close the current frame
                    Main newWindow = new Main(); // Navigate back to Main
                    newWindow.setVisible(true);
                });
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void performSearch() {
        String streetName = searchTextField.getText().trim();
        if (streetName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a street name to search.");
            return;
        }

        boolean resultsFound = loadResults(streetName);
        if (resultsFound) {
            JOptionPane.showMessageDialog(this, "Your service request has been approved. Status: Pending...");
        } else {
            JOptionPane.showMessageDialog(this, "No matching records found for the entered street name.");
        }
    }

    private boolean loadResults(String streetName) {
        tableModel.setRowCount(0); // Clear the table
        boolean foundResults = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("Drainage_YTD.csv"))) {
            String line;
            boolean isFirstLine = true; // Skip the header
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = parseCSVLine(line);
                if (data.length > 3 && data[3].equalsIgnoreCase(streetName)) {
                    tableModel.addRow(data);
                    foundResults = true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV file: " + e.getMessage());
        }

        return foundResults;
    }

    private String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char ch : line.toCharArray()) {
            if (ch == '"') {
                inQuotes = !inQuotes; // Toggle state
            } else if (ch == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0); // Clear buffer
            } else {
                sb.append(ch);
            }
        }
        tokens.add(sb.toString().trim()); // Add last token
        return tokens.toArray(new String[0]);
    }
}
