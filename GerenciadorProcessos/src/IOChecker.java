public class IOChecker extends Thread{
	
	@Override
	public void run() {
		
		while(!Master.getRunning().isEmpty() 
				|| !Master.getThreds().isEmpty()
				|| !Master.getWaiting().isEmpty()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
			for (int i = 0; i < Master.getRunning().size(); i++) {
				Processo p = Master.getRunning().get(i);
				System.out.println(Master.getThreds().get(p).getState());
				if(!Master.getWaiting().contains(p) && Master.getThreds().get(p).getState() == State.TIMED_WAITING) {
					p.setStatus(Estado.WAITING);
					Master.getWaiting().add(p);
					Master.getRunning().remove(p);
					Master.dispath();
				}
			}
			for (int i = 0; i < Master.getWaiting().size(); i++) {
				Processo p = Master.getWaiting().get(i);
				if (Master.getThreds().get(p) != null && Master.getThreds().get(p).getState() == State.RUNNABLE) {
					Master.wakeUp(p);
				}
			}
		}
		
	}
	
}
