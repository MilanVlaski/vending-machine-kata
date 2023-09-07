package main;

import main.Display.PurchaseState;
import stock.CoinStock;
import stock.Item;
import stock.ItemStock;

public class VendingMachine {

	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;

	private final CoinHandler coinHandler;
	private final Dispenser dispenser;

	public VendingMachine() {
		display = new Display();
		coinStock = new CoinStock();
		itemStock = new ItemStock();
		coinHandler = new CoinHandler(coinStock);
		dispenser = new Dispenser(itemStock, coinHandler);
	}

	public String displayMessage() {
		display.update(this);
		dispenser.resetPurchaseState();
		return display.message();
	}

	public double insertedAmount() {
		return coinHandler.insertedAmount();
	}

	public void insert(String coin) {
		coinHandler.acceptCoin(coin);
		if (itemIsSelected())
			dispenser.tryToPurchase();
	}

	public void selectItem(Item item) {
		dispenser.select(item);
		dispenser.tryToPurchase();
	}

	public double selectedItemPrice() {
		return dispenser.priceOfSelection();
	}

	public boolean dispenserContains(Item item) {
		return dispenser.contains(item);
	}

	public boolean itemIsSelected() {
		return dispenser.itemIsSelected();
	}

	public boolean isReturned(String coin) {
		return coinHandler.contains(coin);
	}

	public void giveBackCoins() {
		coinHandler.returnInsertedCoins();
	}

	public CoinStock coinStock() {
		return coinStock;
	}

	public ItemStock itemStock() {
		return itemStock;
	}
	
	public PurchaseState purchaseState() {
		return dispenser.purchaseState();
	}
}
