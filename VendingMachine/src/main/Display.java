package main;

public class Display {

	private final VendingMachine vendingMachine;
	private String message;
	private String amount;
	private String itemPrice;
	
	public enum PurchaseState {
		YES,
		SOLD_OUT,
		HAVENT_BEGUN_PURCHASING;
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
		
		switch (vendingMachine.purchaseState) {
		case YES:
			message = "THANK YOU";
			break;
		case SOLD_OUT:
			message = "SOLD OUT";
			break;
		case HAVENT_BEGUN_PURCHASING:
			message = "INSERT COIN";
			break;
		}
		
		if(vendingMachine.itemIsSelected()) {
			itemPrice = "PRICE = " + formatDollar(vendingMachine.selectedItem().price);
		} else {
			itemPrice = "";	
		}
		
	}
	
	private String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
