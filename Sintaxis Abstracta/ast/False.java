package ast;

public class False extends E {
  private String valor;
  public False(String valor) {
   this.valor = "false";   
  }
  public String val() {return valor;}
  public TipoE tipo() {return TipoE.FALSE;}
  public String toString() {return valor;}      
}
