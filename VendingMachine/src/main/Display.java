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
	
	private DisplayState state = DisplayState.IDLE;

	public void state(DisplayState state) {
		this.state = state;
	}

	public String message(double insertedAmount, DisplayState state,
								double selectedItemPrice) {
		String amount = "";
		if(insertedAmount != 0)
			amount = formatDollar(insertedAmount);	
		
		String itemPrice = "";
		if(selectedItemPrice != 0)
			itemPrice = "PRICE = " + formatDollar(selectedItemPrice);

		return String.join("\n", state.message, amount, itemPrice);
	}

	private static String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
