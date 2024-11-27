import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        // Set up contentPane with BorderLayout
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Add the custom image panel in the center
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load and draw the first image
                ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\ayesh\\eclipse-workspace\\DrainageRequestSystem\\src\\CategoryChart.png");
                Image image1 = imageIcon1.getImage();
                g.drawImage(image1, 0, 0, this.getWidth() / 2, this.getHeight(), this);

                // Load and draw the second image
                ImageIcon imageIcon2 = new ImageIcon("C:\\Users\\ayesh\\eclipse-workspace\\DrainageRequestSystem\\src\\XYChart.png");
                Image image2 = imageIcon2.getImage();
                g.drawImage(image2, this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight(), this);
            }
        };
        contentPane.add(imagePanel, BorderLayout.CENTER);

        // Add the button panel at the bottom
        addButtonPanel();
    }

    /**
     * Add the buttons to the frame.
     */
    private void addButtonPanel() {
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center the buttons
        
        // Create "Exit" button
        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(150, 25)); // Set button size
        buttonPanel.add(btnExit);

        // Create "Return" button
        JButton btnReturn = new JButton("Return");
        btnReturn.setPreferredSize(new Dimension(144, 25)); // Set button size
        buttonPanel.add(btnReturn);

        // Add functionality to "Return" button
        btnReturn.addActionListener(e -> {
            dispose(); // Close the current frame
            Main newWindow = new Main(); // Navigate back to Main
            newWindow.setVisible(true);
        });

        // Add functionality to "Exit" button
        btnExit.addActionListener(e -> System.exit(0));

        // Add button panel to the bottom of contentPane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
}
