package future.data;

public class FutureData implements Data {

	private RealData realData = null;
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData) {
		if (isReady) {
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll(); //通知所有等待在此实例的线程继续执行
	}
	
	@Override
	public synchronized String getResult() {
		while (!isReady) {
			try {
				System.out.println("稍等一下，马上回来");
				wait(); //使当前线程在此处进行等待，直到被通知后继续执行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.result;
	}
}
