import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main2 {
	
	public static void main(String[] args) {
		
		List<Processo> processos = new Stack<Processo>();
		
		Processo p1 = new Processo(Estado.NEW, "Executara um processo padrao", timeRandom());
		Processo p2 = new Processo(Estado.NEW, "Requisição de um processo de I/O", timeRandom());
		Processo p3 = new Processo(Estado.WAITING, "Aguardo da I/O que sera executado pelo proceso p2", timeRandom(), p2);
		Processo p4 = new Processo(Estado.NEW, "TEXTO 6", timeRandom());
		Processo p5 = new Processo(Estado.NEW, "TEXTO 5", timeRandom());
		Processo p6 = new Processo(Estado.NEW, "Descrição aleatoria", timeRandom());
		Processo p7 = new Processo(Estado.WAITING, "Esperando o processo p6 terminar de executar", timeRandom(), p6);
		Processo p8 = new Processo(Estado.BLOCKED, "Bloqueado pelo processo p7", timeRandom());
		Processo p9 = new Processo(Estado.NEW, "TEXTO 9", timeRandom());
		Processo p10 = new Processo(Estado.NEW, "TEXTO 10", timeRandom());
		processos.add(p1);
		processos.add(p2);
		processos.add(p3);
		processos.add(p4);
		processos.add(p5);
		processos.add(p6);
		processos.add(p7);
		processos.add(p8);
		processos.add(p9);
		processos.add(p10);
		
//		Processo p11 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p12 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p13 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p14 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p15 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p16 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p17 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p18 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p19 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		Processo p20 = new Processo(Estado.NEW, "TEXTO", timeRandom());
//		
//		processos.add(p11);
//		processos.add(p12);
//		processos.add(p13);
//		processos.add(p14);
//		processos.add(p15);
//		processos.add(p16);
//		processos.add(p17);
//		processos.add(p18);
//		processos.add(p19);
//		processos.add(p20);
		
//		System.out.println(Arrays.toString(processos.toArray()));
		
		Master check = new Master(processos);
		check.run();
		
	}
	
	public static int timeRandom() {
		return new Random().nextInt(10) + 1;
	}
	
}
