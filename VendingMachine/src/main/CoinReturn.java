package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import stock.CoinStock;
import stock.ValidCoin;

public class CoinReturn {

	private final CoinStock coinStock;
	private final List<String> coins = new ArrayList<>();
	private double insertedAmount;

	public CoinReturn(CoinStock coinStock) {
		this.coinStock = coinStock;
	}

	public void returnCoins(double amount) {
		while (amount > 0) {
			ValidCoin coin = ValidCoin.largestCoinWorthLessThan(amount);
			if (coinStock.has(coin)) {
				returnCoin(coin.toString());
				coinStock.remove(1, coin);
			}
			amount = subtract(amount, coin.value);
		}
		insertedAmount = 0;
	}
	
	public boolean contains(String coin) {
		return coins.contains(coin);
	}

	public void makeChange(double insertedAmount, double itemPrice) {
		double change = subtract(insertedAmount, itemPrice);
		returnCoins(change);
	}
	
	public void returnCoin(String coin) {
		coins.add(coin);
	}
	
	private static double subtract(double payment, double price) {
		return BigDecimal.valueOf(payment)
				.subtract(BigDecimal.valueOf(price))
				.doubleValue();
	}

	public double insertedAmount() {
		return insertedAmount;
	}
	
	public void addToAmount(double value) {
		insertedAmount += value;
	}

	public void returnInsertedCoins() {
		returnCoins(insertedAmount);
	}

	public void acceptCoin(String coin) {
		double coinValue = ValidCoin.valueOfCoin(coin);

		if (coinValue != 0) {
			ValidCoin validCoin = ValidCoin.valueOf(coin.toUpperCase());
			coinStock.add(1, validCoin);
		} else {
			returnCoin(coin);
		}
		addToAmount(coinValue);
	}
}
