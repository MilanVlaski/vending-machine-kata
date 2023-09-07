package main;

import main.Display.PurchaseState;
import stock.CoinStock;
import stock.Item;
import stock.ItemStock;
import stock.ValidCoin;

public class VendingMachine {

	private final Display display;
	private final CoinStock coinStock;
	private final ItemStock itemStock;
	
	private final CoinReturn coinReturn;
	private final Dispenser dispenser;

	public PurchaseState purchaseState = PurchaseState.IDLE;

	public PurchaseState purchaseState() {
		return purchaseState;
	}

	public VendingMachine() {
		display = new Display();
		coinStock = new CoinStock();
		itemStock = new ItemStock();
		coinReturn = new CoinReturn(coinStock);
		dispenser = new Dispenser(itemStock, coinReturn);
	}

	public String displayMessage() {
		display.update(this);
		purchaseState = PurchaseState.IDLE;
		return display.message();
	}

	public double insertedAmount() {
		return coinReturn.insertedAmount();
	}

	public Item selectedItem() {
		return dispenser.selectedItem();
	}
	
	public void insert(String coin) {
		double coinValue = ValidCoin.valueOfCoin(coin);

		if (coinValue != 0) {
			ValidCoin validCoin = ValidCoin.valueOf(coin.toUpperCase());
			coinStock.add(1, validCoin);
		} else {			
			coinReturn.returnCoin(coin);
		}
		coinReturn.addToAmount(coinValue);
		
		if (itemIsSelected())
			dispenser.tryToPurchase();
	}

	public void selectItem(Item item) {
		dispenser.select(item);
		dispenser.tryToPurchase();
	}

	private void tryToPurchase() {
		if (!dispenser.isSelectedItemAvailable()) {
			purchaseState = PurchaseState.SOLD_OUT;
			dispenser.deselect();
		} else if (dispenser.enoughMoneyForItem(insertedAmount())) {
			purchase(insertedAmount(), dispenser.priceOfSelection());
		}
	}
	
	public double selectedItemPrice() {
		return dispenser.priceOfSelection();
	}
	
	private void purchase(double insertedAmount, double price) {
		purchaseState = PurchaseState.SUCCESS;
		dispenser.dispenseSelected();
		coinReturn.makeChange(insertedAmount(), price);
	}
	
	public boolean dispenserContains(Item item) {
		return dispenser.contains(item);
	}
	
	public boolean itemIsSelected() {
		return dispenser.itemIsSelected();
	}

	public boolean isReturned(String coin) {
		return coinReturn.contains(coin);
	}

	public void returnCoins() {
		coinReturn.returnInsertedCoins();
	}
	
	public CoinStock coinStock() {
		return coinStock;
	}

	public ItemStock itemStock() {
		return itemStock;
	}
}
