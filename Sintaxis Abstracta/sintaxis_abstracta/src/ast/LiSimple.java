package ast;

public class LiSimple extends LIs {
   private String id;
   private E exp;
   public LiSimple(String id, E exp) {
     this.id = id;
     this.exp = exp;
   }
   
   public String id() {return id;}
   public E exp() {return exp;}
   public TipoLIs tipo() {return TipoLIs.LISIMPLE;} 
   public String toString() {
	   return id + " = " + exp.toString();
   }
}