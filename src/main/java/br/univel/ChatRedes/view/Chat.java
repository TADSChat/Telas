package br.univel.ChatRedes.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Chat extends JFrame{

	private JTextField textFieldMensagem;
	
	private static JFrame frame;

	public Chat() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setTitle("Chat");
		setBounds(100, 100, 511, 370);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{59, 326, 31, 17, 32, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblUser = new JLabel("user");
		lblUser.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.gridwidth = 3;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 0;
		panel.add(lblUser, gbc_lblUser);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints(); 
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_1.add(textArea, gbc_textArea);
		
		textFieldMensagem = new JTextField();
		textFieldMensagem.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 13));
		textFieldMensagem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					textArea.append(textFieldMensagem.getText() + "\n");
					textFieldMensagem.setText("");
				}
			}
		});
		GridBagConstraints gbc_textFieldMensagem = new GridBagConstraints();
		gbc_textFieldMensagem.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMensagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMensagem.gridx = 1;
		gbc_textFieldMensagem.gridy = 2;
		panel.add(textFieldMensagem, gbc_textFieldMensagem);
		textFieldMensagem.setColumns(10);
		
		Icon imagem_mensagem = new ImageIcon("send-button.png");
		JButton btnEnviar = new JButton(imagem_mensagem);
		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append(textFieldMensagem.getText() + " \n");
				textFieldMensagem.setText("");
			}
		});
		btnEnviar.setContentAreaFilled(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnEnviar, gbc_btnNewButton);
		
		
		Icon imagem_arquivo = new ImageIcon("send-arquivo.png");
		JButton btnArquivo = new JButton(imagem_arquivo);
		btnArquivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("explorer.exe " + "C:\\Usuario\\");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnArquivo.setContentAreaFilled(false);
		GridBagConstraints gbc_btnArquivo = new GridBagConstraints();
		gbc_btnArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnArquivo.anchor = GridBagConstraints.WEST;
		gbc_btnArquivo.gridx = 3;
		gbc_btnArquivo.gridy = 2;
		panel.add(btnArquivo, gbc_btnArquivo);
	
		frame = this;
	}
	
	public static JFrame getFrame(){
		return frame;
	}
}

