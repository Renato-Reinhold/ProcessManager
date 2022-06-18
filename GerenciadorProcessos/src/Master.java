import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class Master implements Runnable{

	private static Map<Integer, Processo> running = new HashMap<Integer, Processo>();
	private static Map<Integer, Processo> waiting = new HashMap<Integer, Processo>();
	private static Map<Processo, Integer> ready = new HashMap<Processo, Integer>();
	private List<Processo> processos;
	
	public Master(List<Processo> processos) {
		this.processos = processos;
	}
	
	@Override
	public void run() {
		while(!processos.isEmpty()) {
			Processo current = ((Stack<Processo>)processos).pop();
			current.setProcessPriority(prioridadeRandom());
			ready.put(current, current.getProcessPriority());
			current.setStatus(Estado.READY);
			current.start();
		}
		
		Checker checker = new Checker(ready);
		checker.run();
		
		System.out.println(Collections.singletonList(ready));
		System.out.println(ready.size());
		
	}
	
	public static int prioridadeRandom() {
		int random = new Random().nextInt(50);
		return random;
	}
	
	private int gerarPossibilidadeBloqueio() {
		
		return 0;
	}

	public static Map<Integer, Processo> getRunning() {
		return running;
	}
	
	public static Map<Integer, Processo> getWaiting() {
		return waiting;
	}

	public static Map<Processo, Integer> getReady() {
		return ready;
	}

	
	
}
