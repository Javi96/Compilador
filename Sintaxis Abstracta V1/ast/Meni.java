package ast;

public class Meni extends EBin {
   public Meni(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.MENI;}
   public String toString() {return "meni("+opnd1().toString()+","+opnd2().toString()+")";}
}
