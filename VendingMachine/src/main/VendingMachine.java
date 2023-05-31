package main;

public class VendingMachine {

	private Display display;
	private double insertedAmount;
	private double coinReturn = 0;
	private String dispenser;
	private Item selectedItem;
	
	public VendingMachine() {
		this.display = new Display(this);
	}
	public String displayMessage() {
		display.update();
		return display.getMessage();
	}
	public double getAmount() {
		return insertedAmount;
	}
	public double getCoinReturn() {
		return coinReturn;
	}
	public String getDispenser() {
		return dispenser;
	}
	public Item getSelectedItem() {
		return selectedItem;
	}
	public Display getDisplay() {
		return display;
	}
	
	public void insert(String typeOfCoin) {
		
		double coin = ValidCoin.getCoinValue(typeOfCoin);
		
		//Adds the amount or returns it
		if(coin != 0)
			insertedAmount += coin;
		else
			coinReturn += coin;		
		
		//Displays thank you and dispenses the item and reduces the money
		if(selectedItem != null && insertedAmount >= selectedItem.price) {
			dispenser = selectedItem.toString();
			coinReturn += insertedAmount - selectedItem.price;
			selectedItem = null;
			insertedAmount = 0;//this will have to return change at some point
		}
	}
	public void selectItem(String item) {		
		String myItem = item.toUpperCase();
		selectedItem = Item.valueOf(myItem);
	}
	
	
	
	
	
	
}
