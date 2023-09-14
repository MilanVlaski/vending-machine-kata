package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Display;
import core.VendingMachine;
import stock.ValidCoin;

class TestDisplay {

	VendingMachine vendingMachine;
	
	@BeforeEach
	void setup() {
		vendingMachine = new VendingMachine();
	}
	
	@Test
	void shouldSayExactChangeOnlyIfEmpty() {
		assertEquals("EXACT CHANGE ONLY", vendingMachine.updatedDisplay());
	}
	
	@Test
	void shouldSayInsertCoinIfStocked() {
		vendingMachine.coinStock().add(50, ValidCoin.NICKEL);
		vendingMachine.coinStock().add(50, ValidCoin.QUARTER);
		vendingMachine.coinStock().add(50, ValidCoin.DIME);
		assertEquals("INSERT COIN", vendingMachine.updatedDisplay());
	}
}
