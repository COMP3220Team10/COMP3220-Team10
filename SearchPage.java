import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SearchPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchTextField;
    private JTextArea resultTextArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchPage frame = new SearchPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SearchPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel searchTitle = new JLabel("Search Drainage Requests by Street");
        searchTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        searchTitle.setBounds(150, 11, 300, 30);
        contentPane.add(searchTitle);

        searchTextField = new JTextField();
        searchTextField.setToolTipText("Enter Street Name");
        searchTextField.setBounds(50, 60, 400, 25);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(460, 60, 90, 25);
        contentPane.add(searchButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 100, 500, 200);
        contentPane.add(scrollPane);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        scrollPane.setViewportView(resultTextArea);

        // Add event listener for the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String streetName = searchTextField.getText().trim();
                if (!streetName.isEmpty()) {
                    SearcherRequest searcher = new SearcherRequest();
                    String results = searcher.searchByStreet("Drainage_YTD.csv", streetName);
                    resultTextArea.setText(results);
                } else {
                    resultTextArea.setText("Please enter a street name.");
                }
            }
        });
    }
}
