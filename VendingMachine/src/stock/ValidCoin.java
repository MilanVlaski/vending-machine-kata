package stock;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Stream;

public enum ValidCoin {
	QUARTER(0.25),
	DIME(0.10),
	NICKEL(0.05);

	public double value;

	ValidCoin(double value) {
		this.value = value;
	}

	private static Stream<ValidCoin> enumStream() {
		return EnumSet.allOf(ValidCoin.class).stream();
	}
	
	public static ValidCoin largestCoinWorthLessThan(double amount) {

		return enumStream()
				.filter(coin -> coin.value <= amount)
				.max(Comparator.comparingDouble(coin -> coin.value))
				.orElse(null);
	}

	/**
	 * If you put in gibberish, or a coin that is not allowed, this function returns
	 * 0. Otherwise, returns the actual value as specified in the ValidCoin enum.
	 * 
	 * @param string representation of coin
	 * @return
	 */
	public static Double valueOfCoin(String typeOfCoin) {

		return enumStream().filter(c -> typeOfCoin.equals(c.toString()))
				.map(c -> c.value)
				.findFirst()
				.orElse(0.0);
	}
	// add a test that this returns 4
	public static int minimumSmallestCoinsNecessaryForChange() {
		return (int) Math.floor(
				((ValidCoin.max().value / ValidCoin.min().value) - 1));
	}
	
	private static ValidCoin max() {
		return enumStream()
				.max((c1, c2) -> Double.compare(c1.value, c2.value))
				.orElseThrow();
	}
	
	public static ValidCoin min() {
		return enumStream()
				.min((c1, c2) -> Double.compare(c1.value, c2.value))
				.orElseThrow();
	}
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}