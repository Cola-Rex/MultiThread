package concurrentUtil;

import java.util.concurrent.Semaphore;

/*
提供了2个构造器：

public Semaphore(int permits) {          //参数permits表示许可数目，即同时可以允许多少线程进行访问
    sync = new NonfairSync(permits);
}
public Semaphore(int permits, boolean fair) {    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
    sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
}

关键方法：
这4个方法都会被阻塞
public void acquire() throws InterruptedException {  }     //获取一个许可
public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
public void release() { }          //释放一个许可
public void release(int permits) { }    //释放permits个许可
如果需要立即得到执行结果，使用下面方法：
public boolean tryAcquire() { };    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
public boolean tryAcquire(int permits) { }; //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
*/
public class SemaphoreExample {

	public static void main(String[] args) {
		int N = 4; //工人数
		Semaphore semaphore = new Semaphore(2); //机器数目
		for (int i = 0; i < N; i++) {
			new Thread(new Worker(i, semaphore)).start();
		}
	}
	static class Worker implements Runnable {
		private int num;
		private Semaphore semaphore;
		
		public Worker(int num, Semaphore semaphore) {
			System.out.println("构造器");
			this.num = num;
			this.semaphore =semaphore; 
		}
		
		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println("工人" + this.num + "占用一个机器在生产");
				Thread.sleep(2000);
				semaphore.release();
				System.out.println("工人" + this.num + "释放出机器");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
