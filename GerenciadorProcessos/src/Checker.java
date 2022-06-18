import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Checker extends Thread{

	private Map<Integer, Processo> running;
	private Map<Integer, Processo> waiting;
	private Map<Processo, Integer> ready;
	
	public Checker(Map<Processo, Integer> ready) {
		this.ready = ready;
	}
	
	@Override
	public void run() {
		List<Entry<Processo, Integer>> list = new ArrayList<>(ready.entrySet());
		list.sort(Entry.comparingByValue());
		list.forEach(System.out::println);
		
	}

	public Map<Processo, Integer> getReady() {
		return ready;
	}

	public void setReady(Map<Processo, Integer> ready) {
		this.ready = ready;
	}
	
	

}
