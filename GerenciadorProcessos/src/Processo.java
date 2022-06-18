
public class Processo implements Runnable{

	private static int id = 1;
	private Estado estado;
	private String info;
	private int prioridade;
	private int tempo_cpu;
	
	public Processo(Estado estado, String info, int prioridade, int tempo_cpu) {
		this.id++;
		this.estado = estado;
		this.info = info;
		this.prioridade = prioridade;
		this.tempo_cpu = tempo_cpu;
	}

	@Override
	public void run() {
		try {
			while(estado == Estado.READY) {
				Thread.sleep(1);
			}
			if(Estado.RUNNING == estado) {
				Thread.sleep(tempo_cpu);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getTempo_cpu() {
		return tempo_cpu;
	}
	public void setTempo_cpu(int tempo_cpu) {
		this.tempo_cpu = tempo_cpu;
	}
	
	
	
}
