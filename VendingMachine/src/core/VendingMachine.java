package core;

import stock.CoinStock;
import stock.Item;
import stock.ItemStock;

public class VendingMachine {

	private final CoinStock coinStock;
	private final ItemStock itemStock;

	private final MoneyHandler moneyHandler;
	private final Dispenser dispenser;

	public VendingMachine() {
		coinStock = new CoinStock();
		itemStock = new ItemStock();
		moneyHandler = new MoneyHandler(coinStock);
		dispenser = new Dispenser(itemStock, moneyHandler);
	}

	public String updatedDisplay() {
		return dispenser.message();
	}

	public void select(Item item) {
		dispenser.selectAndPurchase(item);
	}

	public void insert(String coin) {
		moneyHandler.accept(coin);
		dispenser.tryToPurchaseIfSelected();
	}

	public void giveBackCoins() {
		moneyHandler.returnInsertedCoins();
	}

	// this can maybe get inlined, and tests can only use display
	public double insertedAmount() {
		return moneyHandler.insertedAmount();
	}

	public boolean dispenserContains(Item item) {
		return dispenser.contains(item);
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
}
