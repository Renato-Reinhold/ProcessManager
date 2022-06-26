import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Master implements Runnable{

	private static List<Processo> running = new ArrayList<Processo>();
	private static List<Processo> waiting = new ArrayList<Processo>();
	private static List<Processo> ready = new ArrayList<Processo>();
	private static List<Processo> terminated = new ArrayList<Processo>();
	private List<Processo> processos;
	
	public Master(List<Processo> processos) {
		this.processos = processos;
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
		
		Checker checker = new Checker(ready, waiting, running, terminated);
		checker.start();
		
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

	
	
}
