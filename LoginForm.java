import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginForm {
	
	// Main frame for the login page
    private JFrame frame;
    // Set up the variables for username and password
    private JTextArea txtUser;
    private JPasswordField txtPass;

    public void showLogin() {
        // Initialize and display the login frame
        EventQueue.invokeLater(() -> {
            try {
                LoginForm loginPage = new LoginForm();
                loginPage.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor to initialize the login form
    public LoginForm() {
        initialize();
    }

    // Setup the frame
    private void initialize() {
        setupFrame();
        setupTitle();
        setupFormFields();
        setupButtons();
    }

    // Setup main frame properties
    private void setupFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.getContentPane().setLayout(null);
    }
    
    // Add a title label to the frame declaring it the login form
    private void setupTitle() {
        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTitle.setBounds(221, 46, 215, 46);
        frame.getContentPane().add(lblTitle);
    }

    // Add the fields username and password to the frame
    private void setupFormFields() {
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        lblUsername.setBounds(154, 154, 85, 14);
        frame.getContentPane().add(lblUsername);

        txtUser = new JTextArea();
        txtUser.setBackground(Color.WHITE);
        txtUser.setForeground(Color.BLACK);
        txtUser.setBounds(286, 153, 120, 20);
        txtUser.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        frame.getContentPane().add(txtUser);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        lblPassword.setBounds(154, 208, 85, 14);
        frame.getContentPane().add(lblPassword);

        txtPass = new JPasswordField();
        txtPass.setForeground(Color.BLACK);
        txtPass.setBounds(286, 207, 120, 20);
        txtPass.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        frame.getContentPane().add(txtPass);
    }
    
    // The login and close buttons are here
    private void setupButtons() {
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogin.setBounds(154, 269, 113, 37);
        btnLogin.addActionListener(e -> handleLogin());
        frame.getContentPane().add(btnLogin);

        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClose.setBounds(331, 269, 113, 37);
        btnClose.addActionListener(e -> frame.dispose());
        frame.getContentPane().add(btnClose);
    }
    
    // This will handle the login actions (password and username checks etc.)
    private void handleLogin() {
        String uname = txtUser.getText();
        String pass = String.valueOf(txtPass.getPassword());

        if (uname.equals("admin") && pass.equals("123")) {
            JOptionPane.showMessageDialog(frame, "Login was successful");
            frame.dispose(); // Close login frame
            EventQueue.invokeLater(() -> {
                Main mainWindow = new Main();
                mainWindow.setVisible(true); // Open Main window
            });
        } else {
            JOptionPane.showMessageDialog(frame, "Login was not successful");
        }
    }
}
