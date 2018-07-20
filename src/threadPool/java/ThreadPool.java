package java;

public interface ThreadPool<Job extends Runnable>{

	//执行一个任务job,这个job必须实现Runnable
	void execute(Job job);
	
	void shutdown();
	
	void addWorkers(int num);
	
	void removeWorkers(int num);
	
	int getJobSize();
}
