/*
 * Names: Angela, Ayesha, Faria, Murtaza, Yihan, Yixuan, Zhuqing
 * This is the main entry point of the project.
 * Here, the main screen controls what frame is opened when
 */

// Import statements
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// The main class
public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Start with the loginpage
                LoginForm loginPage = new LoginForm();
                loginPage.showLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Main() {
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Home");
        setBounds(100, 100, 600, 400);

        // Create the content pane with padding
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(0, 2, 10, 10));
        setContentPane(contentPane);

        // Add buttons with their action listeners with the help of the addButton function
        
        // This will open the UploadFile frame and hide the current contentPane
        addButton("Upload", e -> {
            contentPane.setVisible(false);
            UploadFile newWindow = new UploadFile();
            newWindow.setVisible(true);
        });
        
        // This will open the DataPage frame and hide the current contentPane
        addButton("Download", e -> {
            contentPane.setVisible(false);
            DataPage newWindow = new DataPage();
            newWindow.setVisible(true);
        });
        
        // This will open the ServiceRequest frame and hide the current contentPane
        addButton("Start a New Request", e -> {
            contentPane.setVisible(false);
            ServiceRequest newWindow = new ServiceRequest();
            newWindow.setVisible(true);
        });

        // This will open the Search frame and hide the current contentPane
        addButton("Search", e -> {
            contentPane.setVisible(false);
            Search newWindow = new Search();
            newWindow.setVisible(true);
        });

        // This will open the ServiceRequestTrackingPage frame and hide the current contentPane
        addButton("Track Service Request", e -> {
            contentPane.setVisible(false);
            ServiceRequestTrackingPage newWindow = new ServiceRequestTrackingPage();
            newWindow.setVisible(true);
        });
        
        // This will open the dashboard frame and hide the current contentPane
        addButton("Dashboard", e -> {
        	contentPane.setVisible(false);
            dashboard newWindow = new dashboard();
            newWindow.setVisible(true);
        });
        
        // This will open the Report frame and hide the current contentPane
        addButton("Feedback", e -> {
            contentPane.setVisible(false);
            Report newWindow = new Report();
            newWindow.setVisible(true);
        });
        
        // This will open the SearchPage frame and hide the current contentPane
        addButton("Search for a street", e -> {
            contentPane.setVisible(false);
            SearchPage newWindow = new SearchPage();
            newWindow.setVisible(true);
        });
    }

    /**
     * This is a method that helps to add a button to the content pane with an ActionListener.
     */
    private void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        contentPane.add(button);
    }
}
