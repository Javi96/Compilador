package ast;

public abstract class EUn extends E {
   private E opnd1;

   public EBin(E opnd1) {
     this.opnd1 = opnd1;
   }

   public E opnd1() {return opnd1;}
     
}
