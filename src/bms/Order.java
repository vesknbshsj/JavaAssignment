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
    private Inventory inventory;
    private Customer customer;
    private Scanner sc = new Scanner(System.in);

    /**
     * Creates new order associated with customer
     */
    public Order(Inventory inventory, Customer customer) {
        this.inventory = inventory;
        this.customer = customer;
        this.orderId = ++orderIdCounter;
        this.status = STATUS_PENDING;
        this.orderlists = new ArrayList<>();
    }

    /**
     * Main order processing method
     */
    public void placeOrder() {
        int continueOrder;
        inventory.displayInventory();
        displayStatus();
        
        do {
            System.out.print("Enter item name: ");
            String itemName = sc.nextLine();
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            
            addItemToOrder(itemName, quantity);
            
            status = STATUS_IN_PROGRESS;
            displayStatus();
            
            System.out.print("Add more items? (0 to finish): ");
            continueOrder = sc.nextInt();
            sc.nextLine();
        } while (continueOrder != 0);
        
        displayReceipt();
        finalizeOrder();
    }

    /**
     * Adds an item to the current order
     * @param itemName Name of the item to add
     * @param quantity Quantity to add
     * @return true if successfully added, false otherwise
     */
    public boolean addItemToOrder(String itemName, int quantity) {
        BakeryItem inventoryItem = inventory.getItem(itemName);
        if (inventoryItem != null) {
            if (inventory.updateQuantityAfterOrder(itemName, quantity)) {
                orderlists.add(new BakeryItem(
                    inventoryItem.getItemName(),
                    inventoryItem.getPrice(),
                    quantity
                ));
                System.out.println(quantity + " x " + inventoryItem.getItemName() + " added to order");
                return true;
            } else {
                System.out.println("Insufficient stock! Available: " + inventoryItem.getQuantity());
            }
        } else {
            System.out.println("Item not found!");
        }
        return false;
    }
    
    /**
     * Completes the order process
     */
    public void finalizeOrder() {
        customer.addOrder(this);
        System.out.println("Order #" + orderId + " has been finalized.");
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
        
        double total = calculateTotal();
        
        System.out.println("-----------------------");
        System.out.printf("Total: RM%.2f\n", total);
        status = STATUS_COMPLETED;
        displayStatus();
    }
    
    /**
     * Calculates the total cost of the order
     * @return Total cost
     */
    public double calculateTotal() {
        double total = 0;
        for (BakeryItem item : orderlists) {
            double itemTotal = item.getPrice() * item.getQuantity();
            /***************************************************************************************
            *    Title: Formatting Output in Java with printf()
            *    Author: Baeldung
            *    Date: 2022
            *    Availability: https://www.baeldung.com/java-printstream-printf
            ***************************************************************************************/
            System.out.printf("- %d x %s \tRM%.2f \t= RM%.2f\n",
                item.getQuantity(), item.getItemName(), item.getPrice(), itemTotal);
            total += itemTotal;
        }
        return total;
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