package main;

public class Display {

	private VendingMachine vm;
	private String message = "INSERT COIN";
	private String amount = "0.00 $";
	private String itemPrice = "";
	
	public Display (VendingMachine vm) {
		this.vm = vm;
	}
	
	public String getMessage() {
		return message + "\n" + amount + "\n" + itemPrice + "\n";
	}
	
	public void update() {
		if(vm.getAmount() >= 0)
			amount = formatMoney(vm.getAmount());
		
		if(vm.getSelectedItem() != null)
			itemPrice = "PRICE = " + formatMoney(vm.getSelectedItem().price);
		else
			itemPrice = "";
		
		if(vm.getDispenser() != null) {
			message = "THANK YOU";
		}
		else {
			message = "INSERT COIN";
		}
	}
	
	public String formatMoney(double money) {
		return String.format("%.2f $", money);
	}
}
