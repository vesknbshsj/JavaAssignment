package bms;
import java.util.*;

/**
 * Main entry point for the Bakery Management System
 * Handles the main menu and user interaction
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        ArrayList<Order> orderList = new ArrayList<>();
        Report report = new Report(inventory, orderList);
        ArrayList<Customer> customers = new ArrayList<>();

        int userType;

        // ==== Submenu: Select user type	 ====
        do {
            System.out.println("Welcome to the Bakery Management System");
            System.out.println("Select User Type:");
            System.out.println("1. Admin");
            System.out.println("2. Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            userType = sc.nextInt();
            sc.nextLine();

            if (userType == 1) {
                int choice;
                do {
                    System.out.println("\n----- Admin Menu -----");
                    System.out.println("1. Manage Inventory");
                    System.out.println("2. Place New Order");
                    System.out.println("3. Search Order by Customer Name");
                    System.out.println("0. Logout");
                    System.out.print("Enter choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            inventory.manageInventory();
                            break;
                        case 2:
                            System.out.print("Enter customer name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter phone number: ");
                            String phone = sc.nextLine();
                            Customer customer = new Customer(customers.size() + 1, name, phone);
                            customers.add(customer);

                            Order newOrder = new Order(inventory, customer);
                            newOrder.placeOrder();
                            orderList.add(newOrder);
                            break;
                        case 3:
                            System.out.print("Enter customer name: ");
                            String customerName = sc.nextLine();
                            Customer.viewCustomerOrders(customerName, orderList);
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } while (choice != 0);

            } else if (userType == 2) {
                int choice;
                do {
                    System.out.println("\n----- Management Menu -----");
                    System.out.println("1. Generate Sales Report");
                    System.out.println("2. Generate Inventory Report");
                    System.out.println("0. Logout");
                    System.out.print("Enter choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1:
                            report.generateSalesReport();
                            break;
                        case 2:
                            report.generateInventoryReport();
                            break;
                        case 0:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } while (choice != 0);

            } else if (userType == 0) {
                System.out.println("Exiting system...");
            } else {
                System.out.println("Invalid user type. Try again.");
            }

        } while (userType != 0);
    }
}
