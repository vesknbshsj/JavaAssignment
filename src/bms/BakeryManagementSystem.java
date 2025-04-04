package bms;
import java.util.*;

/**
 * Core system class that manages all bakery operations
 */
public class BakeryManagementSystem {
    private ArrayList<BakeryItem> inventory = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor - initializes with default bakery items
     */
    public BakeryManagementSystem() {
        initializeItem();
    }

    /**
     * Populates initial inventory items
     */
    private void initializeItem() {
        inventory.add(new BakeryItem("White Bread", 1.55, 50));
        inventory.add(new BakeryItem("Baguette", 5.00, 30));
        inventory.add(new BakeryItem("Chocolate Cake", 7.50, 20));
        inventory.add(new BakeryItem("Vanilla Cake", 7.50, 20));
        inventory.add(new BakeryItem("Cookies", 3.00, 100));
        inventory.add(new BakeryItem("Croissant", 2.50, 40));
        inventory.add(new BakeryItem("Danish Pastry", 4.00, 25));
        inventory.add(new BakeryItem("Muffin", 2.00, 45));
    }

    /**
     * Displays and manages inventory sub-menu
     */
    public void startSystem() {
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
                case 1: addItem(); break;
                case 2: updateItem(); break;
                case 3: removeItem(); break;
                case 4: displayInventory(); break;
                case 0: System.out.println("Returning to main menu..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while(choice != 0);
    }

    /**
     * Adds new item to inventory
     */
    private void addItem() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        
        inventory.add(new BakeryItem(name, price, quantity));
        System.out.println("Item added successfully!");
    }

    /**
     * Updates existing item's price and quantity
     */
    private void updateItem() {
        System.out.print("Enter item name to update: ");
        String name = sc.nextLine();
        BakeryItem item = getInventory(name);
        
        if (item != null) {
            System.out.print("Enter new price (current: " + item.getPrice() + "): ");
            item.setPrice(sc.nextDouble());
            System.out.print("Enter new quantity (current: " + item.getQuantity() + "): ");
            item.setQuantity(sc.nextInt());
            sc.nextLine();
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    /**
     * Removes item from inventory
     */
    private void removeItem() {
        System.out.print("Enter item name to remove: ");
        String name = sc.nextLine();
        boolean removed = inventory.removeIf(item -> item.getItemName().equalsIgnoreCase(name));
        System.out.println(removed ? "Item removed successfully!" : "Item not found!");
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
        System.out.printf("%-3s %-20s %-10s %-10s\n", "No", "Item Name", "Price(RM)", "Quantity");
        
        System.out.println("------------------------------------------------");
        for (int i = 0; i < inventory.size(); i++) {
            BakeryItem item = inventory.get(i);
            System.out.printf("%-3d %-20s %-10.2f %-10d\n", 
                i+1, item.getItemName(), item.getPrice(), item.getQuantity());
        }
    }

    /**
     * Finds inventory item by name (case-insensitive)
     */
    public BakeryItem getInventory(String itemName) {
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
     * Handles new order creation process
     */
    public void placeOrder() {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        Customer customer = new Customer(customers.size()+1, name, phone);
        customers.add(customer);
        
        Order newOrder = new Order(this, customer);
        newOrder.PlaceOrder();
        orderList.add(newOrder);
    }

    /**
     * Displays all past orders
     */
    public void displayOrderHistory() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        
        System.out.println("\n-----Order History-----");
        for (Order order : orderList) {
            order.displayReceipt();
        }
    }

    /**
     * Displays order history for a specific customer
     */
    public void viewCustomerOrders(String customerName) {
        System.out.println("\n-----Order History for " + customerName + "-----");
       
        for (Order order : orderList)
        {
            if (order.getCustomer().getName().equalsIgnoreCase(customerName))
            {
            	
            	{ 	
            		order.displayReceipt();}
          
             }return;
        }
        System.out.println("Order not found.");
       
    }

    /**
     * Calculates and displays total sales from all orders
     */
    public void displayTotalSales() {
        if (orderList.isEmpty()) {
            System.out.println("No sales data available.");
            return;
        }
        
        double total = 0;
        for (Order order : orderList) {
            for (BakeryItem item : order.getOrderlists()) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        System.out.printf("\nTotal Sales: RM%.2f\n", total);
    }

   
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        
        System.out.println("\n-----Registered Customers-----");
        /***************************************************************************************
        *    Title: Formatting Output in Java with printf()
        *    Author: Baeldung
        *    Date: 2022
        *    Availability: https://www.baeldung.com/java-printstream-printf
        ***************************************************************************************/
        System.out.printf("%-5s %-20s %-15s\n", "ID", "Name", "Phone Number");
        System.out.println("-----------------------------------------");
        for (Customer customer : customers) {
            System.out.printf("%-5d %-20s %-15s\n", 
                customer.getCustomerId(), 
                customer.getName(), 
                customer.getPhone());
        }
    }
    

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    
}