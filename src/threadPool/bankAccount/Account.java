package bankAccount;

public class Account {
	private double balance; //�˻����
	
	/**
	 * ���
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
	 * ��ѯ���
	 */
	public double getBalance() {
		return balance;
	}
}
