package cup;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoJLex;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoJLex alex = new AnalizadorLexicoJLex(input);
	 Evaluador evaluador = new Evaluador(alex);
	 System.out.println(evaluador.parse().value);
	 System.out.println("Análisis terminado" );
 }
}   
   
