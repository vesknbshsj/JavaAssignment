package bms;
import java.util.*;

/**
 * Main entry point for the Bakery Management System
 * Handles the main menu and user interaction
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BakeryManagementSystem system = new BakeryManagementSystem();
        Report report = new Report(system); 
        int choice;
        
        do {
            System.out.println("\n-----Bakery Management System-----");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Place New Order");
            System.out.println("3. Generate Report"); 
            System.out.println("4. Search Order by Customer Name");
            System.out.println("5. Search Order by ID");
            System.out.println("6. View All Customers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();    
            
            switch(choice) {
                case 1: system.startSystem(); break;
                case 2: system.placeOrder(); break;
                case 3: report.generateFullReport(); break; 
                case 4: 
                    System.out.print("Enter customer name: ");
                    system.viewCustomerOrders(sc.nextLine());
                    break;
                case 5: 
                    System.out.print("Enter Order ID: ");
                    system.searchOrder(sc.nextInt());
                    sc.nextLine();
                    break;
                case 6: 
                    system.displayAllCustomers();
                    break;
                case 0: System.out.println("Exiting system..."); break;    
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}