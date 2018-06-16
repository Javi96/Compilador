package ast;

public class Disy extends EBin {
   public Disy(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.DISY;}
   public String toString() {return "disy("+opnd1().toString()+","+opnd2().toString()+")";}
}
