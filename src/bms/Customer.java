package bms;

public class Customer {
	private String name;
    private String contactNumber;
    private int customerId;
    private static int nextId = 1;

    public Customer() {
        this.customerId = nextId++;
    }

    public Customer(String name, String contactNumber) {
        this.customerId = nextId++;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return contactNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void displayCustomer() {
        System.out.println("\nCustomer Information:");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone Number: " + contactNumber);
    }
}
