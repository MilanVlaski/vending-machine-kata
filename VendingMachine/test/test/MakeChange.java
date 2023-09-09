package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.VendingMachine;
import stock.Item;
import stock.ValidCoin;

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
		vendingMachine.insert("quarter");
		assertEquals(1.00, vendingMachine.insertedAmount());
		vendingMachine.selectItem(Item.CANDY);
		
		assertTrue(vendingMachine.isReturned("dime"));
		assertTrue(vendingMachine.isReturned("quarter"));
		
		assertEquals(0, vendingMachine.insertedAmount());
	}

	@Test
	void shouldDispense_ButNotMakeChangeIfImpossibleTo() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(1.00, vendingMachine.insertedAmount());
		vendingMachine.selectItem(Item.CANDY);
		
		assertTrue(vendingMachine.isReturned("quarter"));
		
		assertEquals(0.00, vendingMachine.insertedAmount());
	}

}
