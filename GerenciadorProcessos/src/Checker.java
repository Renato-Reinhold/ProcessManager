import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Checker extends Thread{

	private List<Processo> terminated;
	private final int SLICETIME = 2;
	private Map<Processo ,Thread> threds = new HashMap<Processo, Thread>();
	
	public Checker(List<Processo> ready, List<Processo> waiting, List<Processo> running, List<Processo> terminated) {
		this.terminated = terminated;
	}
	
	@Override
	public void run() {
		dispath();
		while(!Master.getReady().isEmpty()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			System.out.println(Master.getReady());
			if(!Master.getWaiting().isEmpty()) {
				for (int i = 0; i < Master.getWaiting().size(); i++) {
					Processo p = Master.getWaiting().get(i);
					if (p != null && terminated.contains(p.getDependent())) {
						Master.getWaiting().remove(i);
						wakeUp(p);
					}
				}
			}
			if(Master.getRunning().get(0) != null) {
				Processo process = Master.getRunning().get(0);
				if(process.getTimeCPU() <= 0 && !Master.getReady().isEmpty()) {
					Master.getRunning().remove(0);
					dispath();
					threds.get(process).interrupt();
					terminated.add(process);
				}
				if(process.getProcessTime() >= SLICETIME && !Master.getReady().isEmpty()) {
					timeRunOut(process);
					dispath();
					Master.getReady().add(process);
				}
			}
		}
		
	}

	private synchronized void timeRunOut(Processo p) {
		if(Master.getKernel() == Master.getRunning().size()) {
			Master.getRunning().remove(0);
			p.setStatus(Estado.READY);
		}
		p.setProcessTime(0);
	}
	
	public synchronized void dispath() {
		Collections.sort(Master.getReady());
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < Master.getReady().size(); i++) {
			Processo p = Master.getReady().get(i);
			for (int j = 0; j < p.getProcessPriority(); j++) {
				list.add(i);
			}
		}
		int index = list.get((int) Math.random() * Master.getReady().size());
		Processo process = Master.getReady().get(index);
		process.setStatus(Estado.RUNNING);
		Master.getRunning().add(process);
		Master.getReady().remove(index);
		Thread current = new Thread(process);
		current.start();
		threds.put(process, current);
	}
	
	private synchronized void wakeUp(Processo p) {
		Master.getReady().add(p);
	}
	
	private boolean blocker(Processo p) {
		Random r = new Random();
		List<Integer> chances = Arrays.asList(1,1,1,0);
		int i = chances.get(r.nextInt(chances.size()));
		return i == 1;
	}

}
