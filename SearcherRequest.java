import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearcherRequest {

    /**
     * Searches for drainage requests by street name in the specified file.
     * @param filePath Path to the CSV file.
     * @param streetName Street name to search for.
     * @return Results of the search as a formatted string.
     */
    public String searchByStreet(String filePath, String streetName) {
        StringBuilder results = new StringBuilder();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Read the header line
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

            if (!found) {
                results.append("No drainage requests found for the specified street.");
            }
        } catch (IOException ex) {
            results.append("Error reading the file: ").append(ex.getMessage());
        }

        return results.toString();
    }
}