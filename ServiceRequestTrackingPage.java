import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Service Request System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 200, 400, 200);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3, 1, 10, 10));
        setContentPane(contentPane);

        JLabel welcomeLabel = new JLabel("Welcome to the Service Request System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(welcomeLabel);

        JButton trackServiceButton = new JButton("Track Service");
        contentPane.add(trackServiceButton);
        trackServiceButton.addActionListener(e -> {
            ServiceRequestTrackingPage trackingPage = new ServiceRequestTrackingPage();
            trackingPage.setVisible(true);
            dispose(); // Close the main menu
        });

        JButton exitButton = new JButton("Exit");
        contentPane.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
    }
}

public class ServiceRequestTrackingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchTextField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public ServiceRequestTrackingPage() {
        setTitle("Track Service Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Service Request Tracking and Status");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBounds(220, 11, 400, 30);
        contentPane.add(titleLabel);

        JLabel searchLabel = new JLabel("Enter Street Name:");
        searchLabel.setBounds(30, 60, 150, 25);
        contentPane.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setBounds(180, 60, 400, 25);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(600, 60, 100, 25);
        contentPane.add(searchButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 100, 720, 400);
        contentPane.add(scrollPane);

        resultTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Description", "Department", "Block/Address", "Street", "Ward", "Received By", "Created Date"}, 0);
        resultTable.setModel(tableModel);
        scrollPane.setViewportView(resultTable);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(30, 520, 400, 25);
        contentPane.add(statusLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(650, 520, 100, 25);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            MainMenuFrame mainMenu = new MainMenuFrame();
            mainMenu.setVisible(true);
            dispose();
        });

        searchButton.addActionListener(e -> {
            String streetName = searchTextField.getText().trim();
            if (!streetName.isEmpty()) {
                boolean resultsFound = loadResults(streetName);
                if (resultsFound) {
                    JOptionPane.showMessageDialog(null, "Your service request has been approved. Status: Pending...");
                    statusLabel.setText("Status: Pending...");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching records found for the entered street name.");
                    statusLabel.setText(""); // Clear the status label
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a street name to search.");
            }
        });
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
                    foundResults = true; // Mark that a matching result was found
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading CSV file: " + e.getMessage());
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
