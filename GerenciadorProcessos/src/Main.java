import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class Main {

	private static int idGenerator = 1;
	
	public static void main(String[] args) {
		
		Vector<Processo> stack = new Stack<Processo>();
		
		Processo p = new Processo(idGenerator, Estado.NEW, "TEXTO", prioridadeRandom(), timeRandom());
		stack.add(p);
		
		while(!stack.isEmpty()) {
			
		}
		
	}
	
	public static int timeRandom() {
		int random = new Random().nextInt(10);
		return random;
	}
	
	public static int prioridadeRandom() {
		int random = new Random().nextInt(50);
		return random;
	}
	
}
