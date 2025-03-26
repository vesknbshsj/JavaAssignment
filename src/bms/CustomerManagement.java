package bms;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManagement {
	private StaffManagement staffManager;
	private ArrayList<Order> orderList;
	private Scanner sc;

	public CustomerManagement(StaffManagement staffManager) {
		this.staffManager = staffManager;
		sc = new Scanner(System.in);
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public void viewCustomerInfo() {
		System.out.println("\n=== View Customer Information ===");
		System.out.print("Enter your Customer ID: ");
		int customerId = sc.nextInt();
		
		Customer customer = staffManager.findCustomer(customerId);
		if (customer == null) {
			System.out.println("Customer not found!");
			return;
		}
		customer.displayCustomer();
	}

	public void viewCustomerOrders() {
		System.out.println("\n=== View My Orders ===");
		System.out.print("Enter your Customer ID: ");
		int customerId = sc.nextInt();
		
		Customer customer = staffManager.findCustomer(customerId);
		if (customer == null) {
			System.out.println("Customer not found!");
			return;
		}
		
		boolean hasOrders = false;
		System.out.println("\nOrder history for " + customer.getName() + ":");
		for (Order order : orderList) {
			if (order.getCustomer() != null && order.getCustomer().getCustomerId() == customerId) {
				order.displayReceipt();
				hasOrders = true;
			}
		}
		
		if (!hasOrders) {
			System.out.println("No orders found for this customer.");
		}
	}

	public void customerMenu() {
		while (true) {
			System.out.println("\n=== Customer Portal ===");
			System.out.println("1. View Information");
			System.out.println("2. View Orders");
			System.out.println("0. Back to Main Menu");
			
			System.out.print("\nEnter your choice: ");
			int choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					viewCustomerInfo();
					break;
				case 2:
					viewCustomerOrders();
					break;
				case 0:
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
