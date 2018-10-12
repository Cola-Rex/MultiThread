package countdownLatch;

import java.util.concurrent.CountDownLatch;

public class Example {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread("泰达米尔") {
			@Override
			public void run() {
				try {
					System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
					Thread.sleep(3000);
					System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
					latch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread("艾希") {
			@Override
			public void run() {
				try {
					System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
					Thread.sleep(3000);
					System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
					latch.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		try {
			System.out.println("等待2个子线程执行完毕~");
			latch.await(); //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
			System.out.println("2个子线程已经搞完");
			System.out.println("继续执行主线程");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
