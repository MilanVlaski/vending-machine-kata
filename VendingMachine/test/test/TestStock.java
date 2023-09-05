package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.CoinStock;
import main.StockHelper.OutOfItem;
import main.ValidCoin;

public class TestStock {

	CoinStock stock;

	@BeforeEach
	void setup() {
		stock = new CoinStock();
	}

	@Test
	void shouldInitializeEmptyCoinStock() {
		assertFalse(stock.has(ValidCoin.QUARTER));
		assertFalse(stock.has(ValidCoin.NICKEL));
		assertFalse(stock.has(ValidCoin.DIME));
	}

	@Test
	void shouldAddOneQuarter() {
		stock.add(ValidCoin.QUARTER, 1);
		assertTrue(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldRemoveOneQuarter() {
		stock.add(ValidCoin.QUARTER, 1);
		stock.remove(ValidCoin.QUARTER, 1);
		assertFalse(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldThrowOutOfCoin_IfNoCoins() {
		Throwable outOfCoin = assertThrows(OutOfItem.class,
						() -> stock.remove(ValidCoin.QUARTER, 1));
		
		assertEquals("No quarters left in stock.", outOfCoin.getMessage());
	}
	
//	@Test
//	void shouldThrowInsufficientCoins_IfCantProvideSpecifiedAmount() {
//		stock.add(ValidCoin.QUARTER, 1);
//		Throwable insufficientCoins = assertThrows(InsufficientCoins.class, 
//								() -> stock.remove(ValidCoin.QUARTER, 2));
//		
//		assertEquals("I don't have 2 quarters in stock.", insufficientCoins.getMessage());
//	}
}