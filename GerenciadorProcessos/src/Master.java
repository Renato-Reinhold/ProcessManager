import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Master implements Runnable{

	private static List<Processo> running = new ArrayList<Processo>();
	private static List<Processo> waiting = new ArrayList<Processo>();
	private static List<Processo> ready = new ArrayList<Processo>();
	private static List<Processo> terminated = new ArrayList<Processo>();
	private static Map<Processo ,Thread> threds = new HashMap<Processo, Thread>();
	private List<Processo> processos;
	private static int kernel = 2;
	
	public Master(List<Processo> processos) {
		this.processos = processos;
	}
	
	public Master(List<Processo> processos, int kernel) {
		this.processos = processos;
		Master.kernel = kernel;
	}
	
	@Override
	public void run() {
		while(!processos.isEmpty()) {
			Processo current = ((Stack<Processo>)processos).pop();
			current.setProcessPriority(priorityRandom());
			if(current.getStatus() == Estado.NEW) {
				ready.add(current);
				current.setStatus(Estado.READY);
			}else {
				waiting.add(current);
			}
		}
		
		Checker checker = new Checker();
		checker.start();
		IOChecker IO = new IOChecker();
		IO.start();
		
	}
	
	public static synchronized void timeRunOut(Processo p) {
		if(getKernel() == running.size()) {
			running.remove(0);
			p.setStatus(Estado.READY);
		}
		p.setProcessTime(0);
	}
	
	public static synchronized void dispath() {
		Collections.sort(ready);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ready.size(); i++) {
			Processo p = ready.get(i);
			for (int j = 0; j < p.getProcessPriority(); j++) {
				list.add(i);
			}
		}
		int index = list.get((int) Math.random() * ready.size());
		Processo process = ready.get(index);
		process.setStatus(Estado.RUNNING);
		running.add(process);
		ready.remove(index);
		Thread current = new Thread(process);
		current.start();
		threds.put(process, current);
	}
	
	public static synchronized void wakeUp(Processo p) {
		p.setStatus(Estado.READY);
		ready.add(p);
		waiting.remove(p);
	}
	
	public static int priorityRandom() {
		return new Random().nextInt(50) + 1;
	}

	public static List<Processo> getRunning() {
		return running;
	}
	
	public static List<Processo> getWaiting() {
		return waiting;
	}

	public static List<Processo> getReady() {
		return ready;
	}
	
	public static List<Processo> getTerminated() {
		return terminated;
	}

	public static int getKernel() {
		return kernel;
	}

	public static Map<Processo, Thread> getThreds() {
		return threds;
	}
}
