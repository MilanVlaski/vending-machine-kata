package stock;

public enum Item {
	
	COLA(1.00),
	CHIPS(0.50),
	CANDY(0.65);

	public double price;
	
	Item (double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
