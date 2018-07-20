package bankAccount;

public class Account {
	private double balance; //’Àªß”‡∂Ó
	
	/**
	 * ¥ÊøÓ
	 */
	public void deposit(double money) {
		double newBalance = balance + money;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		balance = newBalance;
	}
	
	/**
	 * ≤È—Ø”‡∂Ó
	 */
	public double getBalance() {
		return balance;
	}
}
