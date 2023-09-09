package main;

import main.Dispenser.DisplayState;

public class Display {

	public static String message(double insertedAmount, DisplayState state,
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
