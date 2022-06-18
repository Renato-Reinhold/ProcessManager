
public class Processo extends Thread{

	private static int idG = 1;
	private int id;
	private Estado status;
	private String info;
	private int ProcessPriority;
	private int timeCPU;
	
	public Processo(Estado estado, String info, int tempo_cpu) {
		this.id = idG++;
		this.status = estado;
		this.info = info;
		this.timeCPU = tempo_cpu;
	}

	@Override
	public void run() {
		try {
			while(status != Estado.TERMINATED) {
				if(status == Estado.READY) {
					//System.out.println("Processo " + getId() + " pronto para execução.");
					Thread.sleep((long) (Math.random() * 1000));
				}
				if(Estado.RUNNING == status) {
					Thread.sleep(timeCPU);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public long getId() {
		return id;
	}
	public Estado getStatus() {
		return status;
	}
	public void setStatus(Estado estado) {
		this.status = estado;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getTimeCPU() {
		return timeCPU;
	}
	public void setTimeCPU(int tempo_cpu) {
		this.timeCPU = tempo_cpu;
	}

	public int getProcessPriority() {
		return ProcessPriority;
	}

	public void setProcessPriority(int processPriority) {
		ProcessPriority = processPriority;
	}
	
}
