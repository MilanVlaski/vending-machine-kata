package main;

import main.Display.PurchaseState;
import stock.CoinStock;
import stock.Item;
import stock.ItemStock;
import stock.ValidCoin;

public class VendingMachine {

	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;
	
	private final CoinReturn coinReturn;
	private final Dispenser dispenser;

	private double insertedAmount;
	private Item selectedItem;
	public PurchaseState purchaseState = PurchaseState.IDLE;

	public VendingMachine() {
		display = new Display();
		coinStock = new CoinStock();
		itemStock = new ItemStock();
		coinReturn = new CoinReturn(coinStock);
		dispenser = new Dispenser(itemStock);
	}

	public String displayMessage() {
		display.update(this);
		purchaseState = PurchaseState.IDLE;
		return display.message();
	}

	public double insertedAmount() {
		return insertedAmount;
	}

	public Item selectedItem() {
		return selectedItem;
	}
	
	public void insert(String coin) {
		double coinValue = ValidCoin.valueOfCoin(coin);

		if (coinValue != 0) {
			ValidCoin validCoin = ValidCoin.valueOf(coin.toUpperCase());
			coinStock.add(1, validCoin);
		} else {			
			coinReturn.returnCoin(coin);
		}
		insertedAmount += coinValue;
		
		if (itemIsSelected())
			tryToPurchase();
	}

	public void selectItem(Item item) {
		selectedItem = item;
		tryToPurchase();
	}

	private void tryToPurchase() {
		if (!itemStock.has(selectedItem)) {
			purchaseState = PurchaseState.SOLD_OUT;
			deselectItem();
		} else if (insertedAmount >= selectedItem.price) {
			purchase(insertedAmount, selectedItem);
		}
	}
	
	private void purchase(double insertedAmount, Item selectedItem) {
		purchaseState = PurchaseState.SUCCESS;
		dispenseItem(selectedItem);
		coinReturn.makeChange(insertedAmount, selectedItem.price);
		deselectItem();
		resetInsertedAmount();
	}
	
	public boolean dispenserContains(Item item) {
		return dispenser.contains(item);
	}

	private void deselectItem() {
		selectedItem = null;
	}
	
	private void resetInsertedAmount() {
		insertedAmount = 0;
	}
	
	public boolean itemIsSelected() {
		return selectedItem != null;
	}

	public boolean coinIsReturned(String coin) {
		return coinReturn.contains(coin);
	}

	private void dispenseItem(Item selectedItem) {
		dispenser.dispense(selectedItem);
	}

	public void returnInsertedCoins() {
		coinReturn.returnCoins(insertedAmount);
		resetInsertedAmount(); 
	}
	
	public CoinStock coinStock() {
		return coinStock;
	}

	public ItemStock itemStock() {
		return itemStock;
	}
}
