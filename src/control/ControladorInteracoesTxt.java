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
 * Matr�cula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class ControladorInteracoesTxt {

	//Inst�ncia do componente e do caminho do arquivo.
	private	Component parent;
	private	Path caminho;
	private Formatter output;
	private String conteudo;

	public ControladorInteracoesTxt(Component parent) { //Construtor
		this.parent=parent;
	}

	public String carregaTexto() { //M�todo de leitura de arquivo
		this.caminho = pegaCaminhoDeArquivoOuDiretorio(); //Obter caminho do arquivo
		String texto=obterTexto(caminho); //Chamada do m�todo de obten��o de texto, a partir do caminho
		return texto; //Retorno do conte�do do texto
	}

	public String obterTexto(Path caminho) { //M�todo de leitura do texto dentro do arquivo
		String texto=null; //Vari�vel a receber o texto
		if (caminho != null && Files.exists(caminho)){ // se existir arquivo e caminho, coleta as informa��es
			try { // Verifica��o circulada com try-catch
				StringBuilder criadorDeString = new StringBuilder(); // Construtor de Strings instanciado
				Scanner entrada=new Scanner(caminho); //Scanner para ler o arquivo de texto
				while(entrada.hasNext()) { //Enquanto existirem linhas no arquivo
					String campos[] = entrada.nextLine().split("#"); //Split das quebras de linha do texto
					criadorDeString.append(campos[0]).append("\n");	//Adi��o do texto sem quebra de linha do arquivo + quebra de linha via '\n'
				}
				texto = criadorDeString.toString(); //Extra��o do texto construido via StringBuilder
				entrada.close(); //Fechamento do Scanner
			} catch (IOException e) {
				e.printStackTrace(); //Exce��o para erros
			}

		} 
		else // Caso o caminho nao exista
		{
			JOptionPane.showMessageDialog(this.parent, caminho.getFileName() +
					" n�o existe", "ERROR", JOptionPane.ERROR_MESSAGE);
			return null; //retorno vazio
		}
		return texto; //retorna o texto coletado.

	}


	// pergunta ao usuario o caminho
	private Path pegaCaminhoDeArquivoOuDiretorio(){
		// configura dialogo para selecionar arquivo ou diretorio
		JFileChooser escolhedorDeCaminho = new JFileChooser();
		escolhedorDeCaminho.setCurrentDirectory(new File(".")); //aponta o diret�rio para a raiz do projeto
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text"); //filtro para s� aceitar .txt
		escolhedorDeCaminho.setFileFilter(filter); //Faz o FileChooser receber o filtro
		escolhedorDeCaminho.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Modo de sele��o para "Arquivos e diret�rios"
		int resultado = escolhedorDeCaminho.showOpenDialog(this.parent); //Resultado da abertura de arquivo

		// se o usuario clicar em cancelar, encerra programa
		if (resultado == JFileChooser.CANCEL_OPTION)
			System.exit(1);

		// return Path representing the selected file
		return escolhedorDeCaminho.getSelectedFile().toPath();
	} 
	
	public void criarArquivo()	{
		// configura dialogo para selecionar arquivo ou diretorio
		JFileChooser escolhedorDeCaminho = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text");//filtro para s� aceitar .txt
		escolhedorDeCaminho.setFileFilter(filter);//Faz o FileChooser receber o filtro
		escolhedorDeCaminho.setCurrentDirectory(new File("."));//aponta o diret�rio para a raiz do projeto
		int resultado = escolhedorDeCaminho.showSaveDialog(this.parent); //Resultado da abertura de arquivo
		if (resultado == JFileChooser.APPROVE_OPTION) { //Caso o bot�o de OK seja pressionado
		        File file = escolhedorDeCaminho.getSelectedFile(); //Novo arquivo a ser gerado
		        this.caminho = file.toPath(); //Faz a vari�vel caminho receber o caminho do arquivo
		        this.caminho = caminho.resolveSibling(caminho.getFileName() + ".txt"); //Salva arquivo com .txt no final
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
	
	   public void salvar(Path caminho,String conteudo) { //M�todo para guardar caminho e conte�do, para salvar
		   this.caminho = caminho;
		   this.conteudo = conteudo;
		   salvarArquivo(); //Coleta as informa��es e envia para o m�todo salvarArquivo();
	   }
	   
	   
	   public void salvarArquivo() //M�todo para salvar novos arquivos.
	   {
	      try
	      {
	         output = new Formatter(this.caminho.toString()); // Indica qual a sa�da do objeto (local a ser salvo)
	      }
	      catch (SecurityException securityException) //Catch dos erros
	      {
	         System.err.println("Permissao de escrita negada. Terminando.");
	         System.exit(1); //encerra o programa
	      } 
	      catch (FileNotFoundException fileNotFoundException)
	      {
	         System.err.println("Erro ao abrir o arquivo. Termindo.");
	         System.exit(1); //encerra o programa
	      } 
	      
	      try
	         {
	            // Sa�da do novo arquivo, dentro do caminho coletado, ap�s o output ser devidamente criado
	            output.format(this.conteudo);                             
	         } 
	         catch (FormatterClosedException formatterClosedException) //exce��es de erro
	         {
	            System.err.println("Erro escrevendo no arquivo. Terminando.");
	         } 
	         catch (NoSuchElementException elementException)
	         {
	            System.err.println("Entrada invalida. Por favor tente novamente.");  
	         }
	         
	         fecharArquivo(); //m�todo para fechar o output.
	   } 

	   

	   // fecha ouput de arquivos
	   public void fecharArquivo()
	   {
	      if (output != null)
	         output.close();
	   } 
	}