package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stock.CoinStock;
import stock.StockHelper.OutOfItem;
import stock.ValidCoin;

public class TestStock {

	CoinStock stock;

	@BeforeEach
	void setup() {
		stock = new CoinStock();
	}

	@Test
	void shouldBeEmpty() {
		assertTrue(stock.isEmpty());
	}
	
	@Test
	void shouldInitializeEmptyCoinStock() {
		assertFalse(stock.has(ValidCoin.QUARTER));
		assertFalse(stock.has(ValidCoin.NICKEL));
		assertFalse(stock.has(ValidCoin.DIME));
	}

	@Test
	void shouldAddOneQuarter() {
		stock.add(1, ValidCoin.QUARTER);
		assertTrue(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldRemoveOneQuarter() {
		stock.add(1, ValidCoin.QUARTER);
		stock.remove(1, ValidCoin.QUARTER);
		assertFalse(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldThrowOutOfCoin_IfNoCoins() {
		Throwable outOfCoin = assertThrows(OutOfItem.class,
						() -> stock.remove(1, ValidCoin.QUARTER));
		
		assertEquals("No quarters left in stock.", outOfCoin.getMessage());
	}
	
	@Test
	void shouldThrowInsufficientCoins_IfCantProvideSpecifiedAmount() {
		stock.add(1, ValidCoin.QUARTER);
		Throwable insufficientCoins = assertThrows(OutOfItem.class, 
								() -> stock.remove(2, ValidCoin.QUARTER));
		
		assertEquals("No quarters left in stock.", insufficientCoins.getMessage());
	}
	
	@Test
	void shouldBeAbleToMakeExactChange() {
		stock.add(4, ValidCoin.NICKEL);
		assertTrue(stock.canMakeExactChange());
	}
}