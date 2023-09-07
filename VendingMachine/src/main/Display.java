package main;

public class Display {

	private String message;
	private String amount;
	private String itemPrice;
	
	public enum PurchaseState {
		SUCCESS,
		SOLD_OUT,
		IDLE;
	}
	
	public String message() {
		return String.join("\n", message, amount, itemPrice);
	}
	
	public void update(VendingMachine vendingMachine) {
		
		switch (vendingMachine.purchaseState()) {
		case SUCCESS:
			message = "THANK YOU";
			break;
		case SOLD_OUT:
			message = "SOLD OUT";
			break;
		case IDLE:
			message = "INSERT COIN";
			break;
		}
		
		amount = formatDollar(vendingMachine.insertedAmount());	
		
		if(vendingMachine.itemIsSelected()) {
			itemPrice = "PRICE = " + formatDollar(vendingMachine.selectedItemPrice());
		} else {
			itemPrice = "";	
		}
	}
	
	private String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
