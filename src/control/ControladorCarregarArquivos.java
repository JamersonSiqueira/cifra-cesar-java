package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Tela;

/**************************************************************************
 * Aluno: Adenilson Jamerson Siqueira Santos				              *
 * Matr�cula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class ControladorCarregarArquivos implements ActionListener{
	
	private	Tela tela;
	private boolean ehArquivoNovo; //Indicador para "Novo Arquivo", se for true, ir� salvar como novo arquivo, se for false
								   //ir� abrir arquivo existente
	
	public ControladorCarregarArquivos(Tela tela, boolean ehArquivoNovo) { //M�todo construtor
		this.ehArquivoNovo=ehArquivoNovo;
		this.tela=tela;
	}

	@Override //M�todo para ser executado quando a a��o do bot�o for realizada
	public void actionPerformed(ActionEvent e) {
		if(this.ehArquivoNovo) { //Caso o arquivo seja um novo arquivo
			tela.novoArquivo(); //M�todo para criar um novo arquivo
			tela.chave();		//M�todo para captura da chave
			tela.getBotaoEncriptar().setEnabled(true); //Ativa o bot�o de encriptar
			tela.getBotaoDecriptar().setEnabled(true); //Ativa o bot�o de decriptar
			tela.getBotaoSalvar().setEnabled(true); //Ativa o bot�o de salvar
		} else {
			tela.lerTexto(); //Aciona o m�todo de ler texto
			tela.chave();		//M�todo para captura da chave
			tela.getBotaoEncriptar().setEnabled(true); //Ativa o bot�o de encriptar
			tela.getBotaoDecriptar().setEnabled(true); //Ativa o bot�o de decriptar
			tela.getBotaoSalvar().setEnabled(true); //Ativa o bot�o de salvar
		}
		
	}

}
