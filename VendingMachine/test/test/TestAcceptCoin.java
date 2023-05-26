package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.vintage.engine.descriptor.VintageEngineDescriptor;

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
		assertEquals("0.00", vm.getDisplayMessage());
	}
	
	
	@Test
	void shouldReturnRejectedCoin() {
		
	}

}
