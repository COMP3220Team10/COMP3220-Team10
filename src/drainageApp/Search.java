package drainageApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class Search extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<String> fileNamesList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Search() {
		// Initialize the fileNamesList by reading filenames from FileConfig
        fileNamesList = new ArrayList<>();
        String[] fileNames = FileConfig.getAllFileNames(); // Get the file names from FileConfig

        // Add the file names to the list
        for (String fileName : fileNames) {
            fileNamesList.add(fileName);
        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Search");
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(133, 47, 251, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = textField.getText().toLowerCase();
				ArrayList<String> searchResults = searchByFilename(keyword);
				showSearchResults(searchResults);
			}
		});
		btnNewButton.setBounds(420, 47, 76, 32);
		contentPane.add(btnNewButton);}
		
    private ArrayList<String> searchByFilename(String keyword) {
        ArrayList<String> results = new ArrayList<>();
        for (String fileName : fileNamesList) {
            if (fileName.toLowerCase().contains(keyword)) {
                results.add(fileName);
            }
        }
        return results;
    }

		private void showSearchResults(ArrayList<String> results) {
			JFrame resultsFrame = new JFrame("Search Results");
			resultsFrame.setBounds(100, 100, 600, 400);
			JPanel panel = new JPanel();
			resultsFrame.setContentPane(panel);
			panel.setLayout(null);

			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn("Results");
			
			for (String result : results) {
				tableModel.addRow(new Object[] { result });
			}
			
			JTable table = new JTable(tableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBounds(50, 50, 500, 250);
			panel.add(table);
			
			table.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow >= 0) {
						String selectedItem = (String) table.getValueAt(selectedRow, 0);
						openDownloadPage(selectedItem);
					}
				}
			});
			
			JButton closeButton = new JButton("Close");
			closeButton.setBounds(250, 320, 100, 32);
			closeButton.addActionListener(e -> resultsFrame.dispose());
			panel.add(closeButton);
			
			resultsFrame.setVisible(true);
		}

		private void openDownloadPage(String item) {
			contentPane.setVisible(false);
			DataPage newWindow = new DataPage();
			newWindow.setVisible(true);
	}
	}

