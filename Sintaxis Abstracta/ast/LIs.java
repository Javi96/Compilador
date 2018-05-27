package ast;

public abstract class LIs  {
   public abstract TipoLIs tipo();
   public LIs lis() {throw new UnsupportedOperationException("lis");} 
   public String id() {throw new UnsupportedOperationException("id");}           
   public E exp() {throw new UnsupportedOperationException("exp");}  
}
