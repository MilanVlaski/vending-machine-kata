package main;

public class Display {

	private final VendingMachine vendingMachine;
	private String message;
	private String amount;
	private String itemPrice;
	
	public enum DisplayState {
		READY,
		ITEM_SELECTED,
		PURCHASE_SUCCESSFUL,
		SOLD_OUT;
	}
	
	public Display (VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
		update();
	}
	
	public String message() {
		return message + "\n" + amount + "\n" + itemPrice + "\n";
	}
	
	public void show(String errorMsg) {
		message = errorMsg;
		amount = "";
		itemPrice = "";
	}
	
	public void update() {
		
		if(vendingMachine.insertedAmount() > 0)
			amount = formatDollar(vendingMachine.insertedAmount());
		
		if(vendingMachine.itemIsSelected()) {
			
			if(vendingMachine.isSelectedItemSoldOut()) {
				message = "SOLD OUT";
				return;
			}
			
			itemPrice = "PRICE = " + formatDollar(vendingMachine.selectedItem().price);
		} else {
			itemPrice = "";		
			if(!vendingMachine.dispenserEmpty()) {
				message = "THANK YOU";
			} else {
				message = "INSERT COIN";
			}
		}
		
	}
	
	private String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
