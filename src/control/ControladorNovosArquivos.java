package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Tela;

public class ControladorNovosArquivos implements ActionListener{
	
	Tela tela;
	
	public ControladorNovosArquivos(Tela tela) {
		
		this.tela=tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		tela.novoArquivo();
		tela.chave();
		tela.getBotaoEncriptar().setEnabled(true);
		tela.getBotaoDecriptar().setEnabled(true);
		tela.getBotaoSalvar().setEnabled(true);
		
	}

}
