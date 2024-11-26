import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Start with the Login Page
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

        // Add buttons with their action listeners:
        addButton("Upload", e -> {
            contentPane.setVisible(false);
            UploadFile newWindow = new UploadFile();
            newWindow.setVisible(true);
        });

        addButton("Download", e -> {
            contentPane.setVisible(false);
            DataPage newWindow = new DataPage();
            newWindow.setVisible(true);
        });

        addButton("Start a New Request", e -> {
            contentPane.setVisible(false);
            ServiceRequest newWindow = new ServiceRequest();
            newWindow.setVisible(true);
        });

        addButton("Search", e -> {
            contentPane.setVisible(false);
            Search newWindow = new Search();
            newWindow.setVisible(true);
        });

        addButton("Track Service Request", e -> {
            contentPane.setVisible(false);
            ServiceRequestTrackingPage newWindow = new ServiceRequestTrackingPage();
            newWindow.setVisible(true);
        });

        addButton("Dashboard", e -> {
            JOptionPane.showMessageDialog(this, "Dashboard feature coming soon!");
        });

        addButton("Feedback", e -> {
            contentPane.setVisible(false);
            Report newWindow = new Report();
            newWindow.setVisible(true);
        });
    }

    /**
     * Helper method to add a button to the content pane with an ActionListener.
     */
    private void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        contentPane.add(button);
    }
}
