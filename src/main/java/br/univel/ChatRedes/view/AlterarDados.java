package br.univel.ChatRedes.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AlterarDados extends JFrame{
	private JTextField textFieldEmail;
	private JTextField textFieldUsername;
	private static JFrame frame;
	private JPasswordField senhaField;
	private JPasswordField confirmacaoSenhaField;
	
	public AlterarDados() {
		setTitle("Alterar dados");
		setBounds(100, 100, 511, 370);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		textFieldEmail = new JTextField("Email");
		textFieldEmail.setFont(new Font("YU Gothic Medium", Font.PLAIN, 15));
		textFieldEmail.setForeground(Color.GRAY);
		textFieldEmail.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldEmail.setForeground(Color.BLACK);
				textFieldEmail.setText("");
			}
		});
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 2;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 1;
		panel.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldUsername = new JTextField("Username");
		textFieldUsername.setForeground(Color.GRAY);
		textFieldUsername.setFont(new Font("YU Gothic Medium", Font.PLAIN, 15));
		textFieldUsername.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				textFieldUsername.setForeground(Color.BLACK);
				textFieldUsername.setText("");
			}
		});
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 3;
		panel.add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		
		senhaField = new JPasswordField("Senha");
		senhaField.setForeground(Color.GRAY);
		senhaField.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		senhaField.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				senhaField.setForeground(Color.GRAY);
				senhaField.setText("");
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		panel.add(senhaField, gbc_passwordField);
		
		confirmacaoSenhaField = new JPasswordField("Senha");
		confirmacaoSenhaField.setForeground(Color.GRAY);
		confirmacaoSenhaField.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmacaoSenhaField.setForeground(Color.BLACK);
				confirmacaoSenhaField.setText("");
			}
		});
		confirmacaoSenhaField.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 7;
		panel.add(confirmacaoSenhaField, gbc_passwordField_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 9;
		panel.add(btnOk, gbc_btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 9;
		panel.add(btnCancelar, gbc_btnCancelar);
		
		frame = this;
	}
	
	public static JFrame getFrame(){
		return frame;
	}
}
