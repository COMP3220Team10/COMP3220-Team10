import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList; 

/**
 * Implement class ServiceRequestTrackingApp that goes into MainMenuFrame
 */
class ServiceRequestTrackingApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainMenuFrame mainMenu = new MainMenuFrame();
                mainMenu.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

/**
 * Implement class MainMenuFrame that extends onto Jframe for Service Request System
 */
class MainMenuFrame extends JFrame {
    //Constructor to initialize main menu
    public MainMenuFrame() {
        //Set title of the window
        setTitle("Service Request System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 200, 400, 200);
        //Create new JPanel
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(3, 1, 10, 10));
        setContentPane(contentPane);
        //Create welcomeLabel with "Welcome to the Service Request System"
        JLabel welcomeLabel = new JLabel("Welcome to the Service Request System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(welcomeLabel);
        //Create trackServiceButton with "Track Service"
        JButton trackServiceButton = new JButton("Track Service");
        contentPane.add(trackServiceButton);
        trackServiceButton.addActionListener(e -> {
            ServiceRequestTrackingPage trackingPage = new ServiceRequestTrackingPage();
            trackingPage.setVisible(true);
            dispose(); 
        });
        //Create exitButton for user to exit the system
        JButton exitButton = new JButton("Exit");
        contentPane.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
    }
} 

/**
 * This class represents the main entry of ServiceRequestTrackingPage.
 * With this piece of code it'll display main menu with options supported by MainMenuFrame and ServiceRequestTrackingApp 
 * @author Murtaza Ahmed
 * @since 2024-11-25
 */
public class ServiceRequestTrackingPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchTextField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    //Constructor for the ServiceRequestTrackingPage class
    public ServiceRequestTrackingPage() {
        //Set title of the window
        setTitle("Track Service Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        //Implement new JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Implement a label known as "Service Request Tracking and Status"
        JLabel titleLabel = new JLabel("Service Request Tracking and Status");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        titleLabel.setBounds(220, 11, 400, 30);
        contentPane.add(titleLabel);

        //Implement a label known as "Enter Street name:"
        JLabel searchLabel = new JLabel("Enter Street Name:");
        searchLabel.setBounds(30, 60, 150, 25);
        contentPane.add(searchLabel);

        //Implement a swing componenet for which user can enter and edit a single line of text
        searchTextField = new JTextField();
        searchTextField.setBounds(180, 60, 400, 25);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);

        //Implement a search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(600, 60, 100, 25);
        contentPane.add(searchButton);

        //Impelement a scrollable viewports to seek other components
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 100, 720, 400);
        contentPane.add(scrollPane);

        //Implement a resultTable for which it will provide results from the given input
        resultTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Description", "Department", "Block/Address", "Street", "Ward", "Received By", "Created Date"}, 0);
        resultTable.setModel(tableModel);
        scrollPane.setViewportView(resultTable);
        
        //Implement a placeholder button ""
        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(30, 520, 400, 25);
        contentPane.add(statusLabel);

        //Implement a button called "back"
        JButton backButton = new JButton("Back");
        backButton.setBounds(650, 520, 100, 25);
        contentPane.add(backButton);
        backButton.addActionListener(e -> {
            MainMenuFrame mainMenu = new MainMenuFrame();
            mainMenu.setVisible(true);
            dispose();
        });

        //Implement a addActionListener for when the user input is met, message will display either approved or no matchings found
        searchButton.addActionListener(e -> {
            String streetName = searchTextField.getText().trim();
            if (!streetName.isEmpty()) {
                boolean resultsFound = loadResults(streetName);
                if (resultsFound) {
                    JOptionPane.showMessageDialog(null, "Your service request has been approved. Status: Pending...");
                    statusLabel.setText("Status: Pending...");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching records found for the entered street name.");
                    statusLabel.setText(""); 
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a street name to search.");
            }
        });
    }
    /**
     * This will load service request with CSV file and display the table with matching results if user inputs a desired input
     * 
     * @param streetname which is the name of the street to search, hence will be used for the CSV File
     * @return True if matching is found, else false with an error message
     * @throws IOException if error is found in reading CSV file
     */
    private boolean loadResults(String streetName) {
        tableModel.setRowCount(0); // Clear the table
        boolean foundResults = false;
        //BufferedReader to read file "Drainage_YTD.csv"
        try (BufferedReader reader = new BufferedReader(new FileReader("Drainage_YTD.csv"))) {
            String line;
            boolean isFirstLine = true; 
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
            JOptionPane.showMessageDialog(null, "Error reading CSV file: " + e.getMessage());
        }
    
        return foundResults;
    }
    /**
     * This will parse a single file from the given CSV file into array of strings.
     * 
     * @param line known as the single line of CSV input which is considered as a string.
     * @return Array of strings, hence will be the result that represents a field. 
     */
    private String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        //iterate over each character
        for (char ch : line.toCharArray()) {
            if (ch == '"') {
                inQuotes = !inQuotes; 
            } else if (ch == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0); 
            } else {
                sb.append(ch);
            }
        }
        //Token will be used to increment 
        tokens.add(sb.toString().trim()); 
        return tokens.toArray(new String[0]);
    }
}
