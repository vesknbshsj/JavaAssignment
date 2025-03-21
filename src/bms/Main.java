package bms;
import java.util.*;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BakeryManagementSystem system = new BakeryManagementSystem();
        ArrayList<Order> orderList = new ArrayList<>();
        int chc;
        
        do {
			System.out.println("\n-----Bakery Management System-----");
			System.out.println("1. Menu");
			System.out.println("2. Place Order");
			System.out.println("3. View Order History");
			System.out.println("4. View Total Sales");
			System.out.println("5. Search Order");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");
			chc = sc.nextInt();
			sc.nextLine(); //Use to clear the newline character after the input
			
			switch(chc) {
				case 1:
						system.startSystem();
						break;
				case 2: 
						Order newOrder = new Order(system);
						newOrder.PlaceOrder();
						orderList.add(newOrder);
						break;
				case 3: 
						if (orderList.isEmpty()) {
							System.out.println("No orders found.");
						} else {
							System.out.println("\n=== Order History ===");
							for (Order currentOrder : orderList) {
								currentOrder.displayReceipt();
							}
						}
						break;
				case 4: 
						if (orderList.isEmpty()) {
							System.out.println("No sales data available.");
						} else {
							double totalSales = 0;
							for (Order currentOrder : orderList) {
								for (BakeryItem item : currentOrder.getOrderlists()) {
									totalSales += item.getPrice() * item.getQuantity();
								}
							}
							System.out.printf("\nTotal Sales: RM%.2f\n", totalSales);
						}
						break;
				case 5:
						System.out.print("Enter Order ID to search: ");
						int searchId = sc.nextInt();
						boolean found = false;
						for (Order order : orderList) {
							if (order.getOrderId() == searchId) {
								System.out.println("\n=== Order Found ===");
								order.displayReceipt();
								found = true;
								break;
							}
						}
						if (!found) {
							System.out.println("Order not found.");
						}
						break;
				case 0:
						System.out.println("Exiting...");
						break;	
				default:
						System.out.println("Invalid choice.");
			}
		} while (chc != 0);
	}
}

