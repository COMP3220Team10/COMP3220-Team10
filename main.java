import java.util.InputMismatchException;
import java.util.Scanner;

public class WasteManagementSystem {
    public static void main(String[] args) {
        mainMenu();
    }

    // The main menu
    private static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Infinite loop to display the main menu
            // Display menu options
            System.out.println("\nHello! Welcome to the Drainage Request Management System!");
            System.out.println("Please select from one of the menu options below:");
            System.out.println("1. I would like to request information about an existing service request.");
            System.out.println("2. I would like to file a new service request.");
            System.out.println("3. I would like to see drainage requests in a specific area.");
            System.out.println("4. I would like to update an existing drainage request.");
            System.out.println("5. I would like to exit this menu.");
            System.out.print("Please enter your selection: ");

            int userSelection = -1;
            try {
                userSelection = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                // Handle each menu option
                switch (userSelection) {
                    case 1:
                        System.out.println("You selected: Request information about an existing service request.");
                        requestInformation();
                        break;
                    case 2:
                        System.out.println("You selected: File a new service request.");
                        fileNewRequest();
                        break;
                    case 3:
                        System.out.println("You selected: View drainage requests in a specific area.");
                        viewRequestsInArea();
                        break;
                    case 4:
                        System.out.println("You selected: Update an existing drainage request.");
                        updateRequest();
                        break;
                    case 5:
                        System.out.println("Exiting the menu. Goodbye!");
                        scanner.close(); // Close the scanner before exiting
                        return; // Exit the loop and method
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 5.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void requestInformation() {
        // Placeholder method for requesting information
        System.out.println("Functionality to request information about an existing service request.");
    }

    private static void fileNewRequest() {
        // Placeholder method for filing a new service request
        System.out.println("Functionality to file a new service request.");
    }

    private static void viewRequestsInArea() {
        // Placeholder method to view drainage requests in a specific area
        System.out.println("Functionality to view drainage requests in a specific area.");
    }

    private static void updateRequest() {
        // Placeholder method to update an existing request
        System.out.println("Functionality to update an existing drainage request.");
    }
}
