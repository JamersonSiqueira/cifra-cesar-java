package model;

import java.util.Scanner;

public class CifradorCesar {
	
	private String mensagem; //mensagem para ser criptografada
	private int chave; //chave de criptografia
	private static char[] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	//alfabeto para conversão /\
	
	public CifradorCesar() { //construtor vazio
		super();
	}
	
	public CifradorCesar(String mensagem, int chave) { //construtor com msg e chave
		this.mensagem = mensagem;
		this.chave = chave;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public static char[] getAlfabeto() {
		return alfabeto;
	}

	public static void setAlfabeto(char[] alfabeto) {
		CifradorCesar.alfabeto = alfabeto;
	}
	
	public String encriptar() {
		
		char[] msgEncriptada = new char[getMensagem().length()];
		
		for(int i=0;i<getMensagem().length();i++) {
			if(getMensagem().charAt(i) == ' ') {
				msgEncriptada[i]=getMensagem().charAt(i);
			}	else {
				for(int x=0;x<getAlfabeto().length;x++) {
					if(getMensagem().charAt(i)==getAlfabeto()[x]) {
						if(getChave()<0) {
							msgEncriptada[i]=getAlfabeto()[(x+getChave()+getAlfabeto().length)%getAlfabeto().length];
							x=getAlfabeto().length;
						} else {
							msgEncriptada[i]=getAlfabeto()[(x+getChave())%getAlfabeto().length];
							x=getAlfabeto().length;
						}
					} else {
						msgEncriptada[i]=getMensagem().charAt(i);
					}
				}
			}
		}
		StringBuilder criadorDeString = new StringBuilder();
		criadorDeString.append(msgEncriptada);
		String txt = criadorDeString.toString();
		return txt;
	}
	
	public String decriptar() {
		
		char[] msgEncriptada = new char[getMensagem().length()];
		
		for(int i=0;i<getMensagem().length();i++) {
			if(getMensagem().charAt(i) == ' ') {
				msgEncriptada[i]=getMensagem().charAt(i);
			}	else {
				for(int x=0;x<getAlfabeto().length;x++) {
					if(getMensagem().charAt(i)==getAlfabeto()[x]) {
						if(x<getChave()) {
							msgEncriptada[i]=getAlfabeto()[(x-getChave()+getAlfabeto().length)%getAlfabeto().length];
							x=getAlfabeto().length;
						} else {
							msgEncriptada[i]=getAlfabeto()[(x-getChave())%getAlfabeto().length];
							x=getAlfabeto().length;
						}
					} else {
						msgEncriptada[i]=getMensagem().charAt(i);
					}
				}
			}
		}
		StringBuilder criadorDeString = new StringBuilder();
		criadorDeString.append(msgEncriptada);
		String txt = criadorDeString.toString();
		return txt;
		
	}
}
