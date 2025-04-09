package bms;
import java.util.*;

/**
 * Manages the bakery inventory items
 */
public class Inventory implements InventoryInterface {
	 Scanner sc = new Scanner(System.in);
    private ArrayList<BakeryItem> inventory = new ArrayList<>();
   
    
    /**
     * Constructor - initializes with default bakery items
     */
    public Inventory() {
        initializeItem();
    }
    
    /**
     * Populates initial inventory items
     */
    private void initializeItem() {
        for (int i = 0; i < DEFAULT_ITEMS.length; i++) {
            inventory.add(new BakeryItem(
                DEFAULT_ITEMS[i], 
                DEFAULT_PRICES[i], 
                DEFAULT_QUANTITIES[i]
            ));
        }
    }
    
    /**
     * Displays and manages inventory sub-menu
     */
    public void manageInventory() {
        int choice;
        do {
            System.out.println("\n-----Inventory Management-----");
            System.out.println("1. Add New Item");
            System.out.println("2. Update Existing Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Display Current Inventory");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1: 
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    // Apply MAX_QUANTITY constraint
                    if (quantity > MAX_QUANTITY) {
                        System.out.println("Quantity exceeds maximum limit of " + MAX_QUANTITY + ". Setting to maximum.");
                        quantity = MAX_QUANTITY;
                    }
                    sc.nextLine();
                    addItem(name, price, quantity);
                    break;
                case 2: 
                    System.out.print("Enter item name to update: ");
                    String updateName = sc.nextLine();
                    BakeryItem item = getItem(updateName);
                    
                    if (item != null) {
                        System.out.print("Enter new price (current: " + item.getPrice() + "): ");
                        double newPrice = sc.nextDouble();
                        System.out.print("Enter new quantity (current: " + item.getQuantity() + "): ");
                        int newQuantity = sc.nextInt();
                        // Apply MAX_QUANTITY constraint
                        if (newQuantity > MAX_QUANTITY) {
                            System.out.println("Quantity exceeds maximum limit of " + MAX_QUANTITY + ". Setting to maximum.");
                            newQuantity = MAX_QUANTITY;
                        }
                        sc.nextLine();
                        updateItem(updateName, newPrice, newQuantity);
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 3: 
                    System.out.print("Enter item name to remove: ");
                    removeItem(sc.nextLine());
                    break;
                case 4: 
                    displayInventory(); 
                    break;
                case 0: 
                    System.out.println("Returning to main menu..."); 
                    break;
                default: 
                    System.out.println("Invalid choice.");
            }
        } while(choice != 0);
    }
    
    /**
     * Adds new item to inventory
     */
   
    public void addItem(String name, double price, int quantity) {
        // Ensure quantity doesn't exceed MAX_QUANTITY
        if (quantity > MAX_QUANTITY) {
            quantity = MAX_QUANTITY;
        }
        inventory.add(new BakeryItem(name, price, quantity));
        System.out.println("Item added successfully!");
    }
    
    /**
     * Updates existing item's price and quantity
     */
  
    public void updateItem(String name, double price, int quantity) {
        BakeryItem item = getItem(name);
        
        if (item != null) {
            item.setPrice(price);
            // Ensure quantity doesn't exceed MAX_QUANTITY
            if (quantity > MAX_QUANTITY) {
                quantity = MAX_QUANTITY;
            }
            item.setQuantity(quantity);
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }
    
    /**
     * Removes item from inventory
     */
    public void removeItem(String name) {
        // Flag to track if we found and removed the item
        boolean removed = false;
        
        // Loop through the inventory list
        for (int i = 0; i < inventory.size(); i++) {
            BakeryItem item = inventory.get(i);
            
            // Check if this is the item we want to remove (case-insensitive comparison)
            if (item.getItemName().equalsIgnoreCase(name)) {
                // Remove the item at this position
                inventory.remove(i);
                removed = true;
                break; // Exit the loop once we've found and removed the item
            }
        }
        
        // Print appropriate message based on whether we removed an item
        if (removed) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }
    
    /**
     * Displays current inventory in table format
     */

    public void displayInventory() {
        System.out.println("\n-----Current Inventory-----");
        /***************************************************************************************
        *    Title: Formatting Output in Java with printf()
        *    Author: Baeldung
        *    Date: 2022
        *    Availability: https://www.baeldung.com/java-printstream-printf
        ***************************************************************************************/
        System.out.printf("%-3s %-20s %-10s %-10s %-15s\n", "No", "Item Name", "Price(RM)", "Quantity", "Stock Status");
        
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < inventory.size(); i++) {
            BakeryItem item = inventory.get(i);
            String stockStatus = getStockStatus(item.getQuantity());
            System.out.printf("%-3d %-20s %-10.2f %-10d %-15s\n", 
                i+1, item.getItemName(), item.getPrice(), item.getQuantity(), stockStatus);
        }
    }
    
    /**
     * Determines stock status based on quantity and MAX_QUANTITY
     * @param quantity Current quantity of item
     * @return Status string
     */
    private String getStockStatus(int quantity) {
        double percentFull = ((double)quantity / MAX_QUANTITY) * 100;
        if (percentFull <= 10) {
            return "Low Stock";
        } else if (percentFull >= 90) {
            return "Full Stock";
        } else {
            return "In Stock";
        }
    }
    
    /**
     * Finds inventory item by name (case-insensitive)
     */
    @Override
    public BakeryItem getItem(String itemName) {
        for (BakeryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Gets a copy of the current inventory list
     * @return List of all bakery items in inventory
     */
    public List<BakeryItem> getFullInventory() {
        return new ArrayList<>(inventory); 
    }
    
    /**
     * Updates inventory quantities after an order
     * @param item The bakery item to update
     * @param quantity The quantity ordered
     * @return true if update successful, false if insufficient stock
     */
    public boolean updateQuantity(String itemName, int quantity) {
        BakeryItem item = getItem(itemName);
        if (item != null && item.getQuantity() >= quantity) {
            item.setQuantity(item.getQuantity() - quantity);
            return true;
        }
        return false;
    }
} 