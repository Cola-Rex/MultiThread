package threadPrint;

public class ThreadPrintDemo {

	public static void main(String[] args) {
		final ThreadPrintDemo demo = new ThreadPrintDemo();
		Thread t1 = new Thread(demo :: print1);
		Thread t2 = new Thread(demo :: print2);

		t1.start();
		t2.start();
	}
	
	public synchronized void print1() {
		for (int i = 0; i <= 100; i += 2) {
			System.out.println(i);
			this.notify();
			try {
				//i 到 100 时停止程序，否则一直挂起
				if (i < 100) {
					this.wait();
				}
				//Thread.sleep(100); //防止打印速度过快导致混乱
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void print2() {
		for (int i = 1; i <= 100; i += 2) {
			System.out.println(i);
			this.notify();
			try {
				this.wait();
				//Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
