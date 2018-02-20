package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexico {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   /*
    * INCIO : estado en el que comienza el automata.
    * NADA : estado intermedio en el que no ocurre nada.
    * FIN : estado especial para cuando se recibe EOF.
    * */
   private static enum Estado {
    INICIO, NADA, PAP, PCI, ASIG, IGUAL, MEN,
    MENI, MAY, MAYI, DIST, MAS, ENT, DEC, EX, SEC,
    DIV, POR, NOT, OR, AND, FALSE, NUM, TRUE, VAR, FIN
   }

   private Estado estado;

   public AnalizadorLexico(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INICIO;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) {
		case AND:
			break;
		case ASIG:
			break;
		case DEC:
			break;
		case DIST:
			if (hayDigito()) transita(Estado.ENT);
			break;
		case DIV:
			return unidadDiv();
		case ENT:
			if (hayDigito()) transita(Estado.ENT);
            else if(haySuma()) transita(Estado.MAS);
            else if(hayResta()) transita(Estado.MEN);
            else if(hayMul()) transita(Estado.POR);
            else if(hayDiv()) transita(Estado.DIV);
            else if(hayLetra()) transita(Estado.VAR);
			break;
		case EX:
            if (hayDigito()) transita(Estado.ENT);
            else if(haySuma()) transita(Estado.MAS);
            else if(hayResta()) transita(Estado.MEN);
            else return unidadEx();
			break;
		case FALSE:
			//AND OR NOT COMPARADORES 
			break;
		case FIN:
			break;
		case IGUAL:
			break;
		case INICIO:
			if(hayLetra()) transita(Estado.VAR);
			else if (hayExponente()) transitaIgnorando(Estado.EX); //No hay, daria un error
			else if (hayDigito()) transita(Estado.ENT);
			else if (hayPunto()) transitaIgnorando(Estado.DEC);//No hay, daria un error
			else if (haySuma()) transita(Estado.MAS);
			else if (hayResta()) transita(Estado.MEN);
			else if (hayMul()) transita(Estado.POR);
			else if (hayDiv()) transita(Estado.DIV);
			else if (hayPAp()) transita(Estado.PAP);
			else if (hayPCierre()) transita(Estado.PCI);
			else if (hayMayor()) transita(Estado.MAY);
			else if (hayMenor()) transita(Estado.MEN);
			else if (hayAsignacion()) transita(Estado.ASIG);
			else if (haySep()) transitaIgnorando(Estado.INICIO);
			else if (hayNL()) transitaIgnorando(Estado.INICIO);
			else if (hayEOF()) transita(Estado.FIN);
			else return unidadId();//palabra reservada(+ de un caracter)
			break;
		case MAS:
            if (hayDigito()) transita(Estado.ENT);
            else return unidadMas();
            break;
		case MAY:
			if(hayAsignacion()) transita(Estado.MAYI);
			else return unidadMay();
			break;
		case MAYI:
			return unidadId();
		case MEN:
			if(hayAsignacion()) transita(Estado.MENI);
			else return unidadMay();
			break;
		case MENI:
			return unidadId();
		case NADA:
			break;
		case NOT:
			break;
		case NUM:
			break;
		case OR:
			break;
		case PAP:
			return unidadPap();
		case PCI:
			return unidadPci();
		case POR:
			return unidadPor();
		case SEC:
			break;
		case TRUE:
			break;
		case VAR:
			break;
		default:
			break;
         }
     }    
   }
   private void transita(Estado sig) throws IOException {
     lex.append((char)sigCar);
     sigCar();         
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException {
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException {
     sigCar = input.read();
     if (sigCar == NL.charAt(0)) saltaFinDeLinea();
     if (sigCar == '\n') {
        filaActual++;
        columnaActual=0;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error();
      }
      sigCar = '\n';
   }
   
   /*SIMBOLOS*/
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||sigCar >= 'A' && sigCar <= 'Z';}
   private boolean hayExponente() {return sigCar == 'e' || sigCar == 'E';}
   private boolean hayDigito() {return sigCar >= '0' && sigCar <= '9';}
   private boolean hayPunto() {return sigCar == '.';}
   /*OPERADORES*/
   private boolean haySuma() {return sigCar == '+';}
   private boolean hayResta() {return sigCar == '-';}
   private boolean hayMul() {return sigCar == '*';}
   private boolean hayDiv() {return sigCar == '/';} 
   /*MODIFICADORES DE PRIORIDADES*/
   private boolean hayPAp() {return sigCar == '(';}
   private boolean hayPCierre() {return sigCar == ')';} 
   /*COMPARADORES*/
   private boolean hayMayor() {return sigCar == '>';}
   private boolean hayMenor() {return sigCar == '<';}
   /*ASIGNACIONES*/
   private boolean hayAsignacion() {return sigCar == '=';}
   /*ESPECIALES*/ 
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
   private boolean hayNL() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
   private boolean hayEOF() {return sigCar == -1;}
   
   private UnidadLexica unidadId() {
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
         case ">=":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAYI);
         case "<=":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENI);
         case "==":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);
         case "!=":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIST);             
         case "&&":    
             return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.SEC);           
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IDEN,lex.toString());     
      }
   }    

   private UnidadLexica unidadMay() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAY);     
   }
   private UnidadLexica unidadEx() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EX);     
   }
   private UnidadLexica unidadMen() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MEN);     
   }   
   private UnidadLexica unidadPap() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
   }
   private UnidadLexica unidadPci() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCI);     
   }
   private UnidadLexica unidadEof() {
	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
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
   private UnidadLexica unidadEnt() {
	     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());     
   }
   private UnidadLexica unidadNum() {
	     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.NUM,lex.toString());     
   }
   private UnidadLexica unidadBool() {
	     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL,lex.toString());     
   }
   private UnidadLexica unidadVar() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.VAR,lex.toString());    
   }    
   private UnidadLexica unidadAsig() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.ASIG);     
   }
    
   private void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado");  
     System.exit(1);
   }

   public static void main(String arg[]) throws IOException {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexico al = new AnalizadorLexico(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}