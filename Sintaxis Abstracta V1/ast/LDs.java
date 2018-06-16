package ast;

public abstract class LDs {
   public abstract TipoLDs tipo();
   public LDs decs() {throw new UnsupportedOperationException("decs");}
   public String tipo() {throw new UnsupportedOperationException("tipo");}  
   public String id() {throw new UnsupportedOperationException("id");}           
}
