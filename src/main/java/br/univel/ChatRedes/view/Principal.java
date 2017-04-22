package br.univel.ChatRedes.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import br.univel.ChatRedes.comum.MeuModelo;
import common.Arquivo;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;
import common.Status;
import java.awt.Color;

public class Principal extends JFrame implements InterfaceUsuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private InterfaceServidor conexaoCliente;
	private JTextField field_pesquisa_contato;
	private JTable tableContatos;
	private JTabbedPane tabbedConversas;
	public EntidadeUsuario user;
	
	private static Principal global;
	private JTextField textField;
	private JTable tableContato;

	/**
	 * Create the frame.
	 * 
	 * @param nome
	 * @param string4
	 * @param string3
	 * @param string2
	 * @param string
	 */

	/**
	 * CONSTRUCTOR TESTE
	 */
	public Principal() {
		setBackground(Color.WHITE);
		// this.user = usuario;
		// this.conexaoCliente = con;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		// try {
		// conexaoCliente.conectarChat(user, this);
		// } catch (RemoteException e2) {
		// e2.printStackTrace();
		// }

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TadsZap");
		setBounds(100, 100, 511, 370);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnConexo = new JMenu("Conexão");
		menuBar.add(mnConexo);

		JMenuItem mntmTransissao = new JMenuItem("Transmissão");
		mntmTransissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				enviarTransmissao();

			}
		});
		mnConexo.add(mntmTransissao);

		JMenuItem mntmNewMenuItem = new JMenuItem("Alterar Dados");
		mnConexo.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AlterarDados alterar = new AlterarDados();
				alterar.getFrame().setVisible(true);
			}
		});

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexaoCliente.desconectarChat(user);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnConexo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 324, 0 };
		gbl_contentPane.rowHeights = new int[] { 531, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.WHITE);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		contentPane.add(splitPane, gbc_splitPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 62, 290, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		Icon user_image = new ImageIcon("user.png");
		JLabel user_icon = new JLabel(user_image);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(user_icon, gbc_label);

		final JComboBox<Status> CBStatus = new JComboBox<Status>();
		CBStatus.setForeground(Color.BLACK);
		CBStatus.setBackground(Color.WHITE);
		CBStatus.setModel(new DefaultComboBoxModel<Status>(Status.values()));
		CBStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user.setStatus(Status.valueOf(CBStatus.getSelectedItem().toString()));
					conexaoCliente.atualizarStatus(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		// JLabel lblNomeLogado = new JLabel(user.getNome());
		JLabel lblNomeLogado = new JLabel("jhoestevam");
		lblNomeLogado.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNomeLogado = new GridBagConstraints();
		gbc_lblNomeLogado.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeLogado.gridx = 2;
		gbc_lblNomeLogado.gridy = 0;
		panel.add(lblNomeLogado, gbc_lblNomeLogado);
		GridBagConstraints gbc_CBStatus = new GridBagConstraints();
		gbc_CBStatus.insets = new Insets(0, 0, 5, 5);
		gbc_CBStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_CBStatus.gridx = 3;
		gbc_CBStatus.gridy = 0;
		panel.add(CBStatus, gbc_CBStatus);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		tableContato = new JTable();
		tableContato.setBackground(Color.WHITE);
		GridBagConstraints gbc_tableContato = new GridBagConstraints();
		gbc_tableContato.insets = new Insets(0, 0, 5, 5);
		gbc_tableContato.fill = GridBagConstraints.BOTH;
		gbc_tableContato.gridx = 1;
		gbc_tableContato.gridy = 0;
		panel_1.add(tableContato, gbc_tableContato);
		
		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.gridx = 2;
		gbc_btnPesquisar.gridy = 1;
		panel_1.add(btnPesquisar, gbc_btnPesquisar);

//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBackground(Color.WHITE);
//		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
//		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
//		gbc_tabbedPane.gridx = 0;
//		gbc_tabbedPane.gridy = 0;
//		panel_1.add(tabbedPane, gbc_tabbedPane);
//
//		JPanel panel_2 = new JPanel();
//		panel_2.setBackground(Color.WHITE);
//		tabbedPane.addTab("Contatos", null, panel_2, null);
//		GridBagLayout gbl_panel_2 = new GridBagLayout();
//		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
//		gbl_panel_2.rowHeights = new int[] { 0, 0, 0 };
//		gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
//		gbl_panel_2.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
//		panel_2.setLayout(gbl_panel_2);
//
//		JScrollPane scrollPane_1 = new JScrollPane();
//		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
//		gbc_scrollPane_1.gridwidth = 2;
//		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
//		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
//		gbc_scrollPane_1.gridx = 0;
//		gbc_scrollPane_1.gridy = 0;
//		panel_2.add(scrollPane_1, gbc_scrollPane_1);
//
//		tableContatos = new JTable();
//		tableContatos.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					EntidadeUsuario usuario = new EntidadeUsuario();
//
//					int linha = tableContatos.getSelectedRow();
//
//					usuario.setNome(tableContatos.getValueAt(linha, 0).toString());
//					usuario.setStatus(Status.valueOf(tableContatos.getValueAt(linha, 1).toString()));
//
//					tabbedConversas.add(usuario.getNome(), new Conversa(usuario));
//					
//					int i = tableContatos.getSelectedRow();
//					Chat.getFrame().setVisible(true);
//				}
//			}
//		});
//		scrollPane_1.setViewportView(tableContatos);
//
//		field_pesquisa_contato = new JTextField();
//		GridBagConstraints gbc_field_pesquisa_contato = new GridBagConstraints();
//		gbc_field_pesquisa_contato.insets = new Insets(0, 0, 0, 5);
//		gbc_field_pesquisa_contato.fill = GridBagConstraints.BOTH;
//		gbc_field_pesquisa_contato.gridx = 0;
//		gbc_field_pesquisa_contato.gridy = 1;
//		panel_2.add(field_pesquisa_contato, gbc_field_pesquisa_contato);
//		field_pesquisa_contato.setColumns(10);
//
//		JButton btnPesquisaContato = new JButton("Pesquisar");
//		GridBagConstraints gbc_btnPesquisaContato = new GridBagConstraints();
//		gbc_btnPesquisaContato.gridx = 1;
//		gbc_btnPesquisaContato.gridy = 1;
//		panel_2.add(btnPesquisaContato, gbc_btnPesquisaContato);
//
//		JPanel panel_3 = new JPanel();
//		GridBagLayout gbl_panel_3 = new GridBagLayout();
//		gbl_panel_3.columnWidths = new int[] { 0, 0 };
//		gbl_panel_3.rowHeights = new int[] { 0, 0 };
//		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
//		gbl_panel_3.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
//		panel_3.setLayout(gbl_panel_3);
//
//		tabbedConversas = new JTabbedPane(JTabbedPane.TOP);
//		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
//		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
//		gbc_tabbedPane_1.gridx = 0;
//		gbc_tabbedPane_1.gridy = 0;
//		panel_3.add(tabbedConversas, gbc_tabbedPane_1);

		global = this;
	}

	/**
	 * CONTRUCTOR
	 * 
	 * @param usuario
	 * @param con
	 */
	public Principal(EntidadeUsuario usuario, InterfaceServidor con) {
		this.user = usuario;
		this.conexaoCliente = con;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conexaoCliente.conectarChat(user, this);
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TadsZap");
		setBounds(100, 100, 350, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnConexo = new JMenu("Conexão");
		menuBar.add(mnConexo);

		JMenuItem mntmTransissao = new JMenuItem("Transmissão");
		mntmTransissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				enviarTransmissao();

			}
		});
		mnConexo.add(mntmTransissao);

		JMenuItem mntmNewMenuItem = new JMenuItem("Alterar Dados");
		mnConexo.add(mntmNewMenuItem);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexaoCliente.desconectarChat(user);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnConexo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 324, 0 };
		gbl_contentPane.rowHeights = new int[] { 531, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		contentPane.add(splitPane, gbc_splitPane);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNomeLogado = new JLabel(user.getNome());
		lblNomeLogado.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GridBagConstraints gbc_lblNomeLogado = new GridBagConstraints();
		gbc_lblNomeLogado.insets = new Insets(0, 0, 5, 0);
		gbc_lblNomeLogado.gridwidth = 4;
		gbc_lblNomeLogado.gridx = 0;
		gbc_lblNomeLogado.gridy = 0;
		panel.add(lblNomeLogado, gbc_lblNomeLogado);

		final JComboBox<Status> CBStatus = new JComboBox<Status>();
		CBStatus.setModel(new DefaultComboBoxModel<Status>(Status.values()));
		CBStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user.setStatus(Status.valueOf(CBStatus.getSelectedItem().toString()));
					conexaoCliente.atualizarStatus(user);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_CBStatus = new GridBagConstraints();
		gbc_CBStatus.gridwidth = 4;
		gbc_CBStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_CBStatus.gridx = 0;
		gbc_CBStatus.gridy = 2;
		panel.add(CBStatus, gbc_CBStatus);

		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 322, 0 };
		gbl_panel_1.rowHeights = new int[] { 405, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel_1.add(tabbedPane, gbc_tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Contatos", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panel_2.add(scrollPane_1, gbc_scrollPane_1);

		tableContatos = new JTable();
		tableContatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					EntidadeUsuario usuario = new EntidadeUsuario();

					int linha = tableContatos.getSelectedRow();

					usuario.setNome(tableContatos.getValueAt(linha, 0).toString());
					usuario.setStatus(Status.valueOf(tableContatos.getValueAt(linha, 1).toString()));

//					tabbedConversas.add(usuario.getNome(), new Conversa(usuario));
					Chat.getFrame().setVisible(true);
				}
			}
		});
		scrollPane_1.setViewportView(tableContatos);

		field_pesquisa_contato = new JTextField();
		field_pesquisa_contato.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		GridBagConstraints gbc_field_pesquisa_contato = new GridBagConstraints();
		gbc_field_pesquisa_contato.insets = new Insets(0, 0, 0, 5);
		gbc_field_pesquisa_contato.fill = GridBagConstraints.BOTH;
		gbc_field_pesquisa_contato.gridx = 0;
		gbc_field_pesquisa_contato.gridy = 1;
		panel_2.add(field_pesquisa_contato, gbc_field_pesquisa_contato);
		field_pesquisa_contato.setColumns(10);

		JButton btnPesquisaContato = new JButton("Pesquisar");
		GridBagConstraints gbc_btnPesquisaContato = new GridBagConstraints();
		gbc_btnPesquisaContato.gridx = 1;
		gbc_btnPesquisaContato.gridy = 1;
		panel_2.add(btnPesquisaContato, gbc_btnPesquisaContato);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Chat", null, panel_3, null);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		tabbedConversas = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 0;
		panel_3.add(tabbedConversas, gbc_tabbedPane_1);

		global = this;
	}

	public static void enviaArq(Arquivo arquivo) {
		global.enviarArquivo(arquivo);
	}

	public static void enviaMsg(String msg) {
		global.enviarMensagem(msg);
	}

	public void enviarTransmissao() {
		new Transmissao(conexaoCliente, global.user).setVisible(true);
		;
	}

	public void enviarArquivo(Arquivo arquivo) {
		String titleAt = tabbedConversas.getTitleAt(tabbedConversas.getSelectedIndex());

		try {
			if (titleAt.equals("Público")) {
				JOptionPane.showMessageDialog(null, "Voçe não pode enviar arquivo para todos");
			} else {
				EntidadeUsuario destinatario = new EntidadeUsuario();
				destinatario.setNome(titleAt);
				conexaoCliente.enviarArquivo(user, destinatario, arquivo);
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void enviarMensagem(String mensagem) {

		String titleAt = tabbedConversas.getTitleAt(tabbedConversas.getSelectedIndex());

		try {

			EntidadeUsuario destinatario = new EntidadeUsuario();
			destinatario.setNome(titleAt);
			conexaoCliente.enviarMensagem(user, destinatario, mensagem);
			System.out.println(titleAt + " enviou:  " + mensagem);

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void receberContatosOnline(List<EntidadeUsuario> lista) throws RemoteException {
		List<EntidadeUsuario> listaOnline = new ArrayList<>();

		lista.forEach(e -> {
			if (!e.getStatus().equals(Status.OFFLINE)) {
				listaOnline.add(e);
			}
		});

		TableModel tb = new MeuModelo(listaOnline);
		tableContatos.setModel(tb);

	}

	public void receberListaParticipantes(ArrayList<EntidadeUsuario> lista) throws RemoteException {

	}

	public void receberMensagem(EntidadeUsuario remetente, String mensagem) throws RemoteException {
		int totaltabs = tabbedConversas.getTabCount();
		boolean ativo = false;
		if (totaltabs != 0) {
			for (int i = 0; i < totaltabs; i++) {
				String titulo = tabbedConversas.getTitleAt(i);

				System.out.println(remetente.getNome() + " - " + titulo);
				if (titulo.equalsIgnoreCase(remetente.getNome())) {
					ativo = true;
					Conversa conversa = (Conversa) tabbedConversas.getComponentAt(i);
					conversa.mostrar(remetente, mensagem);
					System.out.println(conversa.toString());
					tabbedConversas.setSelectedIndex(i);
				}
			}
		}

		if (!ativo) {

			Conversa conversa = new Conversa(user);
			tabbedConversas.add(remetente.getNome(), conversa);
			conversa.mostrar(remetente, mensagem);

		}
	}

	@Override
	public void receberArquivo(EntidadeUsuario remetente, Arquivo arquivo) throws RemoteException {
		new FileTransfer(remetente, arquivo);
	}
}
