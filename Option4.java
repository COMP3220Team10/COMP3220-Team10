import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

public class Option4 {

    public static class DrainageRequest {
        private String description;
        private String department;
        private String blockAddress;
        private String street;
        private String ward;
        private String methodReceived;
        private String createdDate;

        public DrainageRequest(String description, String department, String blockAddress,
                               String street, String ward, String methodReceived, String createdDate) {
            this.description = description;
            this.department = department;
            this.blockAddress = blockAddress;
            this.street = street;
            this.ward = ward;
            this.methodReceived = methodReceived;
            this.createdDate = createdDate;
        }

        // Getters & Setters
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }

        public String getBlockAddress() { return blockAddress; }
        public void setBlockAddress(String blockAddress) { this.blockAddress = blockAddress; }

        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }

        public String getWard() { return ward; }
        public void setWard(String ward) { this.ward = ward; }

        public String getMethodReceived() { return methodReceived; }
        public void setMethodReceived(String methodReceived) { this.methodReceived = methodReceived; }

        public String getCreatedDate() { return createdDate; }
        public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

        @Override
        public String toString() {
            return "Description: \"" + description + "\"" +
                   ", Department: \"" + department + "\"" +
                   ", Block/Address: \"" + blockAddress + "\"" +
                   ", Street: \"" + street + "\"" +
                   ", Ward: \"" + ward + "\"" +
                   ", Method Received: \"" + methodReceived + "\"" +
                   ", Created Date: \"" + createdDate + "\"";
        }

        // convert the object back to CSV format
        public String toCSV() {
            return "\"" + description + "\"," +
                   "\"" + department + "\"," +
                   "\"" + blockAddress + "\"," +
                   "\"" + street + "\"," +
                   "\"" + ward + "\"," +
                   "\"" + methodReceived + "\"," +
                   "\"" + createdDate + "\"";
        }
    }

    // load all drainage requests from CSV
    public static List<DrainageRequest> loadRequestsFromCSV(String csvFilePath) {
        List<DrainageRequest> requests = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = parseCSVLine(line);

                if (columns.length >= 7) {
                    String description = columns[0].replace("\"", "").trim();
                    String department = columns[1].replace("\"", "").trim();
                    String blockAddress = columns[2].replace("\"", "").trim();
                    String street = columns[3].replace("\"", "").trim();
                    String ward = columns[4].replace("\"", "").trim();
                    String methodReceived = columns[5].replace("\"", "").trim();
                    String createdDate = columns[6].replace("\"", "").trim();

                    DrainageRequest request = new DrainageRequest(description, department, blockAddress,
                                                                   street, ward, methodReceived, createdDate);
                    requests.add(request);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }

        return requests;
    }

    // save all drainage requests back to CSV
    public static void saveRequestsToCSV(String csvFilePath, List<DrainageRequest> requests) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            // Write header
            writer.append("\"Service Request Description\",\"Department\",\"Block/Address\",\"Street\",\"Ward\",\"Method Received\",\"Created Date\"\n");

            // Write each request
            for (DrainageRequest request : requests) {
                writer.append(request.toCSV()).append("\n");
            }

            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    // parse a CSV line considering quoted commas
    private static String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char ch : line.toCharArray()) {
            if (ch == '\"') {
                inQuotes = !inQuotes;
            } else if (ch == ',' && !inQuotes) {
                result.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        result.add(current.toString());

        return result.toArray(new String[0]);
    }

    // update an existing drainage request
    public static void updateDrainageRequest(String csvFilePath) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get search criteria from user
        System.out.print("Enter the Street to search: ");
        String searchStreet = scanner.nextLine().trim();

        System.out.print("Enter the Ward number to search (e.g., 1, 10): ");
        String searchWardNumber = scanner.nextLine().trim();
        String searchWard = "WARD " + searchWardNumber;

        System.out.print("Enter the Method Received to search: ");
        String searchMethodReceived = scanner.nextLine().trim();

        // Step 2: Load all requests & filter
        List<DrainageRequest> allRequests = loadRequestsFromCSV(csvFilePath);
        List<DrainageRequest> matchedRequests = new ArrayList<>();

        for (DrainageRequest req : allRequests) {
            if (req.getStreet().equalsIgnoreCase(searchStreet) &&
                req.getWard().equalsIgnoreCase(searchWard) &&
                req.getMethodReceived().equalsIgnoreCase(searchMethodReceived)) {
                matchedRequests.add(req);
            }
        }

        // Step 3: Display matched requests
        if (matchedRequests.isEmpty()) {
            System.out.println("No drainage requests found matching the provided criteria.");
            return;
        }

        System.out.println("\nMatched Drainage Requests:");
        for (int i = 0; i < matchedRequests.size(); i++) {
            System.out.println((i + 1) + ". " + matchedRequests.get(i));
        }

        // Step 4: Confirm selection
        DrainageRequest selectedRequest = null;

        if (matchedRequests.size() == 1) {
            System.out.print("\nIs this the request you want to update? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (confirmation.equals("yes") || confirmation.equals("y")) {
                selectedRequest = matchedRequests.get(0);
            } else {
                System.out.println("Update operation cancelled.");
                return;
            }
        } else {
            System.out.print("\nEnter the number of the request you want to update: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= matchedRequests.size()) {
                    selectedRequest = matchedRequests.get(choice - 1);
                } else {
                    System.out.println("Invalid selection. Update operation cancelled.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Update operation cancelled.");
                return;
            }
        }

        // Step 5: Choose which field to update
        System.out.println("\nWhich part do you want to edit?");
        System.out.println("1. Description");
        System.out.println("2. Department");
        System.out.println("3. Block/Address");
        System.out.println("4. Street");
        System.out.println("5. Ward");
        System.out.println("6. Method Received");
        System.out.println("7. Created Date");
        System.out.print("Enter the number corresponding to the field: ");

        int fieldChoice = -1;
        try {
            fieldChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Update operation cancelled.");
            return;
        }

        // Step 6: Get updated information
        String newValue = "";
        switch (fieldChoice) {
            case 1:
                System.out.print("Enter new Description: ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setDescription(newValue);
                break;
            case 2:
                System.out.print("Enter new Department: ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setDepartment(newValue);
                break;
            case 3:
                System.out.print("Enter new Block/Address: ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setBlockAddress(newValue);
                break;
            case 4:
                System.out.print("Enter new Street: ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setStreet(newValue);
                break;
            case 5:
                System.out.print("Enter new Ward number (e.g., 1, 10): ");
                String newWardNumber = scanner.nextLine().trim();
                String formattedNewWard = "WARD " + newWardNumber;
                selectedRequest.setWard(formattedNewWard);
                break;
            case 6:
                System.out.print("Enter new Method Received: ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setMethodReceived(newValue);
                break;
            case 7:
                System.out.print("Enter new Created Date (e.g., JUN 04, 2024 10:29:22 AM): ");
                newValue = scanner.nextLine().trim();
                selectedRequest.setCreatedDate(newValue);
                break;
            default:
                System.out.println("Invalid selection. Update operation cancelled.");
                return;
        }

        // Step 7: Save updated requests back to CSV
        saveRequestsToCSV(csvFilePath, allRequests);
    }
}
