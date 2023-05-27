package main;

public enum Item {
	COLA(1.00, "cola"),
	CHIPS(0.50, "chips"),
	CANDY(0.65, "candy");

	public double price;
	public String name;
	
	Item(double price, String name) {
		this.price = price;
		this.name = name;
	}
}
