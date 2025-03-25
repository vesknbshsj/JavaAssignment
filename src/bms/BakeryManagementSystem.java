package bms;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.*;

public class BakeryManagementSystem {

    public String name;
    public double p;
    public int qty;
    private ArrayList<BakeryItem> inventory = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("##0.00");


    private ArrayList<Order> orderList = new ArrayList<>();  

    public BakeryManagementSystem() {
        initializeItem();
    }


    public void initializeItem() {    
        inventory.add(new BakeryItem("White Bread", 1.55, 50));
        inventory.add(new BakeryItem("Baguette", 5.00, 30));
        inventory.add(new BakeryItem("Chocolate Cake", 7.50, 20));
        inventory.add(new BakeryItem("Vanilla Cake", 7.50, 20));
        inventory.add(new BakeryItem("Cookies", 3.00, 100));
        inventory.add(new BakeryItem("Croissant", 2.50, 40));
        inventory.add(new BakeryItem("Danish Pastry", 4.00, 25));
        inventory.add(new BakeryItem("Muffin", 2.00, 45));
    }

    public void startSystem() {
        int choice;
        do {
            System.out.println("\n-----Bakery Management System-----");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item"); 
            System.out.println("4. Display Inventory");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1: addItem(); break;
                case 2: updateItem(); break;
                case 3: removeItem(); break;
                case 4: displayInventory(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while(choice != 0);
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        name = sc.nextLine();
        System.out.print("Enter price: ");
        p = sc.nextDouble();
        System.out.print("Enter quantity: ");
        qty = sc.nextInt();
        inventory.add(new BakeryItem(name, p, qty));
        System.out.println("Item added.");
    }

    private void updateItem() {
        System.out.print("Enter item name to update: ");
        name = sc.nextLine();
        for (BakeryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                System.out.print("Enter new price: ");
                item.setPrice(sc.nextDouble());
                System.out.print("Enter new quantity: ");
                item.setQuantity(sc.nextInt());
                System.out.println("Item updated.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private void removeItem() {
        System.out.print("Enter item name to remove: ");
        name = sc.nextLine();
        inventory.removeIf(item -> item.getItemName().equalsIgnoreCase(name));
        System.out.println("Item removed.");
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        if (inventory.isEmpty()) {
            System.out.println("No items available.");
        } else {
            int index = 1; 
            System.out.printf("%-10s %-20s %-10s %-10s%n", 
                "Item No.", "Item Name", "Price(RM)", "Quantity");
            System.out.println("--------------------------------------------------------");
            for (BakeryItem item : inventory) {
                System.out.printf("%-10d %-20s %-10s %-10d%n", 
                    index++, item.getItemName(), df.format(item.getPrice()), item.getQuantity());
            }
            System.out.println("--------------------------------------------------------"); 
        }
    }

    public BakeryItem getInventory(String itemName) {
        for (BakeryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item; 
            }
        }
        return null;
    }

    
    public void placeOrder() {
        Order newOrder = new Order(this);
        newOrder.PlaceOrder();
        orderList.add(newOrder);
    }
// methods below can be implemented in Report Class
    public void displayOrderHistory() {
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("\n=== Order History ===");
            for (Order order : orderList) {
                order.displayReceipt();
            }
        }
    }

    public void displayTotalSales() {
        if (orderList.isEmpty()) {
            System.out.println("No sales data available.");
        } else {
            double total = 0;
            for (Order order : orderList) {
                for (BakeryItem item : order.getOrderlists()) {
                    total += item.getPrice() * item.getQuantity();
                }
            }
            System.out.printf("\nTotal Sales: RM%.2f\n", total);
        }
    }

    public void searchOrder(int orderId) {
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                System.out.println("\n=== Order Found ===");
                order.displayReceipt();
                return;
            }
        }
        System.out.println("Order not found.");
    }
}