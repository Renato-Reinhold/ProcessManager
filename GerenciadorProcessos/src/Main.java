

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private JPanel pnlGeral;

	//Lists
	private static JList listReady;
	private static JList listRunning;
	private static JList listWaiting;
	
	private JPanel panelReady;
	private JPanel panelRunning;
	private JPanel panelWaiting;
	
	private JLabel titleReady;
	private JLabel titleRunning;
	private JLabel titleWaiting;
	
	private JButton buttonNewProcess;
	private JButton button;
	private JButton btnSair;
	
	List<Processo> processos;
	private List<Processo> news;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static int timeRandom() {
		return new Random().nextInt(10) + 1;
	}	
	
	{
		processos = new Stack<Processo>();
		
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
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		Master master = new Master(processos);
		Thread t = new Thread(master);
		t.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.setMinimumSize(new Dimension(900, 600));
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));

		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 1, 0, 0));

		pnlGeral = new JPanel();
		pnlGeral.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_5.add(pnlGeral);
		GridBagLayout gbl_pnlGeral = new GridBagLayout();
		gbl_pnlGeral.columnWidths = new int[] {310, 310, 310};
		gbl_pnlGeral.rowHeights = new int[] {700, 30};
		gbl_pnlGeral.columnWeights = new double[] { 1.0, 1.0 };
		gbl_pnlGeral.rowWeights = new double[] { 1.0, 1.0 };
		pnlGeral.setLayout(gbl_pnlGeral);
		
		panelReady = new JPanel();
		GridBagConstraints gbc_panelReady = new GridBagConstraints();
		gbc_panelReady.insets = new Insets(0, 0, 5, 5);
		gbc_panelReady.fill = GridBagConstraints.BOTH;
		gbc_panelReady.gridx = 0;
		gbc_panelReady.gridy = 0;
		pnlGeral.add(panelReady, gbc_panelReady);
		GridBagLayout gbl_panelReady = new GridBagLayout();
		gbl_panelReady.columnWidths = new int[]{327, 0};
		gbl_panelReady.rowHeights = new int[] {30, 650, 0};
		gbl_panelReady.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelReady.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelReady.setLayout(gbl_panelReady);
		
		titleReady = new JLabel("Ready");
		titleReady.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		titleReady.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleReady = new GridBagConstraints();
		gbc_titleReady.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleReady.insets = new Insets(0, 0, 5, 0);
		gbc_titleReady.gridx = 0;
		gbc_titleReady.gridy = 0;
		panelReady.add(titleReady, gbc_titleReady);
		
		listReady = new JList(master.getReady().toArray());
		listReady.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_listReady = new GridBagConstraints();
		gbc_listReady.fill = GridBagConstraints.BOTH;
		gbc_listReady.gridx = 0;
		gbc_listReady.gridy = 1;
		panelReady.add(listReady, gbc_listReady);
		
		panelRunning = new JPanel();
		GridBagConstraints gbc_panelRunning = new GridBagConstraints();
		gbc_panelRunning.insets = new Insets(0, 0, 5, 5);
		gbc_panelRunning.fill = GridBagConstraints.BOTH;
		gbc_panelRunning.gridx = 1;
		gbc_panelRunning.gridy = 0;
		pnlGeral.add(panelRunning, gbc_panelRunning);
		GridBagLayout gbl_panelRunning = new GridBagLayout();
		gbl_panelRunning.columnWidths = new int[]{316, 0};
		gbl_panelRunning.rowHeights = new int[] {30, 650, 0};
		gbl_panelRunning.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelRunning.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelRunning.setLayout(gbl_panelRunning);
		
		titleRunning = new JLabel("Runnig");
		titleRunning.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		titleRunning.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleRunning = new GridBagConstraints();
		gbc_titleRunning.insets = new Insets(0, 0, 5, 0);
		gbc_titleRunning.fill = GridBagConstraints.BOTH;
		gbc_titleRunning.gridx = 0;
		gbc_titleRunning.gridy = 0;
		panelRunning.add(titleRunning, gbc_titleRunning);
		
		listRunning = new JList(master.getRunning().toArray());
		listRunning.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_listRunning = new GridBagConstraints();
		gbc_listRunning.fill = GridBagConstraints.BOTH;
		gbc_listRunning.gridx = 0;
		gbc_listRunning.gridy = 1;
		panelRunning.add(listRunning, gbc_listRunning);
		
		panelWaiting = new JPanel();
		GridBagConstraints gbc_panelWaiting = new GridBagConstraints();
		gbc_panelWaiting.insets = new Insets(0, 0, 5, 0);
		gbc_panelWaiting.fill = GridBagConstraints.BOTH;
		gbc_panelWaiting.gridx = 2;
		gbc_panelWaiting.gridy = 0;
		pnlGeral.add(panelWaiting, gbc_panelWaiting);
		GridBagLayout gbl_panelWaiting = new GridBagLayout();
		gbl_panelWaiting.columnWidths = new int[]{310, 0};
		gbl_panelWaiting.rowHeights = new int[] {30, 650, 0};
		gbl_panelWaiting.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelWaiting.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelWaiting.setLayout(gbl_panelWaiting);
		
		titleWaiting = new JLabel("Waiting");
		titleWaiting.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
		titleWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleWaiting = new GridBagConstraints();
		gbc_titleWaiting.insets = new Insets(0, 0, 5, 0);
		gbc_titleWaiting.fill = GridBagConstraints.BOTH;
		gbc_titleWaiting.gridx = 0;
		gbc_titleWaiting.gridy = 0;
		panelWaiting.add(titleWaiting, gbc_titleWaiting);
		
		listWaiting = new JList(master.getWaiting().toArray());
		listWaiting.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_listWaiting = new GridBagConstraints();
		gbc_listWaiting.fill = GridBagConstraints.BOTH;
		gbc_listWaiting.gridx = 0;
		gbc_listWaiting.gridy = 1;
		panelWaiting.add(listWaiting, gbc_listWaiting);
		
		buttonNewProcess = new JButton("Cadastrar Processo");
		GridBagConstraints gbc_buttonNewProcess = new GridBagConstraints();
		gbc_buttonNewProcess.fill = GridBagConstraints.BOTH;
		gbc_buttonNewProcess.insets = new Insets(0, 0, 0, 5);
		gbc_buttonNewProcess.gridx = 0;
		gbc_buttonNewProcess.gridy = 1;
		pnlGeral.add(buttonNewProcess, gbc_buttonNewProcess);
		
		button = new JButton("Cadastrar Processo");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		pnlGeral.add(button, gbc_button);
		
		btnSair = new JButton("Sair");
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.fill = GridBagConstraints.BOTH;
		gbc_btnSair.gridx = 2;
		gbc_btnSair.gridy = 1;
		pnlGeral.add(btnSair, gbc_btnSair);
		
		UpdateList up = new UpdateList(listReady, listRunning, listWaiting);
		new Thread(up).start();
	}

}
