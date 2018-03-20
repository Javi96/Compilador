package errors;

import ajlex.ClaseLexica;

public class GestionErrores {
	
	public void errorLexico(int fila, String lexema) {
		System.out.println("ERROR fila " + fila + ": Caracter inesperado: " + lexema);
		System.exit(1);
	}
	
	public void errorSintactico(int fila, ClaseLexica encontrada, ClaseLexica ... esperadas) {
		System.out.println("ERROR fila " + fila + ": Encontrado " + encontrada);
		System.out.print("Se esperaba: ");
		for (ClaseLexica esperada: esperadas) {
			System.out.print(esperada + " ");
		}
		System.out.println();
		System.exit(1);
	}
	
	public void errorFatal(Exception e) {
		System.out.println(e);
		e.printStackTrace();
		System.exit(1);
	}

}
