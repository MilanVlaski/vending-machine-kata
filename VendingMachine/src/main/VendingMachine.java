package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.Display.PurchaseState;
import stock.CoinStock;
import stock.Item;
import stock.ItemStock;
import stock.ValidCoin;

public class VendingMachine {

	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;

	private final List<String> coinReturn = new ArrayList<>();
	private final List<Item> dispenser = new ArrayList<>();

	private double insertedAmount;
	private Item selectedItem;
	public PurchaseState purchaseState = PurchaseState.HAVENT_BEGUN_PURCHASING;

	public VendingMachine() {
		display = new Display(this);
		coinStock = new CoinStock();
		itemStock = new ItemStock();
	}

	public String displayMessage() {
		display.update();
		purchaseState = PurchaseState.HAVENT_BEGUN_PURCHASING;
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
			coinStock.add(validCoin, 1);
		} else {			
			coinReturn.add(coin);
		}
		insertedAmount += coinValue;
		
		if (itemIsSelected())
			tryPurchase();
	}

	public void selectItem(Item item) {
		selectedItem = item;
		tryPurchase();
	}

	private void tryPurchase() {
		if (!itemStock.has(selectedItem)) {
			purchaseState = PurchaseState.SOLD_OUT;
			deselectItem();
		} else if (insertedAmount >= selectedItem.price) {
			makePurchase(insertedAmount, selectedItem);
		}
	}
	
	private void makePurchase(double insertedAmount, Item selectedItem) {
		purchaseState = PurchaseState.YES;
		dispenseItem(selectedItem);
		makeChange(insertedAmount, selectedItem.price);
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

	public boolean coinReturnContains(String coin) {
		return coinReturn.contains(coin);
	}

	private void makeChange(double insertedAmount, double itemPrice) {
		double change = subtract(insertedAmount, itemPrice);
		returnCoins(change);
	}

	private void dispenseItem(Item selectedItem) {
		itemStock.remove(selectedItem, 1);
		dispenser.add(selectedItem);
	}

	public void returnInsertedCoins() {
		returnCoins(insertedAmount);
		resetInsertedAmount();
	}
	

	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}

	private void returnCoins(double amount) {
		while (amount > 0) {
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
			if (coinStock.has(coin)) {
				coinStock.remove(coin, 1);
				coinReturn.add(coin.toString());
			}
			amount = subtract(amount, coin.value);
		}
	}

	public void stock(ValidCoin coin, int amount) {
		coinStock.add(coin, amount);
	}

	public void stock(Item item, int amount) {
		itemStock.add(item, amount);
	}
}
