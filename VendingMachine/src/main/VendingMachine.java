package main;

public class VendingMachine {

	private State state;
	private String displayMessage;
	
	public State getState() {
		return state;
	}
	
	public VendingMachine() {
		this.state = State.READY;
		this.displayMessage = "INSERT COIN";
	}

	public String getDisplayMessage() {
		return displayMessage;
	}
	
	public enum State{
		READY;
	}
}
