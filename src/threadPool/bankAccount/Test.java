package bankAccount;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		Account account = new Account();
		ExecutorService service = Executors.newFixedThreadPool(1000);
		
		long start = Calendar.getInstance().getTimeInMillis();
		for(int i = 1; i <= 1000; i++) {
			service.execute(new AddMoneyThread(account, 1));
		}
		
		service.shutdown();
		
		while(!service.isTerminated()) {}
		
		long end = Calendar.getInstance().getTimeInMillis();
		
		System.out.println("账户余额" + account.getBalance() + "，耗时：" + (end - start) + "ms");
	}

}
