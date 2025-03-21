package bms;
import java.util.*;

public class Order {	
    public static final String STATUS_PENDING = "📌 Pending";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_COMPLETED = "✅ Completed";
    
    private String status;
    private static int orderIdCounter = 0;
    private int orderId;
    private ArrayList<BakeryItem> orderlists;
    private BakeryManagementSystem bms; // Refer to BMS
    Scanner sc = new Scanner(System.in);

    public Order(BakeryManagementSystem bms) {	
        this.bms = bms; 
        this.orderlists = new ArrayList<>(); // Initialize an empty array list of items
        this.orderId = ++orderIdCounter; // Let the orderId become incremental
        this.status = STATUS_PENDING;
    }

    public void displayStatus() {
        System.out.println("Order Status: " + status);
    }

    public int getOrderId() {
        return orderId;
    }

    public void PlaceOrder() {	
        int continueOrder;
        bms.displayInventory();
        displayStatus();

        do {
            System.out.println("Enter Item Name: ");
            String iName = sc.nextLine();  // Get the item name from user input

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();  // Get the quantity from user input
            sc.nextLine();           // Consume the newline character left by nextInt()

            // Retrieve the BakeryItem object from inventory using the item name
            BakeryItem item = bms.getInventory(iName); 

            if (item != null) {  // Check if the item exists in inventory
                if (item.getQuantity() >= qty) 
                {  // Check if enough stock is available
                    item.setQuantity(item.getQuantity() - qty);  // Deduct ordered quantity from inventory
                    // Add the selected item to the order list
                    orderlists.add(new BakeryItem(item.getItemName(), item.getPrice(), qty)); 
                    System.out.println("✔️ Added: " + qty + " x " + item.getItemName());
                } else 
                {
                    System.out.println("Not enough stock available.");  // Display an error if stock is insufficient
                }
            } else
            {
                System.out.println("Item not found.");  // Display an error if item does not exist
            }

            status = STATUS_IN_PROGRESS;
            displayStatus();      		 // Display current order status

     
            System.out.println("Enter 0 to finish order, any other number to continue.");
            continueOrder = sc.nextInt();  
            sc.nextLine();

        } while (continueOrder != 0);  
        displayReceipt();
    } 
    
    
    public void displayReceipt() {
        System.out.println("\n🧾 Order Receipt:");
        System.out.println("Order ID: " + orderId);
        double total = 0;

        for (BakeryItem item : orderlists) {
            System.out.println(item.getQuantity() + " x " + item.getItemName() + " - RM" + item.getPrice());
            total += item.getPrice() * item.getQuantity();
        }

        System.out.println("Total: RM" + String.format("%.2f", total));
        System.out.println("----------------------------");
        status = STATUS_COMPLETED;
        displayStatus();
    }

    public ArrayList<BakeryItem> getOrderlists() {
        return orderlists;
    }
}
/*need to create another array list to store each order* (Part4)*/

	
	

