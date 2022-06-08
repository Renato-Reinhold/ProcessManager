import java.util.Stack;
import java.util.Vector;

public class Checker implements Runnable{

	private Vector<Processo> stack = new Stack<Processo>();
	
	@Override
	public void run() {
		
	}

	public Vector<Processo> getStack() {
		return stack;
	}

}
