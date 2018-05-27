package ast;

public class DCompuesta extends LDs {
   private String tipo;
   private String id;

   public DCompuesta(String tipo, String id) {
     this.tipo = tipo;
     this.id = id;
   }
   
   public String type() {return tipo;}
   public String id() {return id;}
   public TipoLDs tipo() {return TipoLDs.DCOMPUESTA;} 
}
