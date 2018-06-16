package ast;

public class Id extends E {
  private String id;
  public Id(String id) {
   this.id = id;   
  }
  public String val() {return id;}
  public TipoE tipo() {return TipoE.ID;}
  public String toString() {return id;}      
}
