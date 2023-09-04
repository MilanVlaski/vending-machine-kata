package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Stock;
import main.Stock.OutOfCoin;
import main.ValidCoin;

public class TestStock {

	Stock stock;

	@BeforeEach
	void setup() {
		stock = new Stock();
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
	void shouldRemoveOneQuarter() throws OutOfCoin {
		stock.add(ValidCoin.QUARTER, 1);
		stock.remove(ValidCoin.QUARTER, 1);
		assertFalse(stock.has(ValidCoin.QUARTER));
	}

	@Test
	void shouldThrowOutOfCoin() {
		assertThrows(OutOfCoin.class, () -> stock.remove(ValidCoin.QUARTER, 1));
	}
s}