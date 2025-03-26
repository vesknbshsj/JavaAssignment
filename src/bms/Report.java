package bms;
import java.util.*;
import java.time.LocalDate;
/**
 * Handles all reporting functions for the bakery system
 */
public class Report {
    private BakeryManagementSystem bms;
    LocalDate date = LocalDate.now();
    public Report(BakeryManagementSystem bms) {
        this.bms = bms;
    }

    /**
     * Generates comprehensive system report including:
     * - All orders
     * - Total sales
     * - Current inventory
     */
    public void generateFullReport() {
        System.out.println("\n===== BAKERY SYSTEM REPORT =====");
        
        // 1. Display all orders
        System.out.println("Report date: "+date);
        System.out.println("\n--- ALL ORDERS ---");
        if (bms.getOrderList().isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : bms.getOrderList()) {
                order.displayReceipt();
            }
        }
        
        // 2. Display total sales
        System.out.println("\n--- SALES SUMMARY ---");
        double total = calculateTotalSales();
        System.out.printf("Total Sales: RM%.2f\n", total);
        
        // 3. Display inventory
        System.out.println("\n--- CURRENT INVENTORY ---");
        bms.displayInventory();
    }

    /**
     * Calculates total sales from all orders
     */
    private double calculateTotalSales() {
        double total = 0;
        for (Order order : bms.getOrderList()) {
            for (BakeryItem item : order.getOrderlists()) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
    }
}