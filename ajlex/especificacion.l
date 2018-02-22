package ajlex;

%%
%line
%class AnalizadorLexicoJLex
%type  UnidadLexica
%unicode

%{
  private ALexOperaciones ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
%}

%eofval{
  return ops.unidadEOF();
%eofval}

%init{
  ops = new ALexOperaciones(this);
%init}

sec = ([&][&])
letra  = ([A-Z]|[a-z])
digito = [0-9]
var = {letra}({letra}|{digito}|[_])*
num = num
bool = bool
true = true
false = false
_and = _and
_or = _or
_not = _not
signo = [\+,\-]
dec = [\.]({dig}{dig}*)
ex = [e,E]({signo}?)({dig}{dig}*)
numr = {signo}?({dig}{dig}*)({dec}?)({ex}?)
mas = \+
menos = \-
por \*
div = /
may = >
men = <
mayi = ([>][=])
meni = ([<][=])
igual = ([=][=])
dist = ([!][=])
asig = \=
nxt  = \;
pap = \(
pci = \)
separador = [ \t\r\b\n]
%%
{separador}               {}
{num}                  {return ops.unidadEvalua();}
{donde}                   {return ops.unidadDonde();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
[^]                       {ops.error();}  