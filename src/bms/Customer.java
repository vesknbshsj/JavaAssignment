package bms;
import java.util.*;

/**
 * Represents a bakery customer
 */
public class Customer {
    private int customerId;
    private String name, phone;
    private ArrayList<Order> orderHistory;

    /**
     * Creates new customer
     */
    public Customer(int customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.orderHistory = new ArrayList<>();
    }

    /**
     * Adds order to customer's history
     */
    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    /**
     * Gets customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets customer name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets customer phone number
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Gets customer's order history
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
    
    /**
     * Displays order history for a specific customer
     * @param customerName Name of the customer to search for
     * @param orderList List of all orders in the system
     */
    public static void viewCustomerOrders(String customerName, ArrayList<Order> orderList) {
        System.out.println("\n-----Order History for " + customerName + "-----");
       
        boolean found = false;
        for (Order order : orderList) {
            if (order.getCustomer().getName().equalsIgnoreCase(customerName)) {
                order.displayReceipt();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Order not found.");
        }
    }
    
    
  
}