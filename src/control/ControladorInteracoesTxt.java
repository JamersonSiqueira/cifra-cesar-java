package control;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**************************************************************************
 * Aluno: Adenilson Jamerson Siqueira Santos				              *
 * Matrícula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class ControladorInteracoesTxt {

	//Instância do componente e do caminho do arquivo.
	Component parent;
	Path caminho;
	private Formatter output;
	private String conteudo;

	public ControladorInteracoesTxt(Component parent) { //Construtor
		this.parent=parent;
	}

	public String carregaTexto() { //Método de leitura de arquivo
		this.caminho = pegaCaminhoDeArquivoOuDiretorio(); //Obter caminho do arquivo
		String texto=obterTexto(caminho); //Chamada do método de obtenção de texto, a partir do caminho
		return texto; //Retorno do conteúdo do texto
	}

	public String obterTexto(Path caminho) { //Método de leitura do texto dentro do arquivo
		String texto=null; //Variável a receber o texto
		if (caminho != null && Files.exists(caminho)){ // se existir arquivo e caminho, coleta as informações
			try { // Verificação circulada com try-catch
				StringBuilder criadorDeString = new StringBuilder(); // Construtor de Strings instanciado
				Scanner entrada=new Scanner(caminho); //Scanner para ler o arquivo de texto
				while(entrada.hasNext()) { //Enquanto existirem linhas no arquivo
					String campos[] = entrada.nextLine().split("#"); //Split das quebras de linha do texto
					criadorDeString.append(campos[0]).append("\n");	//Adição do texto sem quebra de linha do arquivo + quebra de linha via '\n'
				}
				texto = criadorDeString.toString(); //Extração do texto construido via StringBuilder
				entrada.close(); //Fechamento do Scanner
			} catch (IOException e) {
				e.printStackTrace(); //Exceção para erros
			}

		} 
		else // Caso o caminho nao exista
		{
			JOptionPane.showMessageDialog(this.parent, caminho.getFileName() +
					" não existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null; //retorno vazio
		}
		return texto; //retorna o texto coletado.

	}


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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text");
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
	
	//Getter e Setter do caminho
	public Path getCaminho() {
		return caminho;
	}

	public void setCaminho(Path caminho) {
		this.caminho = caminho;
	}
	
	   public void salvar(Path caminho,String conteudo) {
		   this.caminho = caminho;
		   this.conteudo = conteudo;
		   salvarArquivo();
	   }
	   
	   
	   public void salvarArquivo()
	   {
	      try
	      {
	         output = new Formatter(this.caminho.toString()); // abre arquivo
	      }
	      catch (SecurityException securityException)
	      {
	         System.err.println("Permissao de escrita negada. Terminando.");
	         System.exit(1); //encerra o programa
	      } 
	      catch (FileNotFoundException fileNotFoundException)
	      {
	         System.err.println("Erro ao abrir o arquivo. Termindo.");
	         System.exit(1); //encerra o programa
	      } 
	      adicionarRegistros();
	   } 

	   // adiciona registros ao arquivo
	   public void adicionarRegistros()
	   {
	         try
	         {
	            // saida do novo registro ara o arquivo; assume que as entradas foram validas
	            output.format(this.conteudo);                             
	         } 
	         catch (FormatterClosedException formatterClosedException)
	         {
	            System.err.println("Erro escrevendo no arquivo. Terminando.");
	         } 
	         catch (NoSuchElementException elementException)
	         {
	            System.err.println("Entrada invalida. Por favor tente novamente.");  
	         }
	         
	         fecharArquivo();
	}
	   

	   // fecha arquivos
	   public void fecharArquivo()
	   {
	      if (output != null)
	         output.close();
	   } 
	}