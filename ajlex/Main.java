package ajlex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
	    AnalizadorLexicoJLex al = new AnalizadorLexicoJLex(input);
	    UnidadLexica unidad;
	    do {
	    	unidad = al.yylex();
	    	System.out.println(unidad);
	    }
	    while (unidad.clase() != ClaseLexica.EOF);
	}

}
