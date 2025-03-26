package bms;
import java.util.*;

/**
 * Represents a customer order containing multiple bakery items
 */
public class Order {
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_COMPLETED = "Completed";
    
    private static int orderIdCounter = 0;
    private int orderId;
    private String status;
    private ArrayList<BakeryItem> orderlists;
    private BakeryManagementSystem bms;
    private Customer customer;
    private Scanner sc = new Scanner(System.in);

    /**
     * Creates new order associated with customer
     */
    public Order(BakeryManagementSystem bms, Customer customer) {
        this.bms = bms;
        this.customer = customer;
        this.orderId = ++orderIdCounter;
        this.status = STATUS_PENDING;
        this.orderlists = new ArrayList<>();
    }

    /**
     * Main order processing method
     */
    public void PlaceOrder() {
        int continueOrder;
        bms.displayInventory();
        displayStatus();
        
        do {
            System.out.print("Enter item name: ");
            String itemName = sc.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            
            BakeryItem inventoryItem = bms.getInventory(itemName);
            if (inventoryItem != null) {
                if (inventoryItem.getQuantity() >= quantity) {
                    inventoryItem.setQuantity(inventoryItem.getQuantity() - quantity);
                    orderlists.add(new BakeryItem(
                        inventoryItem.getItemName(),
                        inventoryItem.getPrice(),
                        quantity
                    ));
                    System.out.println(quantity + " x " + inventoryItem.getItemName() + " added to order");
                } else {
                    System.out.println("Insufficient stock! Available: " + inventoryItem.getQuantity());
                }
            } else {
                System.out.println("Item not found!");
            }
            
            status = STATUS_IN_PROGRESS;
            displayStatus();
            
            System.out.print("Add more items? (0 to finish): ");
            continueOrder = sc.nextInt();
            sc.nextLine();
        } while (continueOrder != 0);
        
        displayReceipt();
        customer.addOrder(this);
    }

    /**
     * Displays order receipt with all items and total
     */
    public void displayReceipt() {
        System.out.println("\n-----Order Receipt-----");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Phone Number: "+customer.getPhone());
        System.out.println("Items:");
        
        double total = 0;
        for (BakeryItem item : orderlists) {
            double itemTotal = item.getPrice() * item.getQuantity();
            System.out.printf("- %d x %s \tRM%.2f \t= RM%.2f\n",
                item.getQuantity(), item.getItemName(), item.getPrice(), itemTotal);
            total += itemTotal;
        }
        
        System.out.println("-----------------------");
        System.out.printf("Total: RM%.2f\n", total);
        status = STATUS_COMPLETED;
        displayStatus();
    }

    /**
     * Displays current order status
     */
    private void displayStatus() {
        System.out.println("Status: " + status);
    }

    /**
     * Gets the customer who placed this order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Gets list of items in this order
     */
    public ArrayList<BakeryItem> getOrderlists() {
        return orderlists;
    }
}