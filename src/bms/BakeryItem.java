package bms;

/**
 * Represents a single bakery product/item
 */
public class BakeryItem {
    private String itemName;
    private double price;
    private int quantity;

    
    //Default constructor
     
    public BakeryItem() {}

    /**
     * Creates new bakery item
     */
    public BakeryItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Sets item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets item price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets item quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets item price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets item quantity
     */
    public int getQuantity() {
        return quantity;
    }
}