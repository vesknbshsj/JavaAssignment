package bms;

import java.util.ArrayList;
import java.util.Scanner;

public class StaffManagement {
    ArrayList<Customer>customers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addCustomer() {
        System.out.println("\n=== Add New Customer ===");
        scanner.nextLine();
        
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        Customer customer = new Customer(name, phoneNumber);
        customers.add(customer);
        System.out.println("\nCustomer added successfully!");
        customer.displayCustomer();
    }

    public void updateCustomer() {
        System.out.println("\n=== Update Customer ===");
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        
        Customer customer = findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        scanner.nextLine();
        System.out.print("Enter new name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            customer.setName(name);
        }
        
        System.out.print("Enter new phone number (or press Enter to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            customer.setPhoneNumber(phoneNumber);
        }
        
        System.out.println("\nCustomer updated successfully!");
        customer.displayCustomer();
    }

    public void deleteCustomer() {
        System.out.println("\n=== Delete Customer ===");
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        
        Customer customer = findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        customers.remove(customer);
        System.out.println("Customer deleted successfully!");
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("\nNo customers registered yet.");
            return;
        }
        
        System.out.println("\n=== All Customers ===");
        for (Customer customer : customers) {
            customer.displayCustomer();
        }
    }

    public Customer findCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void staffMenu() {
        while (true) {
            System.out.println("\n=== Staff Management ===");
            System.out.println("1. Add New Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. View All Customers");
            System.out.println("0. Back to Main Menu");
            
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    displayAllCustomers();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
