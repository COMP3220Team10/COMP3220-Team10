import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Option3 {

    // filter & display requests based on area input
    public static void viewRequestsInArea(String csvFilePath) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ward number to search for drainage requests (e.g., 1, 10): ");
        String userInputWardNumber = scanner.nextLine().trim();

        // use "WARD " to match the format in the CSV file
        String formattedWard = "WARD " + userInputWardNumber;

        System.out.println("\nDrainage Requests in " + formattedWard + ":");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine(); // skip header
            boolean foundRequests = false;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                if (columns.length >= 7) { 
                    String description = columns[0].replace("\"", "").trim();
                    String department = columns[1].replace("\"", "").trim();
                    String blockAddress = columns[3].replace("\"", "").trim();
                    String street = columns[4].replace("\"", "").trim();
                    String ward = columns[5].replace("\"", "").trim();
                    String methodReceived = columns[6].replace("\"", "").trim();
                    String createdDate = columns[7].replace("\"", "").trim();

                    // check if the area matches the formatted ward
                    if (ward.equalsIgnoreCase(formattedWard)) {
                        foundRequests = true;
                        System.out.println("Description: " + description +
                                ", Department: " + department +
                                ", Block/Address: " + blockAddress +
                                ", Street: " + street +
                                ", Ward: " + ward +
                                ", Method Received: " + methodReceived +
                                ", Created Date: " + createdDate);
                    }
                }
            }

            if (!foundRequests) {
                System.out.println("No drainage requests found for the specified ward.");
            }

        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}
