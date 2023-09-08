package stock;

import java.util.HashMap;
import java.util.Map;

public class StockHelper {
	
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
		if(!has(object) || map.get(object) < amount)
			throw new OutOfItem(object.toString());
		 
		add(object, -amount);
	}
	
	public class OutOfItem extends RuntimeException {
		private static final long serialVersionUID = 1L;

		private OutOfItem(String item) {
			super("No " + item + "s left in stock.");
		}
	}
}