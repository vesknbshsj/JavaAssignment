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
    Scanner sc = new Scanner(System.in);

    public Order(BakeryManagementSystem bms) {	
        this.bms = bms; 
        orderlists = new ArrayList<>();
        orderId = ++orderIdCounter;
        status = STATUS_PENDING;
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
    
    
    public void displayReceipt() {
        System.out.println("\nOrder Receipt:");
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


	
	

