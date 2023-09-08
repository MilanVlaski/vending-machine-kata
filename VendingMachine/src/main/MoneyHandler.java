package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import stock.CoinStock;
import stock.ValidCoin;

public class MoneyHandler {

	private final CoinStock coinStock;
	private final List<String> returnedCoins = new ArrayList<>();
	private double insertedAmount;

	public MoneyHandler(CoinStock coinStock) {
		this.coinStock = coinStock;
	}

	public void accept(String coin) {
		double coinValue = ValidCoin.valueOfCoin(coin);

		if (coinValue != 0) {
			ValidCoin validCoin = ValidCoin.valueOf(coin.toUpperCase());
			coinStock.add(1, validCoin);
		} else {
			returnCoin(coin);
		}
		insertedAmount += coinValue;
	}

	public void makeChange(double itemPrice) {
		double change = subtract(insertedAmount, itemPrice);
		returnCoins(change);
	}

	private void returnCoins(double amount) {
		while (amount > 0) {
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
			if (coinStock.has(coin)) {
				returnCoin(coin.toString());
				coinStock.remove(1, coin);
			}
			amount = subtract(amount, coin.value);
		}
		insertedAmount = amount;
	}

	public boolean hasReturned(String coin) {
		return returnedCoins.contains(coin);
	}

	private void returnCoin(String coin) {
		returnedCoins.add(coin);
	}

	public double insertedAmount() {
		return insertedAmount;
	}

	public void returnInsertedCoins() {
		returnCoins(insertedAmount);
	}

	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}

}
