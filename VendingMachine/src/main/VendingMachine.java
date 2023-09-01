package main;

import java.math.BigDecimal;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.BigDecimalConversion;

public class VendingMachine {
	
	private final Display display;
	private double insertedAmount;
	private String coinReturn;
	private String dispenser;
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
	public String coinReturn() {
		return coinReturn;
	}
	public String dispenser() {
		return dispenser;
	}
	public Item selectedItem() {
		return selectedItem;
	}
	public boolean itemIsSelected() {
		return selectedItem != null;
	}
	public boolean dispenserEmpty() {
		return dispenser == null;
	}
	public Display display() {
		return display;
	}
	
	public void insert(String typeOfCoin) {
		
		double coinValue = ValidCoin.valueOfCoin(typeOfCoin);
		
		if(coinValue != 0)		
			insertedAmount += coinValue;
		else
			coinReturn = typeOfCoin;
				
		dispenseIfPossible();
	}
	
	public void selectItem(String item) {		
		String myItem = item.toUpperCase();
		selectedItem = Item.valueOf(myItem);
		dispenseIfPossible();
	}

	private void dispenseIfPossible() {
		if (itemIsSelected() && insertedAmount >= selectedItem.price) {
			dispenser = selectedItem.toString();
			double change = subtract(insertedAmount, selectedItem.price);
			coinReturn = amountToCoins(change);	
			selectedItem = null;
			insertedAmount = 0;
		}
	}
	
	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}
	
	private static String amountToCoins(double amount) {
		String result = "";
		while(amount > 0) {
			// This coin is never null. But if the machine runs out of coins,
			// we will get a bunch of exceptions. We should deal with them.
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
			result += coin.toString();
			amount = subtract(amount, coin.value);
		}
		return result;
	}




	private enum ValidCoin {

		QUARTER(0.25),
		DIME(0.10),
		NICKEL(0.05);
		
		public double value;
		
		ValidCoin (double value) {
			this.value = value;
		}
	// If the amount can't be properly represented with coins, we can't deal with it
		public static ValidCoin largestCoinWorthLessThan(double amount) {
			for (ValidCoin coin : ValidCoin.values()) {
				if(coin.value <= amount) {
					return coin;
				}
			}
			return null;
		}

		public static double valueOfCoin(String typeOfCoin) {
			
			double result = 0;
			for (ValidCoin c : ValidCoin.values()) {
				if(typeOfCoin.equals(c.toString()))
					result = c.value;
			}
			
			return result;
		}
		
		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	
}
