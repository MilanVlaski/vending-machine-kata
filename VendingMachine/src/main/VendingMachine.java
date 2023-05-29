package main;

public class VendingMachine {

	private double insertedAmount;
	private String displayMessage;
	private String coinReturn = "";
	private String dispenser;
	private Item selectedItem;
	
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
		
		String type = typeOfCoin.toLowerCase();
		
		double coin = ValidCoin.getCoinValue(type);
		
		if(coin != 0)
			insertedAmount += coin;
		else
			coinReturn += type;
		
//		if(type.equals("quarter"))
//			insertedAmount += 0.25;
//		else if (type.equals("dime"))
//			insertedAmount += 0.1;
//		else if (type.equals("nickel"))
//			insertedAmount += 0.05;
//		else
//			coinReturn += type;
		
		if(insertedAmount > 0)
			displayMessage = String.format("%.2f", insertedAmount);
		
		if(selectedItem != null && insertedAmount == selectedItem.price) {
			dispenser = selectedItem.name;
			displayMessage = "THANK YOU";
		}
	}
	
	public void selectCola() {
		selectedItem = Item.COLA;
	}
	
	
	
	
	
	
	
	
	
	
}
