package ast;

public class DSimple extends LDs {
   private String tipo;
   private String id;

   public DSimple(String tipo, String id) {
     this.tipo = tipo;
     this.id = id;
   }
   
   public String type() {return tipo;}
   public String id() {return id;}
   public TipoLDs tipoD() {return TipoLDs.DSIMPLE;} 
}
