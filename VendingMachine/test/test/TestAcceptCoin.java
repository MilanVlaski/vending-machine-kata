package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.vintage.engine.descriptor.VintageEngineDescriptor;

import main.ValidCoin;
import main.Item;
import main.VendingMachine;

class TestAcceptCoin {

	VendingMachine vm;
	
	@BeforeEach
	void setup() {
		vm = new VendingMachine();
	}
	
	@Test
	void shouldSayInsertCoinIfEmpty() {
		assertTrue(vm.displayMessage().contains("INSERT COIN"));
	}
	
	@Test
	void shouldAcceptQuarter() {
		vm.insert("quarter");
		assertEquals(0.25, vm.getAmount());
		assertTrue(vm.displayMessage().contains("0.25"));
	}
	
	@Test
	void shouldAcceptDime() {
		vm.insert("dime");
		assertEquals(0.1, vm.getAmount());
		assertTrue(vm.displayMessage().contains("0.10"));
	}
	
	@Test
	void shouldRejectPenny() {
		vm.insert("penny");
		assertEquals(0, vm.getAmount());
		assertTrue(vm.displayMessage().contains("INSERT COIN"));
		assertEquals(0, vm.getCoinReturn());
	}
	
	@Test
	void shouldDispenseColaIfEnoughMoneyInserted() {
		vm.selectItem("cola");
		vm.insert("quarter");
		vm.insert("quarter");
		vm.insert("quarter");
		vm.insert("quarter");
		assertEquals(0, vm.getAmount());
		assertTrue(vm.getDispenser().contains("COLA"));
	}
	
	@Test
	void shouldAcceptMixedCaseQuarter() {
		vm.insert("qUaRteR");
		assertEquals(0.25, vm.getAmount());
		assertTrue(vm.displayMessage().contains("0.25"));
	}
	
	@Test
	void shouldGetValueOfCoin() {
		assertEquals(0.25, ValidCoin.getCoinValue("quarter"));
	}
	
	@Test
	void shouldGetValueOfFakeCoin() {
		assertEquals(0.00, ValidCoin.getCoinValue("fake coin that dont exist"));
	}
	
	@Test
	void shouldSayThankYouThenInsertCoinIfDispensed() {
		System.out.println("*" + vm.displayMessage() + "*");
		vm.selectItem("chips");
		System.out.println("*" + vm.displayMessage() + "*");
		vm.insert("quarter");
		System.out.println("*" + vm.displayMessage() + "*");
		vm.insert("quarter");
		System.out.println("*" + vm.displayMessage() + "*");
		assertEquals(Item.CHIPS.toString(), vm.getDispenser());
		assertTrue(vm.displayMessage().contains("THANK YOU"));
	}
	
	@Test
	void shouldDisplayPriceOfItemIfNotEnoughMoney() {
		vm.selectItem("cola");
		vm.insert("quarter");
		assertTrue(vm.displayMessage().contains("PRICE = 1.00"));
	}
	
	@Test
	void shouldReturnChange() {
		vm.insert("quarter");
		vm.insert("quarter");
		vm.insert("quarter");
		vm.selectItem("candy");
		assertEquals(0.1, vm.getCoinReturn());
	}
}
