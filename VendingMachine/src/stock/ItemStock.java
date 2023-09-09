package stock;

public class ItemStock {

	private final StockHelper helper;
	
	public ItemStock() {
		helper = new StockHelper(Item.values());
	}	

	public boolean has(Item item) {
		return helper.has(item);
	}
	
	public ItemStock add(int amount, Item item) {
		helper.add(amount, item);
		return this;
	}
	
	public void remove(int amount, Item item) {
		helper.remove(amount, item);
	}
}
