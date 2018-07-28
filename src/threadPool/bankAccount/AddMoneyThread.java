package bankAccount;

public class AddMoneyThread implements Runnable{

	private Account account; //账户
	private double money; //余额
	
	public AddMoneyThread(Account account, double money) {
		this.account = account;
		this.money = money;
	}
	
	public void run() {
		account.deposit(money);
	}
	
}
