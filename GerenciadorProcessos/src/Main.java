import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) {
		
		List<Processo> processos = new Stack<Processo>();
		
		Processo p1 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p2 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p3 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p4 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p5 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p6 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p7 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p8 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p9 = new Processo(Estado.NEW, "TEXTO", timeRandom());
		Processo p10 = new Processo(Estado.NEW, "TEXTO", timeRandom());
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
		int random = new Random().nextInt(10);
		return random;
	}
	
}
