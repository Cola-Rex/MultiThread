package concurrentUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample2 {

	public static void main(String[] args) {
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(N);
		
		for (int i = 0; i < N; i++) {
			new Thread(new Writer(barrier)).start();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CyclicBarrier重用");
		
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
			System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据~");
			try {
				Thread.sleep(1000);
				System.out.println("线程" + Thread.currentThread().getName()	 + "写入数据完毕，等待其他线程写入完毕");
				
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
