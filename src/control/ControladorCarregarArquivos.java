package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Tela;

/**************************************************************************
 * Aluno: Adenilson Jamerson Siqueira Santos				              *
 * Matrícula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class ControladorCarregarArquivos implements ActionListener{
	
	private	Tela tela;
	private boolean ehArquivoNovo; //Indicador para "Novo Arquivo", se for true, irá salvar como novo arquivo, se for false
								   //irá abrir arquivo existente
	
	public ControladorCarregarArquivos(Tela tela, boolean ehArquivoNovo) { //Método construtor
		this.ehArquivoNovo=ehArquivoNovo;
		this.tela=tela;
	}

	@Override //Método para ser executado quando a ação do botão for realizada
	public void actionPerformed(ActionEvent e) {
		if(this.ehArquivoNovo) { //Caso o arquivo seja um novo arquivo
			tela.novoArquivo(); //Método para criar um novo arquivo
			tela.chave();		//Método para captura da chave
			tela.getBotaoEncriptar().setEnabled(true); //Ativa o botão de encriptar
			tela.getBotaoDecriptar().setEnabled(true); //Ativa o botão de decriptar
			tela.getBotaoSalvar().setEnabled(true); //Ativa o botão de salvar
		} else {
			tela.lerTexto(); //Aciona o método de ler texto
			tela.chave();		//Método para captura da chave
			tela.getBotaoEncriptar().setEnabled(true); //Ativa o botão de encriptar
			tela.getBotaoDecriptar().setEnabled(true); //Ativa o botão de decriptar
			tela.getBotaoSalvar().setEnabled(true); //Ativa o botão de salvar
		}
		
	}

}
