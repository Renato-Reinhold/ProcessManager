import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IOChecker extends Thread{
	
	@Override
	public void run() {
		
		while(!Master.getRunning().isEmpty() 
				|| !Master.getThreds().isEmpty()
				|| !Master.getWaiting().isEmpty()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			for (int i = 0; i < Master.getWaiting().size(); i++) {
				Processo p = Master.getWaiting().get(i);
				if (Master.getThreds().get(p) != null && Master.getThreds().get(p).getState() == State.RUNNABLE) {
					Master.wakeUp(p);
				}
			}
			for (int i = 0; i < Master.getRunning().size(); i++) {
				Processo p = Master.getRunning().get(i);
				if(!Master.getWaiting().contains(p) && Master.getThreds().get(p).getState() == State.TIMED_WAITING) {
					p.setStatus(Estado.WAITING);
					Master.getWaiting().add(p);
					Master.getRunning().remove(p);
					Master.dispath();
				}
			}
		}
		
	}
	
	private boolean chancesIO(Processo p) {
		Random r = new Random();
		List<Integer> chances = Arrays.asList(1, 1, 0, 0);
		int i = chances.get(r.nextInt(chances.size()));
		return i == 1;
	}
}
