package ast;

public class LiCompuesta extends LIs {
   private String id;
   private E exp;
   public LiCompuesta(String id, E exp) {
     this.id = id;
     this.exp = exp;
   }
   
   public String id() {return id;}
   public E exp() {return exp;}
   public TipoLIs tipo() {return TipoLIs.LISIMPLE;} 
}
