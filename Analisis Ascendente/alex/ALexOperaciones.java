package alex;

import asint.ClaseLexica;

public class ALexOperaciones {
	
	private AnalizadorLexicoJLex alex;

  	public ALexOperaciones(AnalizadorLexicoJLex alex) {
   		this.alex = alex;   
  	}

	public UnidadLexica unidadVar() {
     switch(alex.lexema().toString()) {
         case "true":  
            return new UnidadLexica(alex.fila(),ClaseLexica.TRUE,"true");
         case "false":    
            return new UnidadLexica(alex.fila(),ClaseLexica.FALSE,"false");
         case "and":    
             return new UnidadLexica(alex.fila(),ClaseLexica.AND,"and");            
         case "or":    
             return new UnidadLexica(alex.fila(),ClaseLexica.OR,"or"); 
         case "not":    
             return new UnidadLexica(alex.fila(),ClaseLexica.NOT,"not"); 
         case "num":
        	 return new UnidadLexica(alex.fila(),ClaseLexica.NUM,"num");
         case "bool":
        	 return new UnidadLexica(alex.fila(),ClaseLexica.BOOL,"bool");
         default:    
        	 return new UnidadLexica(alex.fila(),ClaseLexica.VAR,alex.lexema());     
      }
    }    

	public UnidadLexica unidadMay() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAY,">");     
	}
	   
	public UnidadLexica unidadMayi() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAYI,">=");     
	}
	   
	public UnidadLexica unidadDist() {
		return new UnidadLexica(alex.fila(),ClaseLexica.DIST,"!=");     
	}
	   
	public UnidadLexica unidadMen() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MEN,"<");     
	}  
	   
	public UnidadLexica unidadMeni() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MENI,"<=");     
	}
	   
	public UnidadLexica unidadIgual() {
		return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL,"==");     
	}
   
	public UnidadLexica unidadPap() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PAP,"(");     
	}
   
	public UnidadLexica unidadPci() {
		return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE,")");     
	}
   
	public UnidadLexica unidadMas() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MAS,"+");     
	}    
   
	public UnidadLexica unidadMenos() {
		return new UnidadLexica(alex.fila(),ClaseLexica.MENOS,"-");     
	}    
   
	public UnidadLexica unidadPor() {
		return new UnidadLexica(alex.fila(),ClaseLexica.POR,"*");     
	}
   
	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(),ClaseLexica.DIV,"/");     
	}	   
   
	public UnidadLexica unidadNumR() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NUMR,alex.lexema());     
	}
   
	public UnidadLexica unidadAsig() {
    	return new UnidadLexica(alex.fila(),ClaseLexica.ASIG,"=");     
	}
   
	public UnidadLexica unidadNxt() {
		return new UnidadLexica(alex.fila(),ClaseLexica.NXT,";");     
	}
   
	public UnidadLexica unidadSec() {
		return new UnidadLexica(alex.fila(),ClaseLexica.SEC,"&&");     
	}
   
	public UnidadLexica unidadEOF() {
		return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>");     
	}

}
