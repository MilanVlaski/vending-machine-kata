package main;

import java.util.HashMap;

public class CoinStock {
	
	private final HashMap<ValidCoin, Integer> coinMap = new HashMap<>();
	
	public CoinStock() {
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
		}
		add(coin, -amount);
	}
	
	
	public class OutOfCoins extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OutOfCoins(String coin) {
			super("No "+coin+"s left in stock.");
		}
	}
}
