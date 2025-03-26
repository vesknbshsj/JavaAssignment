package bms;
import java.util.*;

public class Order {	
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_COMPLETED = "Completed";
    
    private String status;
    private static int orderIdCounter = 0;
    private int orderId;
    private ArrayList<BakeryItem> orderlists;
    private BakeryManagementSystem bms;
    private Customer customer;
    Scanner sc = new Scanner(System.in);

    public Order() {	
        this.orderlists = new ArrayList<>();
        this.orderId = ++orderIdCounter;
        this.status = STATUS_PENDING;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void PlaceOrder(BakeryManagementSystem bms, Customer customer) {	
        this.bms = bms;
        this.customer = customer;
        int continueOrder;
        bms.displayInventory();
        displayStatus();

        do {
            System.out.println("Enter Item Name: ");
            String iName = sc.nextLine();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();  
            sc.nextLine();

            BakeryItem item = bms.getInventory(iName); 

            if (item != null) { 
                if (item.getQuantity() >= qty) 
                {  
                    item.setQuantity(item.getQuantity() - qty);
                    orderlists.add(new BakeryItem(item.getItemName(), item.getPrice(), qty)); 
                    System.out.println("Added: " + qty + " x " + item.getItemName());
                } else 
                {
                    System.out.println("Not enough stock available.");
                }
            } else
            {
                System.out.println("Item not found.");
            }

            status = STATUS_IN_PROGRESS;
            displayStatus();

            System.out.println("Enter 0 to finish order, any other number to continue.");
            continueOrder = sc.nextInt();  
            sc.nextLine();

        } while (continueOrder != 0);  
        displayReceipt();
    }

    public void displayStatus() {
        System.out.println("Order Status: " + status);
    }

    public int getOrderId() {
        return orderId;
    }

    public void displayReceipt() {
        System.out.println("\n=== Order Receipt ===");
        System.out.println("Order ID: " + orderId);
        if (customer != null) {
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Phone: " + customer.getPhoneNumber());
        }
        System.out.println("Status: " + status);
        System.out.println("\nOrdered Items:");
        System.out.println("----------------------------");

        double total = 0;
        for (BakeryItem item : orderlists) {
            System.out.printf("%-4d x %-20s RM%-8.2f\n", 
                item.getQuantity(), 
                item.getItemName(), 
                item.getPrice() * item.getQuantity());
            total += item.getPrice() * item.getQuantity();
        }

        System.out.println("----------------------------");
        System.out.printf("Total: RM%.2f\n", total);
        System.out.println("============================\n");
        status = STATUS_COMPLETED;
        displayStatus();
    }

    public ArrayList<BakeryItem> getOrderlists() {
        return orderlists;
    }

    public double getTotal() {
        double total = 0;
        for (BakeryItem item : orderlists) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}