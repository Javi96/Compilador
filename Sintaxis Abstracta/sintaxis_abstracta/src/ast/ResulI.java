package ast;

public class ResulI {

	private String id;
	private E exp;
	
	public ResulI(String id, E exp) {
		this.id = id;
		this.exp = exp;
	}
	
	public E exp() {
		return exp;
	}
	
	public String id() {
		return id;
	}
}
