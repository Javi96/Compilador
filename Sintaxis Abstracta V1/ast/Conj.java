package ast;

public class Conj extends EBin {
   public Conj(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.CONJ;}
   public String toString() {return "conj("+opnd1().toString()+","+opnd2().toString()+")";}
}
