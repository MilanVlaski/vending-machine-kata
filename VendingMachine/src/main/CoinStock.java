package main;

import java.util.HashMap;

public class CoinStock {
	
	private final HashMap<ValidCoin, Integer> coinMap = new HashMap<>();
	
	public CoinStock() {
		initMap();
	}

	private void initMap() {
		for (ValidCoin validCoin : ValidCoin.values()) {
			coinMap.put(validCoin, 0);
		}
	}

	public boolean has(ValidCoin coin) {
		return coinMap.get(coin) > 0;
	}
	
	public void add(ValidCoin coin, int amount) {
		coinMap.put(coin,
				coinMap.get(coin) + amount);
	}
	
	public void remove(ValidCoin coin, int amount) throws OutOfCoins {
		if(!has(coin)) {
			throw new OutOfCoins(coin.toString());
		} else if (coinMap.get(coin) < amount) {
			throw new InsufficientCoins(coin.toString(), amount);
		}
		add(coin, -amount);
	}
	
	public class InsufficientCoins extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public InsufficientCoins(String coin, int amount) {
			super("I don't have " + amount + " " + coin + "s in stock.");
		}
	}
	
	public class OutOfCoins extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OutOfCoins(String coin) {
			super("No "+coin+"s left in stock.");
		}
	}
}
