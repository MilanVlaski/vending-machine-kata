package main;

import java.util.ArrayList;
import java.util.List;

import main.Display.PurchaseState;
import stock.Item;
import stock.ItemStock;

public class Dispenser {

	private final List<Item> dispensedItems = new ArrayList<>();
	private final ItemStock stock;
	private final MoneyHandler moneyHandler;

	private Item selectedItem;
	// this should actually be a return value of tryToPurchase()
	private PurchaseState purchaseState = PurchaseState.IDLE;

	public Dispenser(ItemStock itemStock, MoneyHandler moneyHandler) {
		this.stock = itemStock;
		this.moneyHandler = moneyHandler;
	}

	public void tryToPurchase() {
		if (stock.has(selectedItem)) {
			if (moneyHandler.insertedAmount() >= priceOfSelection()) {
				purchaseState = PurchaseState.SUCCESS;
				moneyHandler.makeChange(priceOfSelection());
				dispense(selectedItem);
				selectedItem = null;
			}
		} else {
			purchaseState = PurchaseState.SOLD_OUT;
			selectedItem = null;
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

	public void select(Item item) {
		selectedItem = item;
	}

	public boolean itemIsSelected() {
		return selectedItem != null;
	}

	public PurchaseState purchaseState() {
		return purchaseState;
	}

	public void resetPurchaseState() {
		purchaseState = PurchaseState.IDLE;
	}

}
