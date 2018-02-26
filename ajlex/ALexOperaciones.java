package ajlex;

public class ALexOperaciones {
	private AnalizadorLexicoJLex alex;

  	public ALexOperaciones(AnalizadorLexicoJLex alex) {
   		this.alex = alex;   
  	}

	private UnidadLexica unidadVar() {
     switch(alex.lex.toString()) {
         case "true":  
            return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.TRUE);
         case "false":    
            return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.FALSE);
         case "and":    
             return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.AND);            
         case "or":    
             return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.OR); 
         case "not":    
             return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.NOT); 
         case "num":
        	 return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.NUM);
         case "bool":
        	 return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.BOOL);
         default:    
        	 return new UnidadLexicaMultivaluada(alex.filaInicio,ClaseLexica.VAR,alex.lex.toString());     
      }
    }    

	private UnidadLexica unidadMay() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MAY);     
	}
	   
	private UnidadLexica unidadMayi() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MAYI);     
	}
	   
	private UnidadLexica unidadDist() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.DIST);     
	}
	   
	private UnidadLexica unidadMen() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MEN);     
	}  
	   
	private UnidadLexica unidadMeni() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MENI);     
	}
	   
	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.IGUAL);     
	}
   
	private UnidadLexica unidadPap() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.PAP);     
	}
   
	private UnidadLexica unidadPci() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.PCI);     
	}
   
	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MAS);     
	}    
   
	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.MENOS);     
	}    
   
	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.POR);     
	}
   
	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.DIV);     
	}	   
   
	private UnidadLexica unidadNumR() {
		return new UnidadLexicaMultivaluada(alex.filaInicio,ClaseLexica.NUMR,alex.lex.toString());     
	}
   
	private UnidadLexica unidadAsig() {
    	return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.ASIG);     
	}
   
	private UnidadLexica unidadNxt() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.NXT);     
	}
   
	private UnidadLexica unidadSec() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.SEC);     
	}
   
	private UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(alex.filaInicio,ClaseLexica.EOF);     
	}

	public void error() {
 		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	}

}
