package bms;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		BakeryManagementSystem bakery = new BakeryManagementSystem();
		StaffManagement staffManager = new StaffManagement();
		ArrayList<Order> orderList = new ArrayList<>();
		CustomerManagement customerManager = new CustomerManagement(staffManager);
		customerManager.setOrderList(orderList);
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n=== Bakery Management System ===");
			System.out.println("1. Staff Portal");
			System.out.println("2. Customer Portal");
			System.out.println("0. Exit");
			
			System.out.print("\nEnter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					staffMenu(bakery, staffManager, orderList);
					break;
					
				case 2:
					customerManager.customerMenu();
					break;
					
				case 0:
					System.out.println("Thank you for using Bakery Management System!");
					break;
					
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);
		
		sc.close();
	}

	private static void staffMenu(BakeryManagementSystem bakery, StaffManagement staffManager, ArrayList<Order> orderList) {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\n=== Staff Portal ===");
			System.out.println("1. Bakery Items Management");
			System.out.println("2. Customer Management");
			System.out.println("3. Place Order");
			System.out.println("4. View Order History");
			System.out.println("5. View Total Sales");
			System.out.println("6. Search Order");
			System.out.println("0. Back to Main Menu");
			
			System.out.print("\nEnter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					bakery.menu();
					break;
					
				case 2:
					staffManager.staffMenu();
					break;
					
				case 3:
					System.out.println("\n=== Place Order ===");
					System.out.print("Enter Customer ID: ");
					int customerId = sc.nextInt();
					Customer customer = staffManager.findCustomer(customerId);
					if (customer == null) {
						System.out.println("Customer not found! Please register the customer first.");
						break;
					}
					Order order = new Order();
					order.PlaceOrder(bakery, customer);
					orderList.add(order);
					break;
					
				case 4:
					System.out.println("\n=== Order History ===");
					if (orderList.isEmpty()) {
						System.out.println("No orders found.");
					} else {
						for (Order o : orderList) {
							o.displayReceipt();
						}
					}
					break;
					
				case 5:
					System.out.println("\n=== Total Sales ===");
					double totalSales = 0;
					for (Order o : orderList) {
						totalSales += o.getTotal();
					}
					System.out.printf("Total Sales: RM%.2f%n", totalSales);
					break;
					
				case 6:
					System.out.println("\n=== Search Order ===");
					System.out.print("Enter Order ID to search: ");
					int searchId = sc.nextInt();
					boolean found = false;
					for (Order o : orderList) {
						if (o.getOrderId() == searchId) {
							o.displayReceipt();
							found = true;
							break;
						}
					}
					if (!found) {
						System.out.println("Order not found.");
					}
					break;
					
				case 0:
					return;
					
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);
	}
}