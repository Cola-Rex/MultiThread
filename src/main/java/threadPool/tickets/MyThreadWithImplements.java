package threadPool.tickets;

/**
 * 当前形式下，tickets 对所有线程是共享的，故3个线程一共执行10次 tickets--
 */
public class MyThreadWithImplements implements Runnable {  
	  
    private int tickets = 10;  
  
    public void run() {  
  
        for (int i = 0; i <= 100; i++) {  
            if(tickets>0){  
                System.out.println(Thread.currentThread().getName() + "--剩余票数" + tickets--);  
            }  
        }  
    }  
      
      
    public static void main(String[] args) {  
        MyThreadWithImplements myRunnable = new MyThreadWithImplements();  
        Thread thread1 = new Thread(myRunnable, "线程一");  
        Thread thread2 = new Thread(myRunnable, "线程二");  
        Thread thread3 = new Thread(myRunnable, "线程三");  
  
        thread1.start();  
        thread2.start();  
        thread3.start();  
    }  
  
}  
