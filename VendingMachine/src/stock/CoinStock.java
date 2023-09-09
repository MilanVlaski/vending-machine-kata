package stock;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class CoinStock {
	
	private final StockHelper helper;
	private static ValidCoin[] VALID_COINS = ValidCoin.values(); 
	
	public CoinStock() {
		helper = new StockHelper(VALID_COINS);
	}	

	public boolean has(ValidCoin coin) {
		return helper.has(coin);
	}
	
	public void add(int amount, ValidCoin coin) {
		helper.add(coin, amount);
	}
	
	public void remove(int amount, ValidCoin coin) {
		helper.remove(coin, amount);
	}
	
	public boolean isEmpty() {
		int result = 0;
		for (ValidCoin typeOfCoin : VALID_COINS) {
			if(!helper.has(typeOfCoin)) {
				result++;
			}
		}
		return result == VALID_COINS.length;
	}
	
	public boolean canMakeExactChange() {
//		we dont need optionals
//		Stream<ValidCoin> stream = Arrays.stream(VALID_COINS);
//		Optional<Double> maxValue = stream.map((c) -> c.value)
//											.max(Double::compare);
//		Optional<Double> minValue = stream.map((c) -> c.value)
//											.min(Double::compare);
		return false;
	}
	
}
