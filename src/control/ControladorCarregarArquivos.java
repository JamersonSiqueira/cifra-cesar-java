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
	private boolean ehArquivoNovo; //Indicador para "Novo Arquivo", se for true, irá salvar como novo arquivo, se for false, irá abrir arquivo existente
	
	public ControladorCarregarArquivos(Tela tela, boolean ehArquivoNovo) {
		this.ehArquivoNovo=ehArquivoNovo;
		this.tela=tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.ehArquivoNovo) {
			tela.novoArquivo();
			tela.chave();
			tela.getBotaoEncriptar().setEnabled(true);
			tela.getBotaoDecriptar().setEnabled(true);
			tela.getBotaoSalvar().setEnabled(true);
		} else {
			tela.lerTexto();
			tela.chave();
			tela.getBotaoEncriptar().setEnabled(true);
			tela.getBotaoDecriptar().setEnabled(true);
			tela.getBotaoSalvar().setEnabled(true);
		}
		
	}

}
