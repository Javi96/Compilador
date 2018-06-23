package javaCC;

import java.io.FileReader;

import ast.*;

public class Main{
   public static void main(String[] args) throws Exception {
      Evaluador eval = new Evaluador(new FileReader(args[0]));
      //System.out.println(eval.Sp());
      Programa ast = (Programa) eval.Sp();
      LDs lds = ast.decs();
      LIs lis = ast.lis();
      System.out.println(ast);
   }
}