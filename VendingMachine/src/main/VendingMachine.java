package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
	
	private final Display display;
	private final List<String> coinReturn = new ArrayList<>();
	private final List<Item> dispenser = new ArrayList<>();
	private double insertedAmount;
	private Item selectedItem;
	
	public VendingMachine() {
		this.display = new Display(this);
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
		
		double coinValue = ValidCoin.valueOfCoin(coin);
		
		if(coinValue != 0)		
			insertedAmount += coinValue;
		else
			coinReturn.add(coin);
				
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
			coinReturn.addAll(amountToCoins(change));
			selectedItem = null;
			insertedAmount = 0;
		}
	}
	
	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}
	
	private static List<String> amountToCoins(double amount) {
		List<String> result = new ArrayList<>();
		while(amount > 0) {
			// This coin is never null. But if the machine runs out of coins,
			// we will get a bunch of exceptions. We CAN just not return change...
			// but this kind of thing is up to the business
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
			result.add(coin.toString());
			amount = subtract(amount, coin.value);
		}
		return result;
	}

	public void returnCoins() {
		coinReturn.addAll(amountToCoins(insertedAmount));
	}
	
}
