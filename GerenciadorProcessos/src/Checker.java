import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Checker extends Thread {

	private final int SLICETIME = 2;

	public Checker() {
	}

	@Override
	public void run() {
		Master.dispath();
		while (!Master.getReady().isEmpty()) {
			try {
				Thread.sleep(500);
				System.out.println(Master.getReady());
				if (Master.getRunning().get(0) != null) {
					Processo process = Master.getRunning().get(0);
					if (process.getTimeCPU() <= 0 && !Master.getReady().isEmpty()) {
						Master.getRunning().remove(0);
						Master.dispath();
						Master.getThreds().get(process).wait();
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

	private boolean blocker(Processo p) {
		Random r = new Random();
		List<Integer> chances = Arrays.asList(1, 1, 1, 0);
		int i = chances.get(r.nextInt(chances.size()));
		return i == 1;
	}

}
