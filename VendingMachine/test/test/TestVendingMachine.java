package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.VendingMachine;
import stock.Item;
import stock.ItemStock;
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
	}

	@Test
	void shouldSayInsertCoinIfEmpty() { assertTrue(vendingMachine.displayMessage().contains("INSERT COIN")); }

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
		assertTrue(vendingMachine.isReturned("penny"));
	}

	@Test
	void shouldDispenseColaIfEnoughMoneyInserted() {
		vendingMachine.selectItem(Item.CHIPS);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
	}

	@Test
	void shouldSayThankYouThenInsertCoinIfDispensed() {
		vendingMachine.selectItem(Item.CHIPS);
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
		assertTrue(vendingMachine.displayMessage().contains("THANK YOU"));
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
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
		vendingMachine.selectItem(Item.CHIPS);
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenserContains(Item.CHIPS));
	}

	@Test // i define change as any money that is above what you gave me
	void shouldReturnChangeAfterPurchase() {
		vendingMachine.coinStock().add(1, ValidCoin.DIME);;
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem(Item.CANDY);

		assertTrue(vendingMachine.isReturned("dime"));
		assertTrue(vendingMachine.isReturned("quarter"));
	}

	@Test
	void shouldDispense_ButNotMakeChangeIfImpossibleTo() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem(Item.CANDY);
		assertTrue(vendingMachine.dispenserContains(Item.CANDY));
		assertTrue(vendingMachine.isReturned("quarter"));
	}

	@Test
	void shouldReturnInsertedCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.returnCoins();
		assertTrue(vendingMachine.isReturned("quarter"));
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
	}

	@Test
	void shouldDisplayOutOfStock() {
		vendingMachine.itemStock().remove(1, Item.CANDY);
		vendingMachine.selectItem(Item.CANDY);
		assertTrue(vendingMachine.displayMessage().contains("SOLD OUT"));
		assertTrue(vendingMachine.displayMessage().contains("INSERT COIN"));
	}
}
