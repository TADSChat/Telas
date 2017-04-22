package br.univel.ChatRedes.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.univel.ChatRedes.model.JNumberField;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;
import common.Status;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField field_email;
	private JTextField field_servidor;
	private JTextField field_porta;
	private JPasswordField field_senha;
	private static Registry registry;
	private static InterfaceUsuario cliente;
	private static InterfaceServidor servidor;
	private static EntidadeUsuario meuUsuario;
	private JLabel label;

	/**
	 * Create the frame.
	 */
	
	public Login() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(true);
		setTitle("TadsZap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 370);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 108, 62, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		Icon logo = new ImageIcon("group.png");
		label = new JLabel(logo);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		contentPane.add(label, gbc_label);
		
		field_email = new JTextField("Email");
		field_email.setForeground(Color.GRAY);
		field_email.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		field_email.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				field_email.setForeground(Color.BLACK);
				field_email.setText("");
			}
			
		});
		GridBagConstraints gbc_field_email = new GridBagConstraints();
		gbc_field_email.gridwidth = 2;
		gbc_field_email.insets = new Insets(0, 0, 5, 5);
		gbc_field_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_email.gridx = 1;
		gbc_field_email.gridy = 5;
		contentPane.add(field_email, gbc_field_email);
		field_email.setColumns(10);

		field_senha = new JPasswordField("senha");
		field_senha.setForeground(Color.GRAY);
		field_senha.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		field_senha.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				field_senha.setForeground(Color.BLACK);
				field_senha.setText("");
			}
		});
		GridBagConstraints gbc_field_senha = new GridBagConstraints();
		gbc_field_senha.gridwidth = 2;
		gbc_field_senha.insets = new Insets(0, 0, 5, 5);
		gbc_field_senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_senha.gridx = 1;
		gbc_field_senha.gridy = 6;
		contentPane.add(field_senha, gbc_field_senha);

		field_servidor = new JTextField("Servidor");
		field_servidor.setFont(new Font("YU Gothic Medium", Font.PLAIN, 15));
		field_servidor.setForeground(Color.GRAY);
		field_servidor.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				field_servidor.setForeground(Color.BLACK);
				field_servidor.setText("");
			}
		});
		GridBagConstraints gbc_field_servidor = new GridBagConstraints();
		gbc_field_servidor.insets = new Insets(0, 0, 5, 5);
		gbc_field_servidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_servidor.gridx = 1;
		gbc_field_servidor.gridy = 7;
		contentPane.add(field_servidor, gbc_field_servidor);
		field_servidor.setColumns(10);

		field_porta = new JNumberField();
		field_porta.setFont(new Font("YU Gothic Medium", Font.PLAIN, 15));
		GridBagConstraints gbc_field_porta = new GridBagConstraints();
		gbc_field_porta.insets = new Insets(0, 0, 5, 5);
		gbc_field_porta.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_porta.gridx = 2;
		gbc_field_porta.gridy = 7;
		contentPane.add(field_porta, gbc_field_porta);
		field_porta.setColumns(10);

		JButton btnConectar = new JButton("Login");
		btnConectar.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		btnConectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Principal tela = new Principal();
				
				tela.getFrame().setVisible(true);
				
				dispose();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.gridwidth = 2;
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 1;
		gbc_btnConectar.gridy = 8;
		contentPane.add(btnConectar, gbc_btnConectar);
	}
}
