package ast;

public class May extends EBin {
   public May(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.MAY;}
   public String toString() {return "may("+opnd1().toString()+","+opnd2().toString()+")";}
}
