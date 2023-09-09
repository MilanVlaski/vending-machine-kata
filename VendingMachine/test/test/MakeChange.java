package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stock.Item;
import stock.ValidCoin;
import vm.VendingMachine;

class MakeChange {

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
	void shouldReturnChangeAfterPurchase() {
		vendingMachine.coinStock().add(1, ValidCoin.DIME);

		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(0.75, vendingMachine.insertedAmount());

		vendingMachine.selectItem(Item.CANDY);
		assertTrue(vendingMachine.isReturned("dime"));
		assertEquals(0, vendingMachine.insertedAmount());
	}

	@Test
	void shouldReturnExtraMoneyfterPurchase() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(0.75, vendingMachine.insertedAmount());

		vendingMachine.selectItem(Item.CHIPS);
		assertTrue(vendingMachine.isReturned("quarter"));
		assertEquals(0, vendingMachine.insertedAmount());
	}

	@Test
	void shouldDisplayExactChangeOnlyIfNoCoinsInStock() {
		assertTrue(vendingMachine.updatedDisplay().contains("EXACT CHANGE ONLY"));
		assertFalse(vendingMachine.updatedDisplay().contains("INSERT COIN"));
	}

	@Test
	void shouldDisplayInsertCoin_IfFourNickelsAreStocked() {
		vendingMachine.coinStock().add(3, ValidCoin.NICKEL);
		assertTrue(vendingMachine.updatedDisplay().contains("EXACT CHANGE ONLY"));
		vendingMachine.coinStock().add(1, ValidCoin.NICKEL);
		assertTrue(vendingMachine.updatedDisplay().contains("INSERT COIN"));
	}

	@Test // if the user doesnt care about change, we let him purchase
	void doesntReturnChange() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem(Item.CANDY);
		assertEquals(0, vendingMachine.insertedAmount());
	}
	
	@Test
	void shouldReturnFour() {
		assertEquals(4, ValidCoin.minimumSmallestCoinsNecessaryForChange());
	}

}
