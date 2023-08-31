package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Item;
import main.VendingMachine;

class TestAcceptCoin {

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
		assertTrue(vendingMachine.coinReturn().contains("penny"));
	}
	
	@Test
	void shouldDispenseColaIfEnoughMoneyInserted() {
		vendingMachine.selectItem("cola");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(0, vendingMachine.insertedAmount());
		assertTrue(vendingMachine.dispenser().contains("COLA"));
	}
	
//	@Test // HIDDEN BECAUSE ValidCoin is private enum 
//	void shouldGetValueOfCoin() {
//		assertEquals(0.25, ValidCoin.valueOfCoin("quarter"));
//	}
//	
//	@Test
//	void shouldGetValueOfFakeCoin() {
//		assertEquals(0.00, ValidCoin.valueOfCoin("fake coin that dont exist"));
//	}
	
	@Test
	void shouldSayThankYouThenInsertCoinIfDispensed() {
		vendingMachine.selectItem("chips");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		assertEquals(Item.CHIPS.toString(), vendingMachine.dispenser());
		assertTrue(vendingMachine.displayMessage().contains("THANK YOU"));
	}
	
	@Test
	void shouldDisplayPriceOfItemIfNotEnoughMoney() {
		vendingMachine.selectItem("cola");
		vendingMachine.insert("quarter");
		assertTrue(vendingMachine.displayMessage().contains("PRICE = 1.00"));
	}
	
	@Test
	void shouldDispenseIfYouSelectItemAfterInsertingEnoughCoins() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem("candy");
		assertEquals("CANDY", vendingMachine.dispenser());
	}
	
	@Test
	void shouldReturnChange() {
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.insert("quarter");
		vendingMachine.selectItem("candy");
		assertTrue(vendingMachine.coinReturn().contains("dime"));
		assertTrue(vendingMachine.coinReturn().contains("quarter"));
	}
}
