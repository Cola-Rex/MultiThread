package future.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class RealData implements Callable<String>{

	private String para;
	
	public RealData(String para) {
		this.para = para;
	}
	
	@Override
	public String call() throws Exception {
		//这里是真实的业务逻辑
		System.out.println("开始执行任务");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "[" + para + "]";
	}
	
	public static void main(String[] args) throws ExecutionException, InterruptedException{
		//传入RealData到FutureTask
		FutureTask<String> futureTask = new FutureTask<>(new RealData("name"));
		//创建一个线程池
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		//在这里开启线程执行 RealData 的 call()方法
		executorService.submit(futureTask);
		Thread.sleep(1000); //证明submit之后，就开始处理任务了
		System.out.println("请求完毕 " + System.currentTimeMillis());
		//...这里进行一些其他操作
		System.out.println("数据" + futureTask.get());
		System.out.println("获取完毕 " + System.currentTimeMillis());
		//启动一个有序的关闭，之前提交的任务将被执行，但是不会接受新的任务
		executorService.shutdown();
	}
}
