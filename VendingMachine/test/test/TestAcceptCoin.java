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
		assertEquals("INSERT COIN", vm.getDisplayMessage());
	}
	
	@Test
	void shouldAcceptQuarter() {
		vm.insert("quarter");
		assertEquals(0.25, vm.getAmount());
		assertEquals("0.25", vm.getDisplayMessage());
	}
	
	@Test
	void shouldAcceptDime() {
		vm.insert("dime");
		assertEquals(0.1, vm.getAmount());
		assertEquals("0.10", vm.getDisplayMessage());
	}
	
	@Test
	void shouldAcceptNickel() {
		vm.insert("nickel");
		assertEquals(0.05, vm.getAmount());
		assertEquals("0.05", vm.getDisplayMessage());
	}
	
	@Test
	void shouldRejectPenny() {
		vm.insert("penny");
		assertEquals(0, vm.getAmount());
		assertEquals("INSERT COIN", vm.getDisplayMessage());
		assertEquals("penny", vm.getCoinReturn());
	}
	
	@Test
	void shouldDispenseColaIfEnoughMoneyInserted() {
		vm.selectItem("cola");
		vm.insert("quarter");
		vm.insert("quarter");
		vm.insert("quarter");
		vm.insert("quarter");
		assertEquals(Item.COLA.toString(), vm.getDispenser());
		assertEquals("THANK YOU", vm.getDisplayMessage());
	}
	
	@Test
	void shouldDispenseChipsIfEnoughMoneyInserted() {
		vm.selectItem("chips");
		vm.insert("quarter");
		vm.insert("quarter");
		assertEquals(Item.CHIPS.toString(), vm.getDispenser());
		assertEquals("THANK YOU", vm.getDisplayMessage());
	}
	
	@Test
	void shouldGetItemPrice() {
		assertEquals(Item.COLA.price, 1.00);
	}
	
	@Test
	void shouldAcceptMixedCaseQuarter() {
		vm.insert("qUaRteR");
		assertEquals(0.25, vm.getAmount());
		assertEquals("0.25", vm.getDisplayMessage());
	}
	
	@Test
	void shouldGetValueOfCoin() {
		assertEquals(0.25, ValidCoin.getCoinValue("quarter"));
	}
	
	@Test
	void shouldGetValueOfFakeCoin() {
		assertEquals(0.00, ValidCoin.getCoinValue("fake coin"));
	}
}
