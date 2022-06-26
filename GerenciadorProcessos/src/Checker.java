import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Checker extends Thread{

	private List<Processo> terminated;
	private final int SLICETIME = 2;
	private Thread current;
	
	public Checker(List<Processo> ready, List<Processo> waiting, List<Processo> running, List<Processo> terminated) {
		this.terminated = terminated;
	}
	
	@Override
	public void run() {
		dispath();
		while(!Master.getReady().isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					current.interrupt();
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
		Master.getRunning().remove(0);
		p.setStatus(Estado.READY);
		p.setProcessTime(0);
		current.interrupt();
	}
	
	public synchronized void dispath() {
		Collections.sort(Master.getReady());
		Processo process = Master.getReady().get(0);
		process.setStatus(Estado.RUNNING);
		Master.getRunning().add(process);
		Master.getReady().remove(0);
		current = new Thread(process);
		current.start();
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
