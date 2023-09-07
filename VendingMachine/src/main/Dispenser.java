package main;

import java.util.ArrayList;
import java.util.List;

import stock.Item;
import stock.ItemStock;

public class Dispenser {

	private final List<Item> items = new ArrayList<>();
	private final ItemStock itemStock;
	private Item selectedItem;
	
	public Dispenser(ItemStock itemStock) {
		this.itemStock = itemStock;
	}

	public void dispense(Item item) {
		itemStock.remove(1, item);
		items.add(item);
	}

	public boolean contains(Item item) {
		return items.contains(item);
	}

	
}
