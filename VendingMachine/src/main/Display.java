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
		
		if(currentState == DisplayState.IDLE && cantMakeChange)
			currentState = DisplayState.EXACT_CHANGE;
			
		String amount = writeInserted(insertedAmount);	
		String priceOfItem = writePrice(selectedItemPrice);
		
		return makeMessageAndResetState(currentState, amount, priceOfItem);
	}
	
	private String makeMessageAndResetState(DisplayState oldState, String amount,
											String priceOfItem){	
		currentState = DisplayState.IDLE;
		return String.join("\n", oldState.message, amount, priceOfItem);
	}

	private String writePrice(double selectedItemPrice) {
		if(selectedItemPrice != 0)
			return "PRICE = " + formatDollar(selectedItemPrice);
		else
			return "";
	}

	private String writeInserted(double insertedAmount) {
		if(insertedAmount != 0)
			return formatDollar(insertedAmount);
		else
			return "";
	}

	private static String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
