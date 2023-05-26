package main;

public class VendingMachine {

	private String displayMessage;
	private double insertedAmount;
	private String coinReturn = "";
	private String dispenser;
	
	public VendingMachine() {
		this.displayMessage = "INSERT COIN";
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public double getAmount() {
		return insertedAmount;
	}
	public String getCoinReturn() {
		return coinReturn;
	}
	public String getDispenser() {
		return dispenser;
	}
	
	public void insert(String typeOfCoin) {
		
		if(typeOfCoin.equals("quarter"))
			insertedAmount += 0.25;
		else if (typeOfCoin.equals("dime"))
			insertedAmount += 0.1;
		else if (typeOfCoin.equals("nickel"))
			insertedAmount += 0.05;
		else if (typeOfCoin.equals("penny"))
			coinReturn += typeOfCoin;
		
		if(insertedAmount > 0)
			displayMessage = String.format("%.2f", insertedAmount);
	}
	
	public void selectCola() {
		
	}
	
}
