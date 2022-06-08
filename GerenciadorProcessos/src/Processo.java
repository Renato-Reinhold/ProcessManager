
public class Processo implements Runnable{

	private int id;
	private Estado estado;
	private String info;
	private int prioridade;
	private int tempo_cpu;
	
	public Processo(int id, Estado estado, String info, int prioridade, int tempo_cpu) {
		this.id = id;
		this.estado = estado;
		this.info = info;
		this.prioridade = prioridade;
		this.tempo_cpu = tempo_cpu;
	}

	@Override
	public void run() {
		try {
			if(Estado.READY == estado) {
				Thread.sleep(3000);
			}else if(Estado.RUNNING == estado) {
				Thread.sleep(tempo_cpu);
			}else if(Estado.WAITING == estado) {
				
			}else if(Estado.BLOCKED == estado) {
				
			}else if(Estado.TERMINATED == estado) {
				
			}else if(Estado.NEW == estado) {
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
