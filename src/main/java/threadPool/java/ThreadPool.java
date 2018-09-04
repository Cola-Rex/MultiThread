package threadPool.java;

public interface ThreadPool<Job extends Runnable>{

	//ִ��һ������job,���job����ʵ��Runnable
	void execute(Job job);
	
	void shutdown();
	
	void addWorkers(int num);
	
	void removeWorkers(int num);
	
	int getJobSize();
}
