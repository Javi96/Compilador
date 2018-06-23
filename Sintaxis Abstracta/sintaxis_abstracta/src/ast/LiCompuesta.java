package ast;

public class LiCompuesta extends LIs {
   private String id;
   private E exp;
   private LIs lis;
   public LiCompuesta(LIs lis,String id, E exp) {
     this.lis = lis;  
     this.id = id;
     this.exp = exp;
   }
   
   public LIs lis() {return lis;}
   public String id() {return id;}
   public E exp() {return exp;}
   public TipoLIs tipo() {return TipoLIs.LICOMPUESTA;} 
   public String toString() {
	   return lis.toString() + ";" + id + " = " + exp.toString();
   }
}
