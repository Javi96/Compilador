package ast;

public class Programa extends S {
   private LDs decs;
   private LIs lis;
   public Programa(LDs decs, LIs lis) {this.decs = decs;this.lis=lis;}
   public LDs decs() {return decs;}
   public LIs lis() {return lis;}
   public TipoS tipo(){return TipoS.PROGRAMA;} 
   public String toString() {
	   return decs.toString() + " && " + lis.toString();
   }
}