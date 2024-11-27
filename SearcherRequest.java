import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents the main entry of SearcherRequest.
 * With this piece of code it runs through the CSV file searching for a specific streetname. Once found it'll generate its elements
 * @author Murtaza Ahmed 
 * @since 2024-11-25
 */
public class SearcherRequest {

    /**
     * Searches for drainage requests by street name in the specified file.
     * @param filePath Path to the CSV file.
     * @param streetName Street name to search for.
     * @return Results of the search as a formatted string.
     * @throws IOException if file cannot be read.
     */
    public String searchByStreet(String filePath, String streetName) {
        StringBuilder results = new StringBuilder();
        boolean found = false;
        //Implement the componenets that associate under the CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    String description = fields[0].replace("\"", "").trim();
                    String department = fields[1].replace("\"", "").trim();
                    String blockOrAddress = fields[3].replace("\"", "").trim();
                    String street = fields[4].replace("\"", "").trim();
                    String ward = fields[5].replace("\"", "").trim();
                    String methodReceived = fields[6].replace("\"", "").trim();
                    String createdDate = fields[7].replace("\"", "").trim();

                    if (street.equalsIgnoreCase(streetName)) {
                        found = true;
                        results.append(
                            "Description: ").append(description)
                            .append("\nDepartment: ").append(department)
                            .append("\nBlock/Address: ").append(blockOrAddress)
                            .append("\nStreet: ").append(street)
                            .append("\nWard: ").append(ward)
                            .append("\nMethod Received: ").append(methodReceived)
                            .append("\nCreated Date: ").append(createdDate)
                            .append("\n-------------------------\n"
                        );
                    }
                }
            }
            //If user inputs a wrong input, display no drainage request found for the specified street
            if (!found) {
                results.append("No drainage requests found for the specified street.");
            }
        //Throws IOException if file cannot be read
        } catch (IOException ex) {
            results.append("Error reading the file: ").append(ex.getMessage());
        }

        return results.toString();
    }
}


