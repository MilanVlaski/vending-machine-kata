package main;

import java.util.HashMap;

import main.StockHelper.OutOfItem;

public class ItemStock {

	private final HashMap<Item, Integer> itemMap = new HashMap<>();

	public ItemStock() {
		for (Item item : Item.values()) {
			itemMap.put(item, 0);
		}
	}

	public boolean has(Item item) {
		return itemMap.get(item) > 0;
	}

	public void add(Item coin, int amount) {
		itemMap.put(coin, itemMap.get(coin) + amount);
	}

	public void remove(Item item, int amount) throws OutOfItem {
		add(item, -amount);
	}
}
