package main;

public enum ValidCoin {
	// valid coins must go in descending order, otherwise largestCoin method breaks
	QUARTER(0.25),
	DIME(0.10),
	NICKEL(0.05);
	
	public double value;
	
	ValidCoin (double value) {
		this.value = value;
	}
// If the amount can't be properly represented with coins, we can't deal with it
	public static ValidCoin largestCoinWorthLessThan(double amount) {
		for (ValidCoin coin : ValidCoin.values()) {
			if(coin.value <= amount) {
				return coin;
			}
		}
		return null;
	}

	public static double valueOfCoin(String typeOfCoin) {
		
		double result = 0;
		for (ValidCoin c : ValidCoin.values()) {
			if(typeOfCoin.equals(c.toString()))
				result = c.value;
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}