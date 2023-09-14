package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.VendingMachine;
import stock.Item;
import stock.ValidCoin;

class TestVendingMachine {

	VendingMachine vendingMachine;

	@BeforeEach
	void setup() {
		vendingMachine = new VendingMachine();
		vendingMachine.itemStock()
				.add(1, Item.COLA)
				.add(1, Item.CANDY)
				.add(1, Item.CHIPS);
		vendingMachine.coinStock().add(10, ValidCoin.DIME);
		vendingMachine.coinStock().add(10, ValidCoin.NICKEL);
		vendingMachine.coinStock().add(10, ValidCoin.QUARTER);
	}

	@Test
	void shouldSayInsertCoinIfEmpty() {
		assertTrue(vendingMachine.updatedDisplay().contains("INSERT COIN"));
		assertFalse(vendingMachine.updatedDisplay().contains("0.00"));
	}

	@Test
	void shouldAcceptQuarter() {
		vendingMachine.insert("quarter");
		assertEquals(0.25, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.updatedDisplay().contains("INSERT COIN"));
		assertTrue(vendingMachine.updatedDisplay().contains("0.25"));
	}

	@Test
	void shouldAcceptDime() {
		vendingMachine.insert("dime");
		assertEquals(0.1, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.updatedDisplay().contains("0.10"));
	}

	@Test
	void shouldRejectPenny() {
		vendingMachine.insert("penny");
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.updatedDisplay().contains("INSERT COIN"));
		assertTrue(vendingMachine.isReturned("penny"));
	}

	@Test
	void shouldDispenseChipsIfEnoughMoneyInserted() {
		vendingMachine.select(Item.CHIPS);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
	}

	@Test
	void shouldSayThankYouThenInsertCoinIfDispensed() {
		vendingMachine.select(Item.CHIPS);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		
		assertTrue(vendingMachine.updatedDisplay().contains("THANK YOU"));
		assertTrue(vendingMachine.updatedDisplay().contains("INSERT COIN"));
	}

	@Test
	void shouldDisplayPriceOfSelectedItem() {
		vendingMachine.select(Item.COLA);
		assertTrue(vendingMachine.updatedDisplay().contains("PRICE = 1.00"));
		assertFalse(vendingMachine.updatedDisplay().contains("0.00"));
	}

	@Test
	void shouldDispenseIfYouSelectItemAfterInsertingEnoughCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.select(Item.CHIPS);
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
	}

	@Test
	void shouldReturnInsertedCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.giveBackCoins();
		assertTrue(vendingMachine.isReturned("quarter"));
		assertEquals(0, vendingMachine.insertedAmount());
	}

	@Test
	void shouldDisplaySoldOut() {
		vendingMachine = new VendingMachine();
		vendingMachine.select(Item.CANDY);
		assertTrue(vendingMachine.updatedDisplay().contains("SOLD OUT"));
	}
}
