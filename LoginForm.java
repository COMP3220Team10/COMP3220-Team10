import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;

public class LoginForm {

    private JFrame frame;
    private JTextArea txtUser;
    private JPasswordField txtPass;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginForm window = new LoginForm();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginForm() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setupFrame();
        setupTitle();
        setupFormFields();
        setupButtons();
    }

    /**
     * Set up the main frame.
     */
    private void setupFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.getContentPane().setLayout(null);
    }

    /**
     * Add the title label to the frame.
     */
    private void setupTitle() {
        JLabel lblTitle = new JLabel("Login Form");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblTitle.setBounds(221, 46, 215, 46);
        frame.getContentPane().add(lblTitle);
    }

    /**
     * Add the username and password fields to the frame.
     */
    private void setupFormFields() {
        // Username Label
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        lblUsername.setBounds(154, 154, 85, 14);
        frame.getContentPane().add(lblUsername);

        // Username Field
        txtUser = new JTextArea();
        txtUser.setBackground(Color.WHITE);
        txtUser.setForeground(Color.BLACK);
        txtUser.setBounds(286, 153, 120, 20);
        txtUser.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        frame.getContentPane().add(txtUser);

        // Password Label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
        lblPassword.setBounds(154, 208, 85, 14);
        frame.getContentPane().add(lblPassword);

        // Password Field
        txtPass = new JPasswordField();
        txtPass.setForeground(Color.BLACK);
        txtPass.setBounds(286, 207, 120, 20);
        txtPass.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        frame.getContentPane().add(txtPass);
    }

    /**
     * Add the login and close buttons to the frame.
     */
    private void setupButtons() {
        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnLogin.setBounds(154, 269, 113, 37);
        btnLogin.addActionListener(e -> handleLogin());
        frame.getContentPane().add(btnLogin);

        // Close Button
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClose.setBounds(331, 269, 113, 37);
        btnClose.addActionListener(e -> frame.dispose()); // Close the application
        frame.getContentPane().add(btnClose);
    }

    /**
     * Handle the login button action.
     */
    private void handleLogin() {
        String uname = txtUser.getText();
        String pass = String.valueOf(txtPass.getPassword());

        if (uname.equals("admin") && pass.equals("123")) {
            JOptionPane.showMessageDialog(frame, "Login was successful");
            frame.setVisible(false); // Close login window
            EventQueue.invokeLater(() -> {
                Main mainWindow = new Main();
                mainWindow.setVisible(true); // Open Main window
            });
        } else {
            JOptionPane.showMessageDialog(frame, "Login was not successful");
        }
    }
}
