package stock;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.Optional;

public enum ValidCoin {
	QUARTER(0.25),
	DIME(0.10),
	NICKEL(0.05);

	public double value;

	ValidCoin(double value) {
		this.value = value;
	}

	public static ValidCoin largestCoinWorthLessThan(double amount) {
		Optional<ValidCoin> largestCoin = EnumSet.allOf(ValidCoin.class)
					.stream()
					.filter(coin -> coin.value <= amount)
					.max(Comparator.comparingDouble(coin -> coin.value));
		return largestCoin.orElse(null);
	}

	/**
	 * If you put in gibberish, or a coin that is not allowed, this function returns
	 * 0. Otherwise, returns the actual value as specified in the ValidCoin enum.
	 * 
	 * @param string representation of coin
	 * @return
	 */
	public static double valueOfCoin(String typeOfCoin) {

		double result = 0;
		for (ValidCoin c : ValidCoin.values()) {
			if (typeOfCoin.equals(c.toString()))
				result = c.value;
		}

		return result;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}