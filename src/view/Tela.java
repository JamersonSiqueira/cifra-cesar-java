package view;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.ControladorCarregarArquivos;
import control.ControladorInteracoesTxt;
import model.CifradorCesar;

/*************************************************************************
 * Aluno: Adenilson Jamerson Siqueira Santos				              *
 * Matr�cula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class Tela extends JFrame
{
	private final JTextArea areaDeTexto; //�rea para exibi��o de texto
	//Bot�es da aplica��o
	Button botaoDecriptar;
	Button botaoNovo;
	Button botaoEncriptar;
	Button botaoAbrirArquivo;
	Button botaoSalvar;
	//Painel de Rolagem
	JScrollPane painelRolagem;
	//Caminho absoluto dos arquivos criados/abertos
	Path caminho;
	//Chave de convers�o
	int chave;
	

	// configura a Interface
	public Tela() throws IOException
	{
		super("Cifra de C�sar");//Superclasse
		setLayout(new BorderLayout()); //Layout de borda
		areaDeTexto = new JTextArea();	//Instanciamento da �rea de texto
		painelRolagem= new JScrollPane(areaDeTexto); //Adi��o da �rea ao painel
		
		add(painelRolagem,BorderLayout.CENTER); // �rea de exibicao vai ter uma barra de rolagem
		
		//Configura��o do bot�o AbrirArquivo+Listener
		botaoAbrirArquivo=new Button("Abrir Arquivo"); 
		botaoAbrirArquivo.addActionListener(new ControladorCarregarArquivos(this,false)); //Listener conversa com controlador+indicador de arquivo novo=false
		
		//Configura��o do bot�o NovoArquivo+Listener
		botaoNovo=new Button("Novo Arquivo");
		botaoNovo.addActionListener(new ControladorCarregarArquivos(this,true)); //Listener conversa com controlador+indicador de arquivo novo=true
				
		//Configura��o do bot�o Encriptar+Listener
		botaoEncriptar=new Button("Cifrar");
		botaoEncriptar.setEnabled(false);
		botaoEncriptar.addActionListener(new ActionListener() {
			
			//Listener aciona m�todo para Cifrar Texto
			@Override
			public void actionPerformed(ActionEvent e) {
				cifrarTexto();	
			}
		});
		
		//Configura��o do bot�o Decriptar+Listener
		botaoDecriptar=new Button("Decifrar");
		botaoDecriptar.setEnabled(false);
		botaoDecriptar.addActionListener(new ActionListener() {
			
			//Listener aciona m�todo para Decifrar Texto
			@Override
			public void actionPerformed(ActionEvent e) {
				decifrarTexto();	
			}
		});
		
		//Configura��o do bot�o Salvar+Listener
		botaoSalvar=new Button("Salvar");
		botaoSalvar.setEnabled(false);
		botaoSalvar.addActionListener(new ActionListener() {
			
			//Listener aciona m�todo para Salvar Texto em .txt
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarTexto();
			}
		});
		
		//Adi��o do painel de bot�es e os bot�es em tela
		JPanel panelBotoes = new JPanel();
		panelBotoes.add(botaoAbrirArquivo);
		panelBotoes.add(botaoNovo);
		panelBotoes.add(botaoEncriptar);
		panelBotoes.add(botaoDecriptar);
		panelBotoes.add(botaoSalvar);
		add(panelBotoes,BorderLayout.SOUTH);
		
		//Configura��es de tela - Tamanho e CloseOperation
		this.setSize(520, 520); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
	}  
	
	//M�todo de leitura de texto
	public void lerTexto() {
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		String texto=leitor.carregaTexto(); //Captura do texto da �rea de texto
		caminho = leitor.getCaminho(); //pegar caminho do arquivo
		areaDeTexto.setText(texto); // exibe conteudo na �rea de texto
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//M�todo de encripta��o de texto via Cifra de C�sar
	public void cifrarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da �rea de texto
		CifradorCesar cifrador=new CifradorCesar(texto,chave); //Instancia de cifrador
		areaDeTexto.setText(cifrador.encriptar().toString()); // exibe conteudo na �rea de texto, cifrado
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//M�todo de decripta��o de texto via Cifra de C�sar
	public void decifrarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da �rea de texto
		CifradorCesar cifrador=new CifradorCesar(texto,chave); //Instancia de cifrador
		areaDeTexto.setText(cifrador.decriptar().toString()); // exibe conteudo na �rea de texto, decifrado
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//M�todo para salvar arquivo txt
	public void salvarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da �rea de texto
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		leitor.salvar(this.caminho, texto);// Inst�ncia de classe respons�vel por savar o txt
		JOptionPane.showMessageDialog(null, "Salvo com Sucesso"); //Msg de confirma��o
	}
	
	//M�todo para criar arquivo txt (para quando n�o h� arquivo .txt para ser aberto)
	public void novoArquivo() {
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		leitor.criarArquivo(); //M�todo para criar novo arquivo
		this.caminho = leitor.getCaminho(); //Captura do caminho do arquivo criado
		JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!"); //Msg de confirma��o
	}
	
	//M�todo para coleta da chave de encripta��o
	public void chave() {
		this.chave = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a chave"));
		//JPanel solicitando a chave para o usu�rio
	}
	
	//Getters dos bot�es para uso nas classes controladoras
	public Component getBotaoEncriptar() {
		return botaoEncriptar;
	}
	
	public Component getBotaoDecriptar() {
		return botaoDecriptar;
	}
	
	public Component getBotaoSalvar() {
		return botaoSalvar;
	}
}