package javaCC;

import java.io.FileReader;
public class Main{
			
   public static void main(String[] args) throws Exception {
      Evaluador eval = new Evaluador(new FileReader(args[0]));
      System.out.println(eval.Sp());
      System.out.println("An�lisis terminado" );
   }  
}