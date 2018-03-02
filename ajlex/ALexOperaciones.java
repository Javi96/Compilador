package ajlex;

public class ALexOperaciones {
	
	private AnalizadorLexicoJLex alex;

  	public ALexOperaciones(AnalizadorLexicoJLex alex) {
   		this.alex = alex;   
  	}

	public UnidadLexica unidadVar() {
     switch(alex.lexema().toString()) {
         case "true":  
            return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.TRUE);
         case "false":    
            return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.FALSE);
         case "and":    
             return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.AND);            
         case "or":    
             return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.OR); 
         case "not":    
             return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NOT); 
         case "num":
        	 return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NUM);
         case "bool":
        	 return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.BOOL);
         default:    
        	 return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.VAR,alex.lexema().toString());     
      }
    }    

	public UnidadLexica unidadMay() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAY);     
	}
	   
	public UnidadLexica unidadMayi() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYI);     
	}
	   
	public UnidadLexica unidadDist() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIST);     
	}
	   
	public UnidadLexica unidadMen() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MEN);     
	}  
	   
	public UnidadLexica unidadMeni() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENI);     
	}
	   
	public UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL);     
	}
   
	public UnidadLexica unidadPap() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PAP);     
	}
   
	public UnidadLexica unidadPci() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCI);     
	}
   
	public UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAS);     
	}    
   
	public UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOS);     
	}    
   
	public UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.POR);     
	}
   
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIV);     
	}	   
   
	public UnidadLexica unidadNumR() {
		return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.NUMR,alex.lexema().toString());     
	}
   
	public UnidadLexica unidadAsig() {
    	return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.ASIG);     
	}
   
	public UnidadLexica unidadNxt() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NXT);     
	}
   
	public UnidadLexica unidadSec() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SEC);     
	}
   
	public UnidadLexica unidadEOF() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF);     
	}

	public void error() {
 		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	}

}
