package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.CoinStock;
import main.CoinStock.OutOfCoins;
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
	void shouldRemoveOneQuarter() throws OutOfCoins {
		stock.add(ValidCoin.QUARTER, 1);
		stock.remove(ValidCoin.QUARTER, 1);
		assertFalse(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldThrowOutOfCoin() {
		assertThrows(OutOfCoins.class, () -> stock.remove(ValidCoin.QUARTER, 1));
	}
}