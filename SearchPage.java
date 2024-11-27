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


/**
 * This class represents the main entry of SearchPage.
 * With this piece of code it'll display a frame for which users will enter a streetname and results will show below 
 * @author Yixuan Wang & Murtaza Ahmed 
 * @since 2024-11-25
 */
public class SearchPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchTextField;
    private JTextArea resultTextArea;

    /**
     * Call in SearchPage class and display frame
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

    //Constructor for the SearchPage class
    public SearchPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //Implement JPanel
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //Implement JLabel with searchTitle with "Search Drainage Request by Street"
        JLabel searchTitle = new JLabel("Search Drainage Requests by Street");
        searchTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        searchTitle.setBounds(150, 11, 300, 30);
        contentPane.add(searchTitle);
        //Implement searchrTextField with JTextField 
        searchTextField = new JTextField();
        //Implement searchTextField with "Enter Stree Name"
        searchTextField.setToolTipText("Enter Street Name");
        searchTextField.setBounds(50, 60, 400, 25);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);
        //Implement searchButton with Jbutton "Search"
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(460, 60, 90, 25);
        contentPane.add(searchButton);
        //Implement scrollPane to scroll up or down displaying the output
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 100, 500, 200);
        contentPane.add(scrollPane);
        //Implement resultTexrArea to display the outputs
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        scrollPane.setViewportView(resultTextArea);

        //Implement an addActionLister to the search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String streetName = searchTextField.getText().trim();
                //Using if else statement, user will input a street and find it under "Drainage_YTD.csv", else wrong input
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
