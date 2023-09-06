package stock;

public class CoinStock {
	
	private final StockHelper helper;
	
	public CoinStock() {
		helper = new StockHelper(ValidCoin.values());
	}	

	public boolean has(ValidCoin coin) {
		return helper.has(coin);
	}
	
	public void add(ValidCoin coin, int amount) {
		helper.add(coin, amount);
	}
	
	public void remove(ValidCoin coin, int amount) {
		helper.remove(coin, amount);
	}
}
