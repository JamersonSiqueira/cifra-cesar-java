package control;
// Fig. 15.3: CreateTextFile.java
// Writing data to a sequential text file with class Formatter.
import java.io.FileNotFoundException;     
import java.lang.SecurityException;
import java.nio.file.Path;
import java.util.Formatter;               
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;                 

public class CriaArquivoDeTexto
{
   private Formatter output; 
   private Path caminho;
   private String conteudo;// outputs text to a file       

   public CriaArquivoDeTexto(Path caminho,String conteudo) {
	   this.caminho = caminho;
	   this.conteudo = conteudo;
	   salvarArquivo();
   }
   
   
   public void salvarArquivo()
   {
      try
      {
         output = new Formatter(this.caminho.toString()); // abre arquivo
      }
      catch (SecurityException securityException)
      {
         System.err.println("Permissao de escrita negada. Terminando.");
         System.exit(1); //encerra o programa
      } 
      catch (FileNotFoundException fileNotFoundException)
      {
         System.err.println("Erro ao abrir o arquivo. Termindo.");
         System.exit(1); //encerra o programa
      } 
      adicionarRegistros();
   } 

   // adiciona registros ao arquivo
   public void adicionarRegistros()
   {
         try
         {
            // saida do novo registro ara o arquivo; assume que as entradas foram validas
            output.format(this.conteudo);                             
         } 
         catch (FormatterClosedException formatterClosedException)
         {
            System.err.println("Erro escrevendo no arquivo. Terminando.");
         } 
         catch (NoSuchElementException elementException)
         {
            System.err.println("Entrada invalida. Por favor tente novamente.");  
         }
         
         fecharArquivo();
}
   

   // fecha arquivos
   public void fecharArquivo()
   {
      if (output != null)
         output.close();
   } 
} // fim da classe


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