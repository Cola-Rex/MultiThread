package threadPool.bankAccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	private Lock accountLock = new ReentrantLock();
	private double balance; //账户余额
	
	/**
	 * 存款
	 */
	public void deposit(double money) {
		accountLock.lock();
		try {
			double newBalance = balance + money;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			balance = newBalance;
		} finally {
			accountLock.unlock();
		}
	}
	
	/**
	 * 获得账户余额
	 */
	public double getBalance() {
		return balance;
	}
}
