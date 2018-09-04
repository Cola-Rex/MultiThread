package threadPool.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job>{

    // �̳߳�ά���������̵߳��������
    private static final int MAX_WORKER_NUMBERS = 10;
    // �̳߳�ά���������̵߳�Ĭ��ֵ
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // �̳߳�ά���������̵߳���С����
    private static final int MIN_WORKER_NUMBERS = 1;
    // ά��һ�������б�,�������ͻ��˷���Ĺ���
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    // �������̵߳��б�
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // �������̵߳�����
    private int workerNum;
    // ÿ���������̱߳������
    private AtomicLong threadNum = new AtomicLong();

 //�����̳߳�
public DefaultThreadPool() {
        this.workerNum = DEFAULT_WORKER_NUMBERS;
        initializeWorkers(this.workerNum);
    }

    public DefaultThreadPool(int num) {
        if (num > MAX_WORKER_NUMBERS) {
            this.workerNum =DEFAULT_WORKER_NUMBERS;
        } else {
            this.workerNum = num;
        }
        initializeWorkers(this.workerNum);
    }
//��ʼ��ÿ���������߳�
private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            //��ӵ��������̵߳��б�
            workers.add(worker);
            //�����������߳�
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

public void execute(Job job) {
        if (job != null) {
        //�����̵߳�"�ȴ�/֪ͨ����"��������jobs����
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }
    //�ر��̳߳ؼ��ر�ÿ���������߳�
     public void shutdown() {
        for (Worker w : workers) {
            w.shutdown();
        }
    }
      //���ӹ������߳�
        public void addWorkers(int num) {
        //��������ֹ���̻߳�ô������ɶ��¸��̼߳������ӵ��¹������̳߳������ֵ
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }
    //���ٹ������߳�
public void removeWorkers(int num) {
        synchronized (jobs) {
        if(num>=this.workerNum){
                  throw new IllegalArgumentException("���������е��߳�����");
                  }
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (worker != null) {
                //�رո��̲߳����б����Ƴ�
                    worker.shutdown();
                    workers.remove(i);
                }
            }
            this.workerNum -= num;
        }
    }

public int getJobSize() {
        // TODO Auto-generated method stub
        return workers.size();
    }
//���幤�����߳�
class Worker implements Runnable {
        // ��ʾ�Ƿ����и�worker
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Job job = null;
                //�̵߳ĵȴ�/֪ͨ����
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();//�̵߳ȴ�����
                        } catch (InterruptedException e) {
                            //��֪���ⲿ�Ը��̵߳��жϲ���������
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // ȡ��һ��job
                    job = jobs.removeFirst();
                }
                //ִ��job
                if (job != null) {
                    job.run();
                }
            }
        }

        // ��ֹ���߳�
        public void shutdown() {
            running = false;
        }
    }

}
