import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Checker extends Thread{

	private List<Processo> running;
	private List<Processo> waiting;
	private List<Processo> ready;
	private List<Processo> terminated;
	private final int SLICETIME = 2;
	private Thread current;
	
	public Checker(List<Processo> ready, List<Processo> waiting, List<Processo> running, List<Processo> terminated) {
		this.ready = ready;
		this.waiting = waiting;
		this.running = running;
		this.terminated = terminated;
	}
	
	@Override
	public void run() {
		dispath();
		while(!ready.isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!waiting.isEmpty()) {
				for (int i = 0; i < waiting.size(); i++) {
					Processo p = waiting.get(i);
					if (p != null && terminated.contains(p.getDependent())) {
						waiting.remove(i);
						wakeUp(p);
					}
				}
			}
			if(running.get(0) != null) {
				Processo process = running.get(0);
				System.out.println("processando: " + process);
				if(process.getTimeCPU() <= 0 && !ready.isEmpty()) {
					running.remove(0);
					dispath();
					current.interrupt();
					terminated.add(process);
				}
				if(process.getProcessTime() >= SLICETIME && !ready.isEmpty()) {
					System.out.println("trocar processo");
					timeRunOut(process);
					dispath();
					ready.add(process);
				}
			}
		}
		System.out.println("ready: "+ ready);
	}

	private synchronized void timeRunOut(Processo p) {
		running.remove(0);
		p.setStatus(Estado.READY);
		p.setProcessTime(0);
		current.interrupt();
	}
	
	public synchronized void dispath() {
		Collections.sort(ready);
		System.out.println(running);
		System.out.println(ready);
		Processo process = ready.get(0);
		process.setStatus(Estado.RUNNING);
		running.add(process);
		ready.remove(0);
		current = new Thread(process);
		current.start();
	}
	
	private synchronized void wakeUp(Processo p) {
		ready.add(p);
	}
	
	private boolean blocker(Processo p) {
		Random r = new Random();
		List<Integer> chances = Arrays.asList(1,1,1,0);
		int i = chances.get(r.nextInt(chances.size()));
		return i == 1;
	}
	
	public List<Processo> getRunning() {
		return running;
	}

	public void setRunning(List<Processo> running) {
		this.running = running;
	}

	public List<Processo> getWaiting() {
		return waiting;
	}

	public void setWaiting(List<Processo> waiting) {
		this.waiting = waiting;
	}

	public List<Processo> getReady() {
		return ready;
	}

	public void setReady(List<Processo> ready) {
		this.ready = ready;
	}
	
	

}
