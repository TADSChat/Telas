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

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableContatos;
	public EntidadeUsuario user;
	
	private static Principal global;
	private JTextField textFieldPesquisar;

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
	 * CONSTRUCTOR
	 */
	public Principal() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TadsZap");
		setBounds(100, 100, 511, 370);
		setBackground(Color.WHITE);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);

		JMenu mnConexo = new JMenu("Conexão");
		menuBar.add(mnConexo);

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
				System.exit(0);
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
		
		tableContatos = new JTable();
		tableContatos.setBackground(Color.WHITE);
		GridBagConstraints gbc_tableContato = new GridBagConstraints();
		gbc_tableContato.insets = new Insets(0, 0, 5, 5);
		gbc_tableContato.fill = GridBagConstraints.BOTH;
		gbc_tableContato.gridx = 1;
		gbc_tableContato.gridy = 0;
		panel_1.add(tableContatos, gbc_tableContato);
		
		textFieldPesquisar = new JTextField();
		textFieldPesquisar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textFieldPesquisar, gbc_textField);
		textFieldPesquisar.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.gridx = 2;
		gbc_btnPesquisar.gridy = 1;
		panel_1.add(btnPesquisar, gbc_btnPesquisar);


		global = this;
	}

	public static JFrame getFrame(){
		return global;
	}
}
