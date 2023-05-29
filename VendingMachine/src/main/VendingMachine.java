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
		
		double coin = ValidCoin.getCoinValue(typeOfCoin);
		
		if(coin != 0)
			insertedAmount += coin;
		else
			coinReturn += typeOfCoin;
		
		if(insertedAmount > 0)
			displayMessage = String.format("%.2f", insertedAmount);
		
		if(selectedItem != null && insertedAmount == selectedItem.price) {
			dispenser = selectedItem.toString();
			displayMessage = "THANK YOU";
		}
	}
	
	public void selectItem(String item) {		
		String myItem = item.toUpperCase();
		selectedItem = Item.valueOf(myItem);
	}
	
	
	
	
	
	
}
