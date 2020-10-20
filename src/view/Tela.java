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
import control.ControladorNovosArquivos;
import control.CriaArquivoDeTexto;
import control.LeitorTxt;
import model.CifradorCesar;

public class Tela extends JFrame
{
	private final JTextArea areaDeTexto; // exibe conteudos de arquivo 
	Button botaoDecriptar;
	Button botaoNovo;
	Button botaoEncriptar;
	Button botaoAbrirArquivo;
	Button botaoSalvar;
	JScrollPane painelRolagem;
	Path caminho;
	int chave;
	

	// configura a GUI
	public Tela() throws IOException
	{
		super("Cifra de César");//aciona o contrustor superclasse
		setLayout(new BorderLayout());
		areaDeTexto = new JTextArea();
		painelRolagem= new JScrollPane(areaDeTexto);
		
		add(painelRolagem,BorderLayout.CENTER); // area de exibicao vai ter uma barra de rolagem
		
		botaoAbrirArquivo=new Button("Abrir Arquivo Existente");
		botaoAbrirArquivo.addActionListener(new ControladorCarregarArquivos(this));
		
		botaoEncriptar=new Button("Cifrar");
		botaoEncriptar.setEnabled(false);
		botaoEncriptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cifrarTexto();	
			}
		});
		
		botaoDecriptar=new Button("Decifrar");
		botaoDecriptar.setEnabled(false);
		botaoDecriptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decifrarTexto();	
			}
		});
		
		botaoNovo=new Button("Novo Arquivo");
		botaoNovo.addActionListener(new ControladorNovosArquivos(this));
		
		botaoSalvar=new Button("Salvar");
		botaoSalvar.setEnabled(false);
		botaoSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarTexto();
			}
		});
		
		
		JPanel panelBotoes = new JPanel();
		panelBotoes.add(botaoAbrirArquivo);
		panelBotoes.add(botaoNovo);
		panelBotoes.add(botaoEncriptar);
		panelBotoes.add(botaoDecriptar);
		panelBotoes.add(botaoSalvar);
		
		add(panelBotoes,BorderLayout.SOUTH);
		
		this.setSize(520, 520); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true); 
	} 
	// analise e exibe informacoes sobre arquivo ou diretorio especificado pelo usuario 

	public void lerTexto() {
		LeitorTxt leitor=new LeitorTxt(this);
		String texto=leitor.carregaTexto();
		caminho = leitor.getCaminho();
		areaDeTexto.setText(texto); // exibe conteudo da String de informacoes
		areaDeTexto.setCaretPosition(0);
	}
	
	public void cifrarTexto() {
		String texto = areaDeTexto.getText();
		CifradorCesar cifrador=new CifradorCesar(texto,chave);
		areaDeTexto.setText(cifrador.encriptar().toString()); // exibe conteudo da String de informacoes
		areaDeTexto.setCaretPosition(0);
	}
	
	public void decifrarTexto() {
		String texto = areaDeTexto.getText();
		CifradorCesar cifrador=new CifradorCesar(texto,chave);
		areaDeTexto.setText(cifrador.decriptar().toString()); // exibe conteudo da String de informacoes
		areaDeTexto.setCaretPosition(0);
	}
	
	public void salvarTexto() {
		String texto = areaDeTexto.getText();
		CriaArquivoDeTexto cria = new CriaArquivoDeTexto(caminho,texto);
		JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
	}
	
	public void novoArquivo() {
		LeitorTxt leitor=new LeitorTxt(this);
		leitor.criarArquivo();
		this.caminho = leitor.getCaminho();
		JOptionPane.showMessageDialog(null, "Arquivo criado com sucesso!");
	}
	
	public void chave() {
		this.chave = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a chave"));
	}
	
	public Component getBotaoEncriptar() {
		return botaoEncriptar;
	}
	
	public Component getBotaoDecriptar() {
		return botaoDecriptar;
	}
	
	public Component getBotaoSalvar() {
		return botaoSalvar;
	}


} // fim da classe JFileChooserDemo


/*************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/