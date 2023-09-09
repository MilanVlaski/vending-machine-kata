package stock;

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
		helper.add(amount, coin);
	}
	
	public void remove(int amount, ValidCoin coin) {
		helper.remove(amount, coin);
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
		return helper.has(ValidCoin.minimumSmallestCoinsNecessaryForChange(),
				ValidCoin.min());
	}
	
}
