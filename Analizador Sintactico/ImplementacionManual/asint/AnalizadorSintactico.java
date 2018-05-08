package asint;

import ajlex.UnidadLexica;
import ajlex.AnalizadorLexicoJLex;
import ajlex.ClaseLexica;
import errors.GestionErrores;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintactico {
	
	private UnidadLexica anticipo;
	private AnalizadorLexicoJLex alex;
	private GestionErrores errores;
	
	public AnalizadorSintactico(Reader input) {
		errores = new GestionErrores();
		alex = new AnalizadorLexicoJLex(input);
		alex.fijaGestionErrores(errores);
		sigToken();
	}
	
	public void S() {
		Programa();
		empareja(ClaseLexica.EOF);
	}
	
	private void Programa() {
		switch(anticipo.clase()) {
		case NUM:
			LDs();
			empareja(ClaseLexica.SEC);
			LIs();
			break;
		case BOOL:
			LDs();
			empareja(ClaseLexica.SEC);
			LIs();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void LDs() {
		switch(anticipo.clase()) {
		case NUM: case BOOL:
			Declaracion();
			RLDs();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void RLDs() {
		switch(anticipo.clase()) {
		case NXT:
			empareja(ClaseLexica.NXT);
			Declaracion();
			RLDs();
			break;
		case SEC:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NXT);
		}
	}
	
	private void Declaracion() {
		switch(anticipo.clase()) {
		case NUM:
			empareja(ClaseLexica.NUM);
			empareja(ClaseLexica.VAR);
			break;
		case BOOL:
			empareja(ClaseLexica.BOOL);
			empareja(ClaseLexica.VAR);
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void LIs() {
		switch(anticipo.clase()) {
		case VAR:
			Instruccion();
			RLIs();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.VAR);
		}
	}
	
	private void RLIs() {
		switch(anticipo.clase()) {
		case NXT:
			empareja(ClaseLexica.NXT);
			Instruccion();
			RLIs();
			break;
		case EOF:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NXT);
		}
	}
	
	private void Instruccion() {
		switch(anticipo.clase()) {
		case VAR:
			empareja(ClaseLexica.VAR);
			empareja(ClaseLexica.ASIG);
			EXP0();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void EXP0(){
		switch(anticipo.clase()) {
		case MENOS: case NOT: case NUMR: case VAR: case TRUE: case FALSE: case PAP:
			EXP1();
			R0();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MENOS, ClaseLexica.NOT,
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void R0() {
		switch(anticipo.clase()) {
		case MAS: case MENOS:
			OP0();
			EXP1();
			R0();
			break;
		case PCI: case NXT: case EOF:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MAS, ClaseLexica.MENOS, 
				ClaseLexica.PCI,ClaseLexica.NXT,ClaseLexica.EOF);
		}
	}
	
	private void EXP1(){
		switch(anticipo.clase()) {
		case MENOS: case NOT: case NUMR: case VAR: case TRUE: case FALSE: case PAP:
			EXP2();
			R1();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MENOS, ClaseLexica.NOT,
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void R1() {
		switch(anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			EXP2();
			R1();
			break;
		case OR:
			empareja(ClaseLexica.OR);
			EXP2();
			break;
		case PCI: case MAS: case MENOS: case NXT: case EOF:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.AND, ClaseLexica.OR,ClaseLexica.MAS, ClaseLexica.MENOS, 
				ClaseLexica.PCI,ClaseLexica.NXT,ClaseLexica.EOF);
		}
	}
	
	private void EXP2(){
		switch(anticipo.clase()) {
		case MENOS: case NOT: case NUMR: case VAR: case TRUE: case FALSE: case PAP:
			EXP3();
			R2();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MENOS, ClaseLexica.NOT,
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void R2() {
		switch(anticipo.clase()) {
		case MAY: case MEN: case MAYI: case MENI: case IGUAL: case DIST:
			OP2();
			EXP3();
			R2();
			break;
		case PCI: case AND: case OR: case MAS: case MENOS: case NXT: case EOF:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.AND, ClaseLexica.OR,
				ClaseLexica.MAY, ClaseLexica.MEN, ClaseLexica.MAYI, ClaseLexica.MENI, ClaseLexica.IGUAL, ClaseLexica.DIST,
				ClaseLexica.MAS, ClaseLexica.MENOS, ClaseLexica.PCI,ClaseLexica.NXT,ClaseLexica.EOF);
		}
	}
	
	private void EXP3(){
		switch(anticipo.clase()) {
		case MENOS: case NOT: case NUMR: case VAR: case TRUE: case FALSE: case PAP:
			EXP4();
			R3();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MENOS, ClaseLexica.NOT,
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void R3() {
		switch(anticipo.clase()) {
		case POR: case DIV: 
			OP3();
			EXP4();
			R3();
			break;
		case PCI: case MAY: case MEN: case MAYI: case MENI: case IGUAL: case DIST: case AND: case OR: case MAS: case MENOS: case NXT: case EOF:
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.AND, ClaseLexica.OR,
				ClaseLexica.MAY, ClaseLexica.MEN, ClaseLexica.MAYI, ClaseLexica.MENI, ClaseLexica.IGUAL, ClaseLexica.DIST,
				ClaseLexica.MAS, ClaseLexica.MENOS, ClaseLexica.PCI,ClaseLexica.NXT,ClaseLexica.EOF);
		}
	}
	
	private void EXP4(){
		switch(anticipo.clase()) {
		case MENOS: 
			empareja(ClaseLexica.MENOS);
			EXP4();
			break;
		case NOT: 
			empareja(ClaseLexica.NOT);
			EXP5();
			break;
		case NUMR: case VAR: case TRUE: case FALSE: case PAP:
			EXP5();
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MENOS, ClaseLexica.NOT,
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void EXP5(){
		switch(anticipo.clase()) {
		case NUMR: 
			empareja(ClaseLexica.NUMR);
			break;
		case VAR: 
			empareja(ClaseLexica.VAR);
			break;
		case TRUE: 
			empareja(ClaseLexica.TRUE);
			break;
		case FALSE: 
			empareja(ClaseLexica.FALSE);
			break;
		case PAP:
			empareja(ClaseLexica.PAP);
			EXP0();
			empareja(ClaseLexica.PCI);
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(),
				ClaseLexica.NUMR,ClaseLexica.VAR,ClaseLexica.TRUE,ClaseLexica.FALSE,ClaseLexica.PAP);
		}	
	}
	
	private void OP0() {
		switch(anticipo.clase()) {
		case MAS:
			empareja(ClaseLexica.MAS);
			break;
		case MENOS:
			empareja(ClaseLexica.MENOS);
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MAS, ClaseLexica.MENOS);			
		}
	}
	
	private void OP2() {
		switch(anticipo.clase()) {
		case MAY:
			empareja(ClaseLexica.MAY);
			break;
		case MEN:
			empareja(ClaseLexica.MEN);
			break;
		case MAYI:
			empareja(ClaseLexica.MAYI);
			break;
		case MENI:
			empareja(ClaseLexica.MENI);
			break;
		case IGUAL:
			empareja(ClaseLexica.IGUAL);
			break;
		case DIST:
			empareja(ClaseLexica.DIST);
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.MAY, ClaseLexica.MEN,
				ClaseLexica.MAYI,ClaseLexica.MENI,ClaseLexica.IGUAL,ClaseLexica.DIST);			
		}
	}
	
	private void OP3() {
		switch(anticipo.clase()) {
		case POR:
			empareja(ClaseLexica.POR);
			break;
		case DIV:
			empareja(ClaseLexica.DIV);
			break;
		default: errores.errorSintactico(anticipo.fila(), anticipo.clase(), ClaseLexica.POR, ClaseLexica.DIV);			
		}
	}
	
	private void empareja(ClaseLexica esperada) {
		if (anticipo.clase() == esperada) {
			sigToken();
		}
		else {
			errores.errorSintactico(anticipo.fila(),anticipo.clase(),esperada);
		}
	}
	
	private void sigToken() {
		try {
			anticipo = alex.yylex();
		} catch(IOException e) {
			errores.errorFatal(e);
		}
	}
}
