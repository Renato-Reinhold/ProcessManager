import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Checker extends Thread {

	private final int SLICETIME = 3;

	public Checker() {
	}

	@Override
	public void run() {
		Master.dispath();
		while (!Master.getReady().isEmpty()) {
			try {
				Thread.sleep(2000);
				System.out.println(Master.getReady());
				if (Master.getRunning().get(0) != null) {
					Processo process = Master.getRunning().get(0);
					System.out.println(process);
					if (process.getTimeCPU() <= 0 && !Master.getReady().isEmpty()) {
						Master.getRunning().remove(0);
						Master.dispath();
						Master.getThreds().get(process).interrupt();
						Master.getTerminated().add(process);
					}
					if (process.getProcessTime() >= SLICETIME && !Master.getReady().isEmpty()) {
						Master.timeRunOut(process);
						Master.dispath();
						Master.getReady().add(process);
					}
				}
			} catch (InterruptedException e) {
				
			}
		}

	}

	

}
