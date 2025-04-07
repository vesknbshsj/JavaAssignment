package bms;
import java.util.*;

/**
 * Main entry point for the Bakery Management System
 * Handles the main menu and user interaction
 */
public class Main {
    private static Inventory inventory;
    private static ArrayList<Order> orderList = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Initialize system components
        inventory = new Inventory();
        Report report = new Report(inventory, orderList);
        
        int choice;
        do {
            System.out.println("\n-----Bakery Management System-----");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Place New Order");
            System.out.println("3. Generate Sales Report"); 
            System.out.println("4. Generate Inventory Report"); 
            System.out.println("5. Search Order by Customer Name");
            System.out.println("6. View All Customers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();    
            
            switch(choice) {
                case 1: inventory.manageInventory(); break;
                case 2: placeOrder(); break;
                case 3: report.generateSalesReport(); break; 
                case 4: report.generateInventoryReport(); break;
                case 5: 
                    System.out.print("Enter customer name: ");
                    String customerName = sc.nextLine();
                    Customer.viewCustomerOrders(customerName, orderList);
                    break;
                case 6: 
                    Customer.displayAllCustomers(customers);
                    break;
                case 0: System.out.println("Exiting system..."); break;    
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
    
    /**
     * Handles new order creation process
     */
    private static void placeOrder() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        Customer customer = new Customer(customers.size()+1, name, phone);
        customers.add(customer);
        
        Order newOrder = new Order(inventory, customer);
        newOrder.placeOrder();
        orderList.add(newOrder);
    }
}