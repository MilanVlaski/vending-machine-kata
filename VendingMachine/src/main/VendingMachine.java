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
			
			coinReturn = getCoinsForChange(insertedAmount - selectedItem.price);
			
			selectedItem = null;
			insertedAmount = 0;
		}
	}
	
	
	
	
	private String getCoinsForChange(double change) {
		if(change >= ValidCoin.DIME.value ) {
			return "dime";
		}
		return "";
	}




	private enum ValidCoin {

		QUARTER(0.25),
		DIME(0.10),
		NICKEL(0.05);
		
		public double value;
		
		ValidCoin (double value) {
			this.value = value;
		}
		
		public static double valueOfCoin(String typeOfCoin) {
			
			String type = typeOfCoin.toUpperCase();
			double result = 0;
			for (ValidCoin c : ValidCoin.values()) {
				if(type.equals(c.toString()))
					result = c.value;
			}
			
			return result;
		}
	}
	
}
