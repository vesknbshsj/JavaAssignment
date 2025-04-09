package bms;
import java.util.*;
import java.time.LocalDate;

/**
 * Generates both inventory and sales reports
 */
public class Report {
    private Inventory inventory;
    private ArrayList<Order> orderList;
    /***************************************************************************************
    *    Title: Java Platform, Standard Edition 8 API Specification - Class LocalDate
    *    Author: Oracle Corporation
    *    Date: March 2014
    *    Code version: Java SE 8
    *    Availability: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    ***************************************************************************************/
    LocalDate date = LocalDate.now();
  
    /**
     * Creates a new report generator
     */
    public Report(Inventory inventory, ArrayList<Order> orderList) {
        this.inventory = inventory;
        this.orderList = orderList;
    }

    /**
     * Generates a sales report showing all orders and summaries
     */
    public void generateSalesReport() {
        System.out.println("\n===== SALES REPORT =====");
        System.out.println("Report date: " + date);
        
        // Display all orders
        System.out.println("\n--- ALL ORDERS ---");
        if (orderList.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orderList) {
                order.displayReceipt();  // Show receipt for each order
            }
        }

        // Display total sales and calculate average spending
        System.out.println("\n--- SALES SUMMARY ---");
        double total = 0;
        int totalOrders = orderList.size();

        for (Order order : orderList) {
            for (BakeryItem item : order.getOrderlists()) {
                total += item.getPrice() * item.getQuantity();  // Accumulate total
            }
        }

        System.out.printf("Total Sales: RM%.2f\n", total);

        // Calculate and display average spending per order
        if (totalOrders > 0) {
            double avg = total / totalOrders;
            System.out.printf("Average Spending per Order: RM%.2f\n", avg);
        }
    }
    
    /**
     * Generates an inventory report with current inventory and sales per item
     */
    public void generateInventoryReport() {
        System.out.println("\n===== INVENTORY REPORT =====");
        System.out.println("Report date: " + date);

        // Display current inventory
        inventory.displayInventory();

        // Display sales per item
        displaySalesPerItem();
    }
    
    /**
     * Display how many units sold for each item
     */
    private void displaySalesPerItem() {
        // Map to store total quantity sold per item
    	/***************************************************************************************
    	*    Title: Java Platform, Standard Edition 8 API Specification - Interface Map<K,V>
    	*    Author: Oracle Corporation
    	*    Date: March 2014
    	*    Code version: Java SE 8
    	*    Availability: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
    	*
    	***************************************************************************************/
    	Map<String, Integer> salesMap = new HashMap<>();

        for (Order order : orderList) {
            for (BakeryItem item : order.getOrderlists()) {
                salesMap.put(item.getItemName(),
                    salesMap.getOrDefault(item.getItemName(), 0) + item.getQuantity());
            }
        }

        // Display results
        System.out.println("\n--- SALES PER ITEM ---");
        for (BakeryItem item : inventory.getFullInventory()) {
            String name = item.getItemName();
            int sold = salesMap.getOrDefault(name, 0);
            /***************************************************************************************
            *    Title: Formatting Output in Java with printf()
            *    Author: Baeldung
            *    Date: 2022
            *    Availability: https://www.baeldung.com/java-printstream-printf
            ***************************************************************************************/
            System.out.printf("%-20s : %d sold\n", name, sold);
        }
    }
    
    /**
     * Helper method to calculate and display total sales
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
}