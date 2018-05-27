package ast;

public class True extends E {
  private String valor;
  public True(String valor) {
   this.valor = "true";   
  }
  public String val() {return valor;}
  public TipoE tipo() {return TipoE.TRUE;}
  public String toString() {return valor;}      
}
