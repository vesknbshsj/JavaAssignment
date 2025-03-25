package bms;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BakeryManagementSystem system = new BakeryManagementSystem();
        int chc;
        
        do {
            System.out.println("\n-----Bakery Management System-----");
            System.out.println("1. Edit Item");
            System.out.println("2. Place Order");
            System.out.println("3. View Order History");
            System.out.println("4. View Total Sales");
            System.out.println("5. Search Order");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            chc = sc.nextInt();
            sc.nextLine();    
            
            switch(chc) {
                case 1:
                    system.startSystem();  
                    break;
                case 2: 
                    system.placeOrder(); 
                    break;
                case 3: 
                    system.displayOrderHistory();  
                    break;
                case 4: 
                    system.displayTotalSales();  
                    break;
                case 5:
                    System.out.print("Enter Order ID to search: ");		
                    system.searchOrder(sc.nextInt()); 
                    sc.nextLine();
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