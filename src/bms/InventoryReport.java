package bms;

import java.util.*;
import java.time.LocalDate;

/**
 * Generates a detailed inventory report
 * including current inventory and sales per item.
 */
public class InventoryReport implements IReportGenerator {
    private BakeryManagementSystem bms;
    /***************************************************************************************
    *    Title: Java Platform, Standard Edition 8 API Specification - Class LocalDate
    *    Author: Oracle Corporation
    *    Date: March 2014
    *    Code version: Java SE 8
    *    Availability: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
    ***************************************************************************************/
    private LocalDate date = LocalDate.now();
  

    public InventoryReport(BakeryManagementSystem bms) {
        this.bms = bms;
    }

    // Override method from IReportGenerator
    public void generateReport() {
        System.out.println("\n===== INVENTORY REPORT =====");
        System.out.println("Report date: " + date);

        // Display current inventory
        bms.displayInventory();

        // Display sales per item
        displaySalesPerItem();
    }

    
    // Display how many units sold for each item
    public void displaySalesPerItem() {
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

        for (Order order : bms.getOrderList()) {
            for (BakeryItem item : order.getOrderlists()) {
                salesMap.put(item.getItemName(),
                    salesMap.getOrDefault(item.getItemName(), 0) + item.getQuantity());
            }
        }

        // Display results
        System.out.println("\n--- SALES PER ITEM ---");
        for (BakeryItem item : bms.getFullInventory()) {
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

 
}