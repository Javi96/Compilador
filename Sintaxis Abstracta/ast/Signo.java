package ast;

public class Signo extends EUn {
   public Signo(E opnd1) {
     super(opnd1);  
   }     
   public TipoE tipo() {return TipoE.SIGNO;}
   public String toString() {return "signo("+opnd1().toString()+")";}
}
