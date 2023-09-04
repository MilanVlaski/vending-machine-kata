package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Item;
import main.Stock;
import main.ValidCoin;
import main.VendingMachine;

class TestVendingMachine {

	VendingMachine vendingMachine;

	@BeforeEach
	void setup() {
		vendingMachine = new VendingMachine();
	}
	
	@Test
	void shouldSayInsertCoinIfEmpty() {
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
	}
	
	@Test
	void shouldAcceptQuarter() {
		vendingMachine.insert("quarter");
		assertEquals(0.25, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.displayMessage().contains("0.25"));
	}
	
	@Test
	void shouldAcceptDime() {
		vendingMachine.insert("dime");
		assertEquals(0.1, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.displayMessage().contains("0.10"));
	}
	
	@Test
	void shouldRejectPenny() {
		vendingMachine.insert("penny");
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
		assertTrue(vendingMachine.coinReturnContains("penny"));
	}
	
	@Test
	void shouldDispenseColaIfEnoughMoneyInserted() {
		vendingMachine.selectItem(Item.COLA);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenserContains(Item.COLA));
	}
	
	@Test
	void shouldSayThankYouThenInsertCoinIfDispensed() {
		vendingMachine.selectItem(Item.CHIPS);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
		assertTrue(vendingMachine.displayMessage().contains("THANK YOU"));
	}
	
	@Test
	void shouldDisplayPriceOfItemIfNotEnoughMoney() {
		vendingMachine.selectItem(Item.COLA);
		vendingMachine.insert("quarter");
		assertTrue(vendingMachine.displayMessage().contains("PRICE = 1.00"));
	}
	
	@Test
	void shouldDispenseIfYouSelectItemAfterInsertingEnoughCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem(Item.CANDY);
		assertTrue(vendingMachine.dispenserContains(Item.CANDY));
	}
	
	@Test
	void shouldReturnChangeAfterPurchase() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem(Item.CANDY);
		assertTrue(vendingMachine.coinReturnContains("dime"));
		assertTrue(vendingMachine.coinReturnContains("quarter"));
	}
	
	@Test
	void shouldReturnInsertedCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.returnCoins();
		assertTrue(vendingMachine.coinReturnContains("quarter"));
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
	}
}
