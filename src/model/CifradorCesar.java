package model;

/*************************************************************************
 * Aluno: Adenilson Jamerson Siqueira Santos				              *
 * Matr�cula: 321181042							                          *
 * Disciplina: Arquitetura de Software                            		  *
 * Professor: Galdir Reges     											  *
 *************************************************************************/

public class CifradorCesar {
	
	private String mensagem; //mensagem para ser criptografada
	private int chave; //chave de criptografia
	private static char[] alfabeto = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	//alfabeto para convers�o /\
	
	public CifradorCesar() { //construtor vazio
		super();
	}
	
	public CifradorCesar(String mensagem, int chave) { //construtor com msg e chave
		this.mensagem = mensagem;
		this.chave = chave;
	}

	//Getter e setter da mensagem
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	//Getter e setter da chave
	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	//Getter e setter do alfabeto
	public static char[] getAlfabeto() {
		return alfabeto;
	}

	public static void setAlfabeto(char[] alfabeto) {
		CifradorCesar.alfabeto = alfabeto;
	}
	
	//M�todo de encripta��o
	public String encriptar() {
		
		char[] msgEncriptada = new char[getMensagem().length()]; //Array de tipo char, para verifica��o das letras
		
		for(int i=0;i<getMensagem().length();i++) { //for analisando letra a letra
			if(getMensagem().charAt(i) == ' ') { //Caso a letra selecionada seja um espa�o
				msgEncriptada[i]=getMensagem().charAt(i); //Manter sem criptografia
			}	else { //Caso normal (letra)
				for(int x=0;x<getAlfabeto().length;x++) { //Loop para analisar o alfabeto
					if(getMensagem().charAt(i)==getAlfabeto()[x]) { //Caso encontre a letra no alfabeto
						if(getChave()<0) { //Chave negativa
							//Mensagem encriptada na posi��o i recebe a letra do alfabeto na posi��o:
							//Resto da divis�o entre x (posi��o dentro do alfabeto)+chave+TamanhoDoAlfabeto pelo tamanho do alfabeto.
							//PS: adicionado o TamanhoDoAlfabeto a equa��o, para trabalhar corretamente com a chave negativa.
							msgEncriptada[i]=getAlfabeto()[(x+getChave()
							+getAlfabeto().length)%getAlfabeto().length]; 
							//x vira tamanho do alfabeto (reset)
							x=getAlfabeto().length;
						} else { //Chave positiva
							//Mensagem encriptada na posi��o i recebe a letra do alfabeto na posi��o:
							//Resto da divis�o entre x (posi��o dentro do alfabeto)+chave pelo tamanho do alfabeto.
							msgEncriptada[i]=getAlfabeto()[(x+getChave())%getAlfabeto().length];
							//x vira tamanho do alfabeto (reset)
							x=getAlfabeto().length;
						}
					} else { //Caso seja um s�mbolo/caractere especial
						msgEncriptada[i]=getMensagem().charAt(i); //Mantem-se sem criptografia
					}
				}
			}
		}
		//Ap�s criptografia, constru��o da string para ser passada como retorno do m�todo.
		StringBuilder criadorDeString = new StringBuilder();
		criadorDeString.append(msgEncriptada);
		String txt = criadorDeString.toString();
		return txt;
	}
	
	public String decriptar() {
		
		char[] msgEncriptada = new char[getMensagem().length()]; //Array de tipo char, para verifica��o das letras
		
		for(int i=0;i<getMensagem().length();i++) { //Caso a letra selecionada seja um espa�o
			if(getMensagem().charAt(i) == ' ') { //Caso a letra selecionada seja um espa�o
				msgEncriptada[i]=getMensagem().charAt(i); //Manter sem criptografia
			}	else { //Caso normal (letra)
				for(int x=0;x<getAlfabeto().length;x++) { //Loop para analisar o alfabeto
					if(getMensagem().charAt(i)==getAlfabeto()[x]) { //Caso encontre a letra no alfabeto
						if(x<getChave()) { //Chave negativa
							//Mensagem encriptada na posi��o i recebe a letra do alfabeto na posi��o:
							//Resto da divis�o entre x (posi��o dentro do alfabeto)-chave+TamanhoDoAlfabeto pelo tamanho do alfabeto.
							//PS: adicionado o TamanhoDoAlfabeto a equa��o, para trabalhar corretamente com a chave negativa.
							msgEncriptada[i]=getAlfabeto()[(x-getChave()
									+getAlfabeto().length)%getAlfabeto().length];
							//x vira tamanho do alfabeto (reset)
							x=getAlfabeto().length;
						} else { //Chave positiva
							//Mensagem encriptada na posi��o i recebe a letra do alfabeto na posi��o:
							//Resto da divis�o entre x (posi��o dentro do alfabeto)+chave pelo tamanho do alfabeto.
							msgEncriptada[i]=getAlfabeto()[(x-getChave())%getAlfabeto().length];
							//x vira tamanho do alfabeto (reset)
							x=getAlfabeto().length;
						}
					} else {//Caso seja um s�mbolo/caractere especial
						msgEncriptada[i]=getMensagem().charAt(i); //Mantem-se sem criptografia
					}
				}
			}
		}
		//Ap�s decriptografia, constru��o da string para ser passada como retorno do m�todo.
		StringBuilder criadorDeString = new StringBuilder();
		criadorDeString.append(msgEncriptada);
		String txt = criadorDeString.toString();
		return txt;
		
	}
}
