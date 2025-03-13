package testing;

public class Testing 
{
	String testing;
	int price;
	double number;
	
	public void setTesting(String t, int p, double n)
	{
		testing = t;
		price = p;
		number = n;
	}
	
	public String getTesting() {
		return testing;
	}
	public int getPrice() {
		return price;
	}
	public double getNumber() {
		return number;
	}
	public void displayTesting() {
		System.out.println("Testing is " +testing +" " +price +" " +number);
	}
}
