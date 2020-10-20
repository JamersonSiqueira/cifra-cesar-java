package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Tela;

public class ControladorCarregarArquivos implements ActionListener{
	
	Tela tela;
	
	public ControladorCarregarArquivos(Tela tela) {
		
		this.tela=tela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tela.lerTexto();
		tela.chave();
		tela.getBotaoEncriptar().setEnabled(true);
		tela.getBotaoDecriptar().setEnabled(true);
		tela.getBotaoSalvar().setEnabled(true);
		
	}

}
