package main;

import main.Dispenser.DisplayState;

public class Display {

	public static String message(double insertedAmount, DisplayState state,
								double priceOfSelected) {
		String amount = formatDollar(insertedAmount);	
		
		String itemPrice = "";
		if(priceOfSelected != 0)
			itemPrice = "PRICE = " + formatDollar(priceOfSelected);

		return String.join("\n", state.message, amount, itemPrice);
	}

	private static String formatDollar(double money) {
		return String.format("%.2f $", money);
	}
}
