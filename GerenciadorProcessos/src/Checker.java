import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class Checker implements Runnable{

	private Random random = new Random();
	private static Vector<Processo> running = new Stack<Processo>();
	private static Vector<Processo> waiting = new Stack<Processo>();
	private static Vector<Processo> ready = new Stack<Processo>();
	private static Queue<Processo> news = new PriorityQueue<Processo>();
	
	@Override
	public void run() {
		Processo current = news.poll();
		if(current.getEstado() == Estado.NEW) {
			
		}
	}
	
	private int gerarPossibilidadeBloqueio() {
		
		return 0;
	}

	public static Vector<Processo> getRunning() {
		return running;
	}

	public static void setRunning(Vector<Processo> running) {
		Checker.running = running;
	}

	public static Vector<Processo> getWaiting() {
		return waiting;
	}

	public static void setWaiting(Vector<Processo> waiting) {
		Checker.waiting = waiting;
	}

	public static Vector<Processo> getReady() {
		return ready;
	}

	public static void setReady(Vector<Processo> ready) {
		Checker.ready = ready;
	}

	
	
}
