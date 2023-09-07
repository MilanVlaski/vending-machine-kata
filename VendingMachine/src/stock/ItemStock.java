package stock;

import java.util.HashMap;

import stock.StockHelper.OutOfItem;

public class ItemStock {

	private final StockHelper helper;
	
	public ItemStock() {
		helper = new StockHelper(Item.values());
	}	

	public boolean has(Item item) {
		return helper.has(item);
	}
	
	public ItemStock add(Item item, int amount) {
		helper.add(item, amount);
		return this;
	}
	
	public void remove(Item item, int amount) {
		helper.remove(item, amount);
	}
}
