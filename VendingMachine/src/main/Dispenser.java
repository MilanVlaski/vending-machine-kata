package main;

import java.util.ArrayList;
import java.util.List;

import stock.Item;
import stock.ItemStock;

public class Dispenser {

	private enum PurchaseState {
		SUCCESS("THANK YOU"),
		SOLD_OUT("SOLD OUT"),
		IDLE("INSERT COIN");
		
		public String message;

		PurchaseState(String message) {
			this.message = message;
		}

	}
	
	private final List<Item> dispensedItems = new ArrayList<>();
	private final ItemStock stock;
	private final MoneyHandler moneyHandler;

	private Item selectedItem;
	private PurchaseState purchaseState = PurchaseState.IDLE;

	public Dispenser(ItemStock itemStock, MoneyHandler moneyHandler) {
		this.stock = itemStock;
		this.moneyHandler = moneyHandler;
	}

	public void selectAndPurchase(Item item) {
		selectedItem = item;
		tryToPurchase(item);
	}

	private void tryToPurchase(Item item) {
		if (stock.has(item)) {
			if (moneyHandler.insertedAmount() >= item.price) {
				moneyHandler.makeChange(item.price);
				selectedItem = null;
				dispense(item);
				purchaseState = PurchaseState.SUCCESS;
			}
		} else {
			selectedItem = null;
			purchaseState = PurchaseState.SOLD_OUT;
		}
	}

	private void dispense(Item item) {
		stock.remove(1, item);
		dispensedItems.add(item);
	}

	public boolean contains(Item item) {
		return dispensedItems.contains(item);
	}

	public double priceOfSelection() {
		return selectedItem.price;
	}

	public boolean itemIsSelected() {
		return selectedItem != null;
	}

	public void tryToPurchaseIfSelected() {
		if (itemIsSelected())
			tryToPurchase(selectedItem);
	}

	public String message() {
		String message = purchaseState.message;
		purchaseState = PurchaseState.IDLE;
		return message;
	}

}
