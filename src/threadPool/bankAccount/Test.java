package bankAccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		Account account = new Account();
		ExecutorService service = Executors.newFixedThreadPool(1000);
		
		for(int i = 1; i <= 1000; i++) {
			service.execute(new AddMoneyThread(account, 1));
		}
		
		service.shutdown();
		
		while(!service.isTerminated()) {}
		
		System.out.println("ÕË»§Óà¶î" + account.getBalance());
	}

}
