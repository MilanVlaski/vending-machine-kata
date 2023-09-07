package main;

import java.util.ArrayList;
import java.util.List;

import main.Display.PurchaseState;
import stock.Item;
import stock.ItemStock;

public class Dispenser {

	private final List<Item> dispensedItems = new ArrayList<>();
	private final ItemStock stock;
	private final CoinReturn coinReturn;
	
	private Item selected;
	private PurchaseState purchaseState = PurchaseState.IDLE;
	
	public Dispenser(ItemStock itemStock, CoinReturn coinReturn) {
		this.stock = itemStock;
		this.coinReturn = coinReturn;
	}

	public void dispenseSelected() {
		stock.remove(1, selected);
		dispensedItems.add(selected);
		deselect();
	}

	public boolean contains(Item item) {
		return dispensedItems.contains(item);
	}

	public double priceOfSelection() {
		return selected.price;
	}

	public void select(Item item) {
		selected = item;
	}

	public boolean isSelectedItemAvailable() {
		if(itemIsSelected()) 			
			return stock.has(selected);
		else
			return true;
	}

	public void deselect() {
		selected = null;
	}

	public boolean itemIsSelected() {
		return selected != null;
	}	

	public boolean enoughMoneyForItem(double amount) {
		return amount >= priceOfSelection();
	}
	
	public void tryToPurchase() {
		double insertedAmount = coinReturn.insertedAmount();
		if (!isSelectedItemAvailable()) {
			purchaseState = PurchaseState.SOLD_OUT;
			deselect();
		} else if (enoughMoneyForItem(insertedAmount)) {
			purchase(insertedAmount, priceOfSelection());
		}
	}
	
	private void purchase(double insertedAmount, double price) {
		purchaseState = PurchaseState.SUCCESS;
		dispenseSelected();
		coinReturn.makeChange(insertedAmount, price);
	}

	public PurchaseState purchaseState() {
		return purchaseState;
	}

	public void resetPurchaseState() {
		purchaseState = PurchaseState.IDLE;
	}
}
