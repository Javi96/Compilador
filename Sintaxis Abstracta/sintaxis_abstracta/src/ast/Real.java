package ast;

public class Real extends E {
  private String valor;
  public Real(String valor) {
   this.valor = valor;   
  }
  public String val() {return valor;}
  public TipoE tipo() {return TipoE.REAL;}
  public String toString() {return valor;}      
}
