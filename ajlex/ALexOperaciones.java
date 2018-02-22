package ajlex;

public class ALexOperaciones {
	private AnalizadorLexicoJLex alex;

  	public ALexOperaciones(AnalizadorLexicoJLex alex) {
   		this.alex = alex;   
  	}

	private UnidadLexica unidadVar() {
     switch(lex.toString()) {
         case "true":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TRUE);
         case "false":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.FALSE);
         case "and":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND);            
         case "or":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR); 
         case "not":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT); 
         case "num":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NUM);
         case "bool":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL);
         default:    
        	 return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.VAR,lex.toString());     
      }
    }    

	private UnidadLexica unidadMay() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAY);     
	}
	   
	private UnidadLexica unidadMayi() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAYI);     
	}
	   
	private UnidadLexica unidadDist() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIST);     
	}
	   
	private UnidadLexica unidadMen() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MEN);     
	}  
	   
	private UnidadLexica unidadMeni() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENI);     
	}
	   
	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);     
	}
   
	private UnidadLexica unidadPap() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
	}
   
	private UnidadLexica unidadPci() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCI);     
	}
   
	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);     
	}    
   
	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOS);     
	}    
   
	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.POR);     
	}
   
	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
	}	   
   
	private UnidadLexica unidadNumR() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.NUMR,lex.toString());     
	}
   
	private UnidadLexica unidadAsig() {
    	return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ASIG);     
	}
   
	private UnidadLexica unidadNxt() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NXT);     
	}
   
	private UnidadLexica unidadSec() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.SEC);     
	}
   
	private UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
	}

	public void error() {
 		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	}

}
