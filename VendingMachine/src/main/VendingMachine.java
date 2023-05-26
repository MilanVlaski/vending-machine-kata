package main;

public class VendingMachine {

	private String displayMessage;
	private double amount;
	
	public VendingMachine() {
		this.displayMessage = "INSERT COIN";
	}
	public String getDisplayMessage() {
		return displayMessage;
	}
	public double getAmount() {
		return amount;
	}
	
	public void insert(String typeOfCoin) {
		if(typeOfCoin.equals("quarter"))
			amount += 0.25;
		else if (typeOfCoin.equals("dime"))
			amount += 0.1;
		else if (typeOfCoin.equals("nickel"))
			amount += 0.05;
		
		displayMessage = String.format("%.2f", amount);
	}
}
