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
	
	public void add(Item item, int amount) {
		helper.add(item, amount);
	}
	
	public void remove(Item item, int amount) {
		helper.remove(item, amount);
	}
}
