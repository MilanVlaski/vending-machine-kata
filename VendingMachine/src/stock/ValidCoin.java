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
	public static Double valueOfCoin(String typeOfCoin) {

		Optional<Double> value = EnumSet.allOf(ValidCoin.class)
				.stream()
				.filter(c -> typeOfCoin.equals(c.toString()))
				.map(c -> c.value)
				.findFirst();
		
		return value.orElse(0.0);
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}