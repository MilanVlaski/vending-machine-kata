package main;

import main.Display.PurchaseState;
import stock.CoinStock;
import stock.Item;
import stock.ItemStock;

public class VendingMachine {

	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;

	private final MoneyHandler moneyHandler;
	private final Dispenser dispenser;

	public VendingMachine() {
		display = new Display();
		coinStock = new CoinStock();
		itemStock = new ItemStock();
		moneyHandler = new MoneyHandler(coinStock);
		dispenser = new Dispenser(itemStock, moneyHandler);
	}

	public String displayMessage() {
		display.update(this);
		dispenser.resetPurchaseState();
		return display.message();
	}

	public void selectItem(Item item) {
		dispenser.select(item);
		dispenser.tryToPurchase();
	}

	public void insert(String coin) {
		moneyHandler.accept(coin);
		if (itemIsSelected())
			dispenser.tryToPurchase();
	}

	public void giveBackCoins() {
		moneyHandler.returnInsertedCoins();
	}

	public double insertedAmount() {
		return moneyHandler.insertedAmount();
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
		return moneyHandler.hasReturned(coin);
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
