package main;

public class Display {

	private String message;
	private String amount;
	private String itemPrice;
	
	public String message() {
		return String.join("\n", message, amount, itemPrice);
	}
	
	public void update(double insertedAmount, Dispenser dispenser) {
		message = dispenser.message();
		
		amount = formatDollar(insertedAmount);	
		
		if(dispenser.itemIsSelected()) {
			itemPrice = "PRICE = " + formatDollar(dispenser.priceOfSelection());
		} else {
			itemPrice = "";	
		}
	}
	
	private String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
