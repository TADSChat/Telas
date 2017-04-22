package br.univel.ChatRedes.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Explorer extends JFrame {
	
	public void buscar() {
		FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("png", "txt", "doc", "jpg");
		
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileFilter(fileNameExtensionFilter);
		fileChooser.setDialogTitle("Selecionando arquivo");
		
		int resposta = fileChooser.showOpenDialog(null);
		
		if (resposta == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			FileReader fileReader;
			
			try {
				fileReader = new FileReader(file);
				BufferedReader buffered = new BufferedReader(fileReader);
				
				while (buffered.ready()) {
					System.out.println(buffered.readLine() + " \n");
				}
				
				buffered.close();
				fileReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
