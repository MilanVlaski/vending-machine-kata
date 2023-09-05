package main;

import java.util.HashMap;

import main.CoinStock.OutOfCoins;

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
		if (!has(item)) {
			throw new OutOfItem(item.toString());
		}
		add(item, -amount);
	}

	public class OutOfItem extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OutOfItem(String item) {
			super("No " + item + "s left in stock.");
		}
	}
}
