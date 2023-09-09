package main;

public class Display {
	public enum DisplayState {
		SUCCESS("THANK YOU"),
		SOLD_OUT("SOLD OUT"),
		IDLE("INSERT COIN"),
		EXACT_CHANGE("EXACT CHANGE ONLY");

		public String message;

		DisplayState(String message) {
			this.message = message;
		}
	}
	
	private DisplayState currentState = DisplayState.IDLE;

	public void update(DisplayState state) {
		this.currentState = state;
	}

	public String message(double insertedAmount, double selectedItemPrice,
							boolean cantMakeChange) {
		
		if(currentState == DisplayState.IDLE && cantMakeChange) {
			currentState = DisplayState.EXACT_CHANGE;
		}
		DisplayState oldState = currentState;
		update(DisplayState.IDLE);
		
		String amount = amount(insertedAmount);	
		String priceOfItem = priceOfItem(selectedItemPrice);		
		 
		return String.join("\n", oldState.message, amount, priceOfItem);
	}

	private String priceOfItem(double selectedItemPrice) {
		if(selectedItemPrice != 0)
			return "PRICE = " + formatDollar(selectedItemPrice);
		else
			return "";
	}

	private String amount(double insertedAmount) {
		if(insertedAmount != 0)
			return formatDollar(insertedAmount);
		else
			return "";
	}

	private static String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
