package vm;

import java.util.ArrayList;
import java.util.List;

import stock.Item;
import stock.ItemStock;
import vm.Display.DisplayState;

public class Dispenser {

	private final List<Item> dispensedItems = new ArrayList<>();
	private final ItemStock stock;
	private final MoneyHandler moneyHandler;
	private final Display display;

	private Item selectedItem;

	public Dispenser(ItemStock itemStock, MoneyHandler moneyHandler, Display display) {
		this.stock = itemStock;
		this.moneyHandler = moneyHandler;
		this.display = display;
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
				display.update(DisplayState.SUCCESS);
			}
		} else {
			selectedItem = null;
			display.update(DisplayState.SOLD_OUT);
		}
	}

	public String message() {
		return display.message(moneyHandler.insertedAmount(),
		 priceOfSelection(), moneyHandler.cantMakeChange());
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
