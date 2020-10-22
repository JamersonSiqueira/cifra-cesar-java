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
 * Matrícula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class Tela extends JFrame
{
	private final JTextArea areaDeTexto; //Área para exibição de texto
	//Botões da aplicação
	Button botaoDecriptar;
	Button botaoNovo;
	Button botaoEncriptar;
	Button botaoAbrirArquivo;
	Button botaoSalvar;
	//Painel de Rolagem
	JScrollPane painelRolagem;
	//Caminho absoluto dos arquivos criados/abertos
	Path caminho;
	//Chave de conversão
	int chave;
	

	// configura a Interface
	public Tela() throws IOException
	{
		super("Cifra de César");//Superclasse
		setLayout(new BorderLayout()); //Layout de borda
		areaDeTexto = new JTextArea();	//Instanciamento da Área de texto
		painelRolagem= new JScrollPane(areaDeTexto); //Adição da área ao painel
		
		add(painelRolagem,BorderLayout.CENTER); // Área de exibicao vai ter uma barra de rolagem
		
		//Configuração do botão AbrirArquivo+Listener
		botaoAbrirArquivo=new Button("Abrir Arquivo"); 
		botaoAbrirArquivo.addActionListener(new ControladorCarregarArquivos(this,false)); //Listener conversa com controlador+indicador de arquivo novo=false
		
		//Configuração do botão NovoArquivo+Listener
		botaoNovo=new Button("Novo Arquivo");
		botaoNovo.addActionListener(new ControladorCarregarArquivos(this,true)); //Listener conversa com controlador+indicador de arquivo novo=true
				
		//Configuração do botão Encriptar+Listener
		botaoEncriptar=new Button("Cifrar");
		botaoEncriptar.setEnabled(false);
		botaoEncriptar.addActionListener(new ActionListener() {
			
			//Listener aciona método para Cifrar Texto
			@Override
			public void actionPerformed(ActionEvent e) {
				cifrarTexto();	
			}
		});
		
		//Configuração do botão Decriptar+Listener
		botaoDecriptar=new Button("Decifrar");
		botaoDecriptar.setEnabled(false);
		botaoDecriptar.addActionListener(new ActionListener() {
			
			//Listener aciona método para Decifrar Texto
			@Override
			public void actionPerformed(ActionEvent e) {
				decifrarTexto();	
			}
		});
		
		//Configuração do botão Salvar+Listener
		botaoSalvar=new Button("Salvar");
		botaoSalvar.setEnabled(false);
		botaoSalvar.addActionListener(new ActionListener() {
			
			//Listener aciona método para Salvar Texto em .txt
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarTexto();
			}
		});
		
		//Adição do painel de botões e os botões em tela
		JPanel panelBotoes = new JPanel();
		panelBotoes.add(botaoAbrirArquivo);
		panelBotoes.add(botaoNovo);
		panelBotoes.add(botaoEncriptar);
		panelBotoes.add(botaoDecriptar);
		panelBotoes.add(botaoSalvar);
		add(panelBotoes,BorderLayout.SOUTH);
		
		//Configurações de tela - Tamanho e CloseOperation
		this.setSize(520, 520); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
	}  
	
	//Método de leitura de texto
	public void lerTexto() {
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		String texto=leitor.carregaTexto(); //Captura do texto da área de texto
		caminho = leitor.getCaminho(); //pegar caminho do arquivo
		areaDeTexto.setText(texto); // exibe conteudo na área de texto
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//Método de encriptação de texto via Cifra de César
	public void cifrarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da área de texto
		CifradorCesar cifrador=new CifradorCesar(texto,chave); //Instancia de cifrador
		areaDeTexto.setText(cifrador.encriptar().toString()); // exibe conteudo na área de texto, cifrado
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//Método de decriptação de texto via Cifra de César
	public void decifrarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da área de texto
		CifradorCesar cifrador=new CifradorCesar(texto,chave); //Instancia de cifrador
		areaDeTexto.setText(cifrador.decriptar().toString()); // exibe conteudo na área de texto, decifrado
		areaDeTexto.setCaretPosition(0); //Reposiciona cursor
	}
	
	//Método para salvar arquivo txt
	public void salvarTexto() {
		String texto = areaDeTexto.getText(); //Captura do texto da área de texto
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		leitor.salvar(this.caminho, texto);// Instância de classe responsável por savar o txt
		JOptionPane.showMessageDialog(null, "Salvo com Sucesso"); //Msg de confirmação
	}
	
	//Método para criar arquivo txt (para quando não há arquivo .txt para ser aberto)
	public void novoArquivo() {
		ControladorInteracoesTxt leitor=new ControladorInteracoesTxt(this); //Cria-se um leitor de arquivo txt
		leitor.criarArquivo(); //Método para criar novo arquivo
		this.caminho = leitor.getCaminho(); //Captura do caminho do arquivo criado
		JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!"); //Msg de confirmação
	}
	
	//Método para coleta da chave de encriptação
	public void chave() {
		this.chave = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a chave"));
		//JPanel solicitando a chave para o usuário
	}
	
	//Getters dos botões para uso nas classes controladoras
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