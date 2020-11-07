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
	private	Component parent;
	private	Path caminho;
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
		escolhedorDeCaminho.setCurrentDirectory(new File(".")); //aponta o diretório para a raiz do projeto
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text"); //filtro para só aceitar .txt
		escolhedorDeCaminho.setFileFilter(filter); //Faz o FileChooser receber o filtro
		escolhedorDeCaminho.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Modo de seleção para "Arquivos e diretórios"
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Texto (.txt)", "txt", "text");//filtro para só aceitar .txt
		escolhedorDeCaminho.setFileFilter(filter);//Faz o FileChooser receber o filtro
		escolhedorDeCaminho.setCurrentDirectory(new File("."));//aponta o diretório para a raiz do projeto
		int resultado = escolhedorDeCaminho.showSaveDialog(this.parent); //Resultado da abertura de arquivo
		if (resultado == JFileChooser.APPROVE_OPTION) { //Caso o botão de OK seja pressionado
		        File file = escolhedorDeCaminho.getSelectedFile(); //Novo arquivo a ser gerado
		        this.caminho = file.toPath(); //Faz a variável caminho receber o caminho do arquivo
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
	
	   public void salvar(Path caminho,String conteudo) { //Método para guardar caminho e conteúdo, para salvar
		   this.caminho = caminho;
		   this.conteudo = conteudo;
		   salvarArquivo(); //Coleta as informações e envia para o método salvarArquivo();
	   }
	   
	   
	   public void salvarArquivo() //Método para salvar novos arquivos.
	   {
	      try
	      {
	         output = new Formatter(this.caminho.toString()); // Indica qual a saída do objeto (local a ser salvo)
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
	            // Saída do novo arquivo, dentro do caminho coletado, após o output ser devidamente criado
	            output.format(this.conteudo);                             
	         } 
	         catch (FormatterClosedException formatterClosedException) //exceções de erro
	         {
	            System.err.println("Erro escrevendo no arquivo. Terminando.");
	         } 
	         catch (NoSuchElementException elementException)
	         {
	            System.err.println("Entrada invalida. Por favor tente novamente.");  
	         }
	         
	         fecharArquivo(); //método para fechar o output.
	   } 

	   

	   // fecha ouput de arquivos
	   public void fecharArquivo()
	   {
	      if (output != null)
	         output.close();
	   } 
	}