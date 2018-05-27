package ast;

public class Mayi extends EBin {
   public Mayi(E opnd1, E opnd2) {
     super(opnd1,opnd2);  
   }     
   public TipoE tipo() {return TipoE.MAYI;}
   public String toString() {return "mayi("+opnd1().toString()+","+opnd2().toString()+")";}
}
