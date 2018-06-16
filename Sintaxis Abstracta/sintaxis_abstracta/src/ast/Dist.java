package ast;

public class Dist extends EBin {
   public Dist(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.DIST;}
   public String toString() {return "dist("+opnd1().toString()+","+opnd2().toString()+")";}
}
