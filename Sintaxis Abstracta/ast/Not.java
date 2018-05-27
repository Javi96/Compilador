package ast;

public class Not extends EUn {
   public Not(E opnd1) {
     super(opnd1);  
   }     
   public TipoE tipo() {return TipoE.NOT;}
   public String toString() {return "not("+opnd1().toString()+")";}
}
