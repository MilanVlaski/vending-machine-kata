package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.CoinStock.OutOfCoins;

public class VendingMachine {
	
	private final Display display;
	private final CoinStock coinStock;
	private final List<String> coinReturn = new ArrayList<>();
	private final List<Item> dispenser = new ArrayList<>();
	private double insertedAmount;
	private Item selectedItem;
	
	public VendingMachine() {
		display = new Display(this);
		coinStock = new CoinStock();
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
	public Display display() {
		return display;
	}
	public boolean coinReturnContains(String coin) {
		return coinReturn.contains(coin);
	}
	
	public void insert(String coin) {
		//repetition!
		double coinValue = ValidCoin.valueOfCoin(coin);
		
		if(coinValue != 0) {
			insertedAmount += coinValue;
			coinStock.add(ValidCoin.valueOf(coin.toUpperCase()), 1);
		}
		else {			
			coinReturn.add(coin);
		}
				
		dispenseIfPossible();
	}
	
	public void selectItem(Item item) {		
		selectedItem = item;
		dispenseIfPossible();
	}

	private void dispenseIfPossible() {
		if (itemIsSelected() && insertedAmount >= selectedItem.price) {
			dispenser.add(selectedItem);
			double change = subtract(insertedAmount, selectedItem.price);
			returnCoins(change);
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
			try {
				coinStock.remove(coin, 1);
				coinReturn.add(coin.toString());
			} catch (OutOfCoins e) {
				e.printStackTrace();
			}
			amount = subtract(amount, coin.value);
		}
	}

	public void returnCoins() {
		returnCoins(insertedAmount);
	}
	public void stock(ValidCoin coin, int amount) {
		coinStock.add(coin, amount);
	}
	
}
