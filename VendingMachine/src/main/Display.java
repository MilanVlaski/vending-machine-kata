package main;

public class Display {

	private final VendingMachine vendingMachine;
	private String message = "INSERT COIN";
	private String amount = "0.00 $";
	private String itemPrice = "";
	
	public Display (VendingMachine vm) {
		this.vendingMachine = vm;
	}
	
	public String show() {
		return message + "\n" + amount + "\n" + itemPrice + "\n";
	}
	
	public void update() {
		if(vendingMachine.insertedAmount() > 0)
			amount = formatDollar(vendingMachine.insertedAmount());
		
		if(vendingMachine.itemIsSelected()) {
			itemPrice = "PRICE = " + formatDollar(vendingMachine.selectedItem().price);
		}
		else {
			itemPrice = "";		
			if(!vendingMachine.dispenserEmpty()) {
				message = "THANK YOU";
			} else {
				message = "INSERT COIN";
			}
		}
		
	}
	
	public String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
