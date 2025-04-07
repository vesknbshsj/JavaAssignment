package bms;

/**
 * Interface defining the standard inventory operations and constants
 */
public interface InventoryInterface {
    // Constants for initial inventory items
    public static final String[] DEFAULT_ITEMS = {
        "White Bread", "Baguette", "Chocolate Cake", "Vanilla Cake",
        "Cookies", "Croissant", "Danish Pastry", "Muffin"
    };
    
    public static final double[] DEFAULT_PRICES = {
        1.55, 5.00, 7.50, 7.50, 3.00, 2.50, 4.00, 2.00
    };
    
    public static final int[] DEFAULT_QUANTITIES = {
        50, 30, 20, 20, 100, 40, 25, 45
    };
    
    public static final int MAX_QUANTITY = 100; // Modified from 200 to 100 as requested
    
    // Core inventory operations
    public void addItem(String name, double price, int quantity);
    public void updateItem(String name, double price, int quantity);
    public void removeItem(String name);
    public void displayInventory();
    public BakeryItem getItem(String itemName);
}