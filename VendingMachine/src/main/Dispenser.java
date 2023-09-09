package main;

import java.util.ArrayList;
import java.util.List;

import stock.Item;
import stock.ItemStock;

public class Dispenser {

	public enum DisplayState {
		SUCCESS("THANK YOU"),
		SOLD_OUT("SOLD OUT"),
		IDLE("INSERT COIN");

		public String message;

		DisplayState(String message) {
			this.message = message;
		}
	}

	private final List<Item> dispensedItems = new ArrayList<>();
	private final ItemStock stock;
	private final MoneyHandler moneyHandler;

	private Item selectedItem;
	private DisplayState displayState = DisplayState.IDLE;

	public Dispenser(ItemStock itemStock, MoneyHandler moneyHandler) {
		this.stock = itemStock;
		this.moneyHandler = moneyHandler;
	}

	public void selectAndPurchase(Item item) {
		selectedItem = item;
		tryToPurchase(item);
	}

	public void tryToPurchaseIfSelected() {
		if (itemIsSelected())
			tryToPurchase(selectedItem);
	}

	private void tryToPurchase(Item item) {
		if (stock.has(item)) {
			if (moneyHandler.insertedAmount() >= item.price) {
				moneyHandler.makeChange(item.price);
				selectedItem = null;
				dispense(item);
				displayState = DisplayState.SUCCESS;
			}
		} else {
			selectedItem = null;
			displayState = DisplayState.SOLD_OUT;
		}
	}

	public String message() {
		DisplayState state = this.displayState;
		this.displayState = DisplayState.IDLE;
		return Display.message(moneyHandler.insertedAmount(), state, priceOfSelection());
	}

	public boolean contains(Item item) {
		return dispensedItems.contains(item);
	}

	private void dispense(Item item) {
		stock.remove(1, item);
		dispensedItems.add(item);
	}

	private double priceOfSelection() {
		if (itemIsSelected())
			return selectedItem.price;
		else
			return 0;
	}

	private boolean itemIsSelected() {
		return selectedItem != null;
	}

}
