package ast;

public class Men extends EBin {
   public Men(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.MEN;}
   public String toString() {return "men("+opnd1().toString()+","+opnd2().toString()+")";}
}
