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
    * R_AMP : R_&
    * */
   private static enum Estado {
    INICIO, R_PAP, R_PCI, R_ASIG, R_IGUAL, R_MEN,
    R_MENI, R_MAY, R_MAYI, R_EXL,R_DIST,R_VAR,
    R_FIN,R_AMP,R_SEC,R_POR,R_DIV,R_MAS,R_MENOS,
    R_ENT,R_P,R_DEC,R_E,R_DIG,R_EX,R_AND,R_NOT,R_OR
   }

   private Estado estado;

   public AnalizadorLexico(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   /*	NOTA:
    * 		Hay muchos casos en los que devuelvo error y en otros la unidad en cuestion, no se 
    * 		cual de las dos maneras esta bien. Hay que revisar esto. Tambien uso mucho lo de 
    * 		unidadId,pero no se si eso esta bien.
    * 
    * 	NOTA2:
    * 		Hay que revisar las clases lexicas, creo que sobran algunas y puede que falten otras.
    * */
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INICIO;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) {
		case INICIO:
			//SI PCI -> TRANSITA R_PCI
			if(hayPCierre()) transita(Estado.R_PCI);		
			//SI PAP -> TRANSITA R_PAP
			else if(hayPAp()) transita(Estado.R_PAP);
			//SI ASIG -> TRANSITA R_ASIG
			else if(hayAsignacion()) transita(Estado.R_ASIG);
			//SI MEN -> TRANSITA R_MEN
			else if(hayMenor()) transita(Estado.R_MEN);
			//SI MAY -> TRANSITA R_MAY
			else if(hayMayor()) transita(Estado.R_MAY);
			//SI ! -> TRANSITA R_EXL
			else if(hayExcl()) transita(Estado.R_EXL);
			//SI LETRA -> TRANSITA R_VAR
			else if(hayLetra()) transita(Estado.R_VAR);
			//SI EOF -> TRANSITA R_FIN
			else if(hayEOF()) transita(Estado.R_FIN);
			//SI & -> TRANSITA R_AMP
			else if(hayAmp()) transita(Estado.R_AMP);
			//SI * -> TRANSITA R_POR
			else if(hayMul()) transita(Estado.R_POR);
			//SI / -> TRANSITA R_DIV
			else if(hayDiv()) transita(Estado.R_DIV);
			//SI + -> TRANSITA R_MAS
			else if(haySuma()) transita(Estado.R_MAS);
			//SI - -> TRANSITA R_MENOS
			else if(hayResta()) transita(Estado.R_MENOS);
			//SI SEP -> TRANSITA INICIO
			else if(haySep()) transitaIgnorando(Estado.INICIO);
			//SI ENT -> TRANSITA R_ENT
			else if(hayDigito()) transita(Estado.R_ENT);
			//si no es ninguna de las anteriores,deberia dar error
			else error();
			break;
		case R_AMP:
			if(hayAmp()) transita(Estado.R_SEC);
			else error();
			break;
		case R_AND:
			//no se muy bien que hacer aqui
			break;
		case R_ASIG:
			if(hayAsignacion()) transita(Estado.R_IGUAL);
			return unidadAsig();
		case R_DEC:
			if(hayDigito()) transita(Estado.R_DEC);
			else if(hayExponente()) transita(Estado.R_EX);
			else error();
			break;
		case R_DIG:
			if(hayDigito()) transita(Estado.R_EX);
			else error();
			break;
		case R_DIST:
			return unidadId();
		case R_DIV:
			return unidadDiv();
		case R_E:
			if(haySuma() || hayResta()) transita(Estado.R_DIG);
			else error();
			break;
		case R_ENT:
			if(hayDigito()) transita(Estado.R_ENT);
			else if(hayPunto()) transita(Estado.R_P);
			else if(hayExponente()) transita(Estado.R_E);
			else error();
			break;
		case R_EX:
			return unidadEx();
		case R_EXL:
			if(hayAsignacion()) transita(Estado.R_DIST);
			else error();
			break;
		case R_FIN:
			return unidadId();
		case R_IGUAL:
			return unidadId();
		case R_MAS:
			if(hayDigito()) transita(Estado.R_ENT);
			return unidadMas();
		case R_MAY:
			if(hayAsignacion()) transita(Estado.R_MAYI);
			return unidadMay();
		case R_MAYI:
			return unidadId();
		case R_MEN:
			if(hayAsignacion()) transita(Estado.R_MENI);
			else unidadMen();
			break;
		case R_MENI:
			return unidadId();
		case R_MENOS:
			if(hayDigito()) transita(Estado.R_ENT);
			else unidadMenos();
			break;
		case R_NOT:
			//no se muy bien que hacer aqui
			break;
		case R_OR:
			//no se muy bien que hacer aqui
			break;
		case R_P:
			if(hayDigito()) transita(Estado.R_DEC);
			else error();
			break;
		case R_PAP:
			return unidadPap();
		case R_PCI:
			return unidadPci();
		case R_POR:
			return unidadPor();
		case R_SEC:
			return unidadId();
		case R_VAR:
			return unidadVar();
		default:
			error();
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
   private boolean hayExcl() {return sigCar == '!';}
   private boolean hayAmp() {return sigCar == '&';}
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
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n' || sigCar == '\r' 
		   							|| sigCar == '\b';}
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
         case "-1":
        	 return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);
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