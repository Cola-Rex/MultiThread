package future;

import future.data.Data;
import future.data.FutureData;
import future.data.RealData;

public class Client {

	public Data request(String queryStr) {
		FutureData futureData = new FutureData();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
		}).start();
		return futureData;
	}
}
