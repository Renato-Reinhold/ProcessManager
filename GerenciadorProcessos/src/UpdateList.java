import javax.swing.JList;

public class UpdateList implements Runnable {

	private JList listReady;
	private JList listRunning;
	private JList listWaiting;

	public UpdateList(JList listReady, JList listRunning, JList listWaiting) {
		this.listReady = listReady;
		this.listRunning = listRunning;
		this.listWaiting = listWaiting;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);

			while (!Master.getReady().isEmpty() || !Master.getRunning().isEmpty() || !Master.getWaiting().isEmpty()) {
				listReady.setListData(Master.getReady().toArray());
				listRunning.setListData(Master.getRunning().toArray());
				listWaiting.setListData(Master.getWaiting().toArray());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
}
