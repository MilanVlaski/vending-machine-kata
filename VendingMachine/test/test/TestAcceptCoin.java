package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.vintage.engine.descriptor.VintageEngineDescriptor;

import main.VendingMachine;
import main.VendingMachine.State;

class TestAcceptCoin {

	VendingMachine vm;
	
	@BeforeEach
	void setup() {
		vm = new VendingMachine();
	}
	
	@Test
	void shouldSBeInReadyStateIfNothingHappened() {
		assertEquals(State.READY, vm.getState());
	}
	
	@Test
	void shouldSayInsertCoinIfEmpty() {
		assertEquals("INSERT COIN", vm.getDisplayMessage());
	}
	
	@Test
	void shouldAcceptQuarter() {
		
	}

	@Test
	void shouldUpdateMoneyAmount() {
		
	}
	
	@Test
	void shouldRejectPenny() {
		
	}
	
	@Test
	void shouldReturnRejectedCoin() {
		
	}

}
