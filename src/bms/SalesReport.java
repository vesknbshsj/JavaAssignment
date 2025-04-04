package bms;
import java.time.LocalDate;

public class SalesReport implements IReportGenerator {
    private BakeryManagementSystem bms;
    /***************************************************************************************
     *    Title: Java Platform, Standard Edition 8 API Specification - Class LocalDate
     *    Author: Oracle Corporation
     *    Date: March 2014
     *    Code version: Java SE 8
     *    Availability: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
     ***************************************************************************************/
    private LocalDate date = LocalDate.now();
    public SalesReport(BakeryManagementSystem bms) {
        this.bms = bms;
    }


    public void generateReport() {
        System.out.println("\n===== SALES REPORT =====");
        System.out.println("Report date: " + date);
        // Display all orders
        System.out.println("\n--- ALL ORDERS ---");
        if (bms.getOrderList().isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : bms.getOrderList()) {
                order.displayReceipt();  // Show receipt for each order
            }
        }

        // Display total sales and calculate average spending
        System.out.println("\n--- SALES SUMMARY ---");
        double total = 0;
        int totalOrders = bms.getOrderList().size();

        for (Order order : bms.getOrderList()) {
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
}
