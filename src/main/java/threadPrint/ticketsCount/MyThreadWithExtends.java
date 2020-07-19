package threadPrint.ticketsCount;

/**
 * 当前形式，因为单继承的原则，无法再继承别的类，
 * 而且还有1个很神奇的现象，tickets 不是线程共享的，每个线程都能执行10次 tickets--
 */
public class MyThreadWithExtends extends Thread {  
	  
    private int tickets = 10;  
  
    @Override  
    public void run() {  
  
        for (int i = 0; i <= 100; i++) {  
            if(tickets>0){  
                System.out.println(Thread.currentThread().getName()+"--剩余票数" + tickets--);  
            }  
        }  
    }  
      
      
    public static void main(String[] args) {  
        MyThreadWithExtends thread1 = new MyThreadWithExtends();  
        MyThreadWithExtends thread2 = new MyThreadWithExtends();  
        MyThreadWithExtends thread3 = new MyThreadWithExtends();  
  
        thread1.start();  
        thread2.start();  
        thread3.start();  
    }  
  
}  
