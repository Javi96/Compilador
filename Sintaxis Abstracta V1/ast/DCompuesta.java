package ast;

public class DCompuesta extends LDs {
   private String tipo;
   private String id;
   private LDs decs;
   public DCompuesta(LDs decs,String tipo, String id) {
     this.decs = decs;  
     this.tipo = tipo;
     this.id = id;
   }
   
   public LDs decs() {return decs;}
   public String type() {return tipo;}
   public String id() {return id;}
   public TipoLDs tipo() {return TipoLDs.DCOMPUESTA;} 
}
