package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	
	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;
	private final List<String> coinReturn = new ArrayList<>();
	private final List<Item> dispenser = new ArrayList<>();
	private double insertedAmount;
	private Item selectedItem;
	
	public VendingMachine() {
		display = new Display(this);
		coinStock = new CoinStock();
		itemStock = new ItemStock();
	}
	public String displayMessage() {
		display.update();
		return display.show();
	}
	public double insertedAmount() {
		return insertedAmount;
	}
	public boolean dispenserContains(Item item) {
		return dispenser.contains(item);
	}
	public Item selectedItem() {
		return selectedItem;
	}
	public boolean itemIsSelected() {
		return selectedItem != null;
	}
	public boolean dispenserEmpty() {
		return dispenser.isEmpty();
	}
	public boolean coinReturnContains(String coin) {
		return coinReturn.contains(coin);
	}
	
	public void insert(String coin) {
		double coinValue = ValidCoin.valueOfCoin(coin);
		
		if(coinValue == 0) {
			coinReturn.add(coin);
		} else {			
			insertedAmount += coinValue;
			coinStock.add(ValidCoin.valueOf(coin.toUpperCase()), 1);
		}
				
		dispenseIfPossible();
	}
	
	public void selectItem(Item item) {		
		selectedItem = item;
		dispenseIfPossible();
	}
	
	public void returnInsertedCoins() {
		returnCoins(insertedAmount);
	}
	
	public void stock(ValidCoin coin, int amount) {
		coinStock.add(coin, amount);
	}
	
	public void stock(Item item, int amount) {
		itemStock.add(item, amount);
	}

	private void dispenseIfPossible() {
		if (itemIsSelected() && insertedAmount >= selectedItem.price) {
			double change = subtract(insertedAmount, selectedItem.price);
			returnCoins(change);
			dispenseItem();
		}
	}
	private void dispenseItem() {
		if(itemStock.has(selectedItem)) {		
			itemStock.remove(selectedItem, 1);
			dispenser.add(selectedItem);
			selectedItem = null;
			insertedAmount = 0;
		}
	}
	
	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}
	
	private void returnCoins(double amount) {
		while(amount > 0) {
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
				if(coinStock.has(coin)) {					
					coinStock.remove(coin, 1);
					coinReturn.add(coin.toString());
				}
			amount = subtract(amount, coin.value);
		}
	}

	
}
