package control;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeitorTxt {

	Component parent;
	Path caminho;

	public LeitorTxt(Component parent) {
		this.parent=parent;
	}

	public String carregaTexto() {
		this.caminho = pegaCaminhoDeArquivoOuDiretorio();
		String texto=obterTexto(caminho);
		return texto;
	}

	public String obterTexto(Path caminho) {
		String texto=null;
		if (caminho != null && Files.exists(caminho)){ // se existir exibe informacoes
			try {
				StringBuilder criadorDeString = new StringBuilder();
				Scanner entrada=new Scanner(caminho);
				while(entrada.hasNext()) {
					String campos[] = entrada.nextLine().split("#");
					criadorDeString.append(campos[0]).append("\n");	
				}
				texto = criadorDeString.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}


		} 
		else // se caminho nao existe
		{
			JOptionPane.showMessageDialog(this.parent, caminho.getFileName() +
					" nao existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return texto;

	} // fim do metodo analisaCaminho


	// pergunta ao usuario o caminho
	private Path pegaCaminhoDeArquivoOuDiretorio(){
		// configura dialogo para selecionar arquivo ou diretorio
		JFileChooser escolhedorDeCaminho = new JFileChooser();
		escolhedorDeCaminho.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text");
		escolhedorDeCaminho.setFileFilter(filter);
		escolhedorDeCaminho.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int resultado = escolhedorDeCaminho.showOpenDialog(this.parent);

		// se o usuario clicar em cancelar, encerra programa
		if (resultado == JFileChooser.CANCEL_OPTION)
			System.exit(1);

		// return Path representing the selected file
		return escolhedorDeCaminho.getSelectedFile().toPath();
	} 
	
	public void criarArquivo()	{
		// configura dialogo para selecionar arquivo ou diretorio
		JFileChooser escolhedorDeCaminho = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		escolhedorDeCaminho.setFileFilter(filter);
		escolhedorDeCaminho.setCurrentDirectory(new File("."));
		int resultado = escolhedorDeCaminho.showSaveDialog(this.parent); //parent component to JFileChooser
		if (resultado == JFileChooser.APPROVE_OPTION) { //OK button pressed by user
		        File file = escolhedorDeCaminho.getSelectedFile();
		        this.caminho = file.toPath();
		        this.caminho = caminho.resolveSibling(caminho.getFileName() + ".txt");
		}

		// se o usuario clicar em cancelar, encerra programa
		if (resultado == JFileChooser.CANCEL_OPTION)
			System.exit(1);
		} 
	
	public Path getCaminho() {
		return caminho;
	}

	public void setCaminho(Path caminho) {
		this.caminho = caminho;
	}
	}