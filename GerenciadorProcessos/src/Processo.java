//Renato Muller Reinhold
//Joao Vitor Persuhn
//Monica Luiza

public class Processo implements Runnable, Comparable<Processo> {

	private static int idG = 1;
	private int id;
	private Estado status;
	private String info;
	private int ProcessPriority;
	private int timeCPU;
	private int processTime;
	private Processo dependent;

	public Processo(Estado estado, String info, int timeCPU) {
		this.id = idG++;
		this.status = estado;
		this.info = info;
		this.timeCPU = timeCPU;
	}
	
	public Processo(Estado estado, String info, int timeCPU, Processo dependent) {
		this.id = idG++;
		this.status = estado;
		this.info = info;
		this.timeCPU = timeCPU;
		this.dependent = dependent;
	}

	@Override
	public void run() {
		while (status != Estado.TERMINATED) {
			try {
			if (Estado.RUNNING == status) {
				Thread.sleep((long)(Math.random() * 20000));
				timeCPU -= 1;
				this.processTime += 1;
				if (timeCPU == 0) {
					this.status = Estado.TERMINATED;
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
			}
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

	public int getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public Processo getDependent() {
		return dependent;
	}

	public void setDependent(Processo dependent) {
		this.dependent = dependent;
	}

	@Override
	public int compareTo(Processo o) {
		if (this.ProcessPriority > o.getProcessPriority()) {
			return -1;
		} else if (this.ProcessPriority < o.getProcessPriority()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "[id:" + id + ", prioridade: " + ProcessPriority + ", Tempo CPU: " + timeCPU + "] \n";
	}

}
