package main;

import java.util.HashMap;

public class Stock {
	
	private final HashMap<ValidCoin, Integer> coinMap = new HashMap<>();
	
	public Stock() {
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
	
	public void remove(ValidCoin coin, int amount) throws OutOfCoin {
		if(!has(coin)) {
			throw new OutOfCoin(coin.toString());
		}
		add(coin, -amount);
	}
	
	
	public class OutOfCoin extends Exception {
		private static final long serialVersionUID = 1L;

		public OutOfCoin(String coin) {
			super("No "+coin+"s left in stock.");
		}
	}
}
