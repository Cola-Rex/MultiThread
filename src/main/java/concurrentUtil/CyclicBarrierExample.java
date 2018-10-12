package concurrentUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//构造方法：
//参数parties指让多少个线程或者任务等待至barrier状态；
//参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
public class CyclicBarrierExample {

	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		for (int i = 0; i < N; i++) {
			new Thread(new Writer(barrier)).start();
		}
	}
	
	static class Writer implements Runnable {
		private CyclicBarrier cyclicBarrier;
		
		public Writer(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run() {
			System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
			try {
				Thread.sleep(5000);
				System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("所有线程写入完毕，继续处理其他任务~");
		}
	}
}
