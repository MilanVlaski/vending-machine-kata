package stock;

public class CoinStock {
	
	private final StockHelper helper;
	
	public CoinStock() {
		helper = new StockHelper(ValidCoin.values());
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
}
