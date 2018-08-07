package star;

/**
 * 验证CPU的指令重排
 */
public class MachineResort {

	static int x = 0, y = 0;
	static int a = 0, b = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		int coun = 0;
		while(true) {
			Thread one = new Thread(new Runnable() {
				public void run() {
					a = 1;
					x = b;
				}
			});
		 
			Thread two = new Thread(new Runnable() {
				public void run() {
					b = 1;
					y = a;
				}
			});
			
			one.start();
			two.start();
			one.join(1);two.join(1);
			System.out.println(coun++ + "数值"+x+y);
			if(x == 0 && y == 0) break;
		}
	}
}
