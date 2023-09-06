package stock;

import java.util.HashMap;
import java.util.Map;

public class StockHelper/*<T extends Enum<T>>*/ {
	
	private final Map<Object, Integer> map = new HashMap<>();
	
	public StockHelper(Object[] values) {
		for (Object object : values) {
			map.put(object, 0);
		}
	}
	
	public boolean has(Object object) {
		return map.get(object) > 0;
	}
	
	public void add(Object object, int amount) {
		map.put(object,
				map.get(object) + amount);
	}
	
	public void remove(Object object, int amount) throws OutOfItem{
		if(!has(object))
			throw new OutOfItem(object.toString());
		
		add(object, -amount);
	}
	
	public Integer get(Object object) {
		return map.get(object);
	}
	
	public class OutOfItem extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OutOfItem(String item) {
			super("No " + item + "s left in stock.");
		}
	}
	
	public class InsufficientCoins extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public InsufficientCoins(String coin, int amount) {
			super("I don't have " + amount + " " + coin + "s in stock.");
		}
	}
}