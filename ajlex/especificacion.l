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
dig = [0-9]
var = {letra}({letra}|{dig}|[_])*
num = num
bool = bool
true = true
false = false
and = and
or = or
not = not
signo = [\+,\-]
entero = {dig}{dig}*
parteEntera = {signo}?{entero}
dec = \.{entero}
ex = (E|e){parteEntera}
numr = ({parteEntera}|{parteEntera}{dec}|{parteEntera}{ex}|{parteEntera}{dec}{ex}|)
mas = \+
menos = \-
por = \*
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
{separador}         {}
{var}               {return ops.unidadVar();}
{may}               {return ops.unidadMay();}
{mayi}           	{return ops.unidadMayi();}
{men}            	{return ops.unidadMen();}
{meni}              {return ops.unidadMeni();}
{dist}            	{return ops.unidadDist();}
{igual}           	{return ops.unidadIgual();}
{pap}  			 	{return ops.unidadPap();}
{pci}        		{return ops.unidadPci();}
{mas}     			{return ops.unidadMas();}
{menos}        		{return ops.unidadMenos();} 
{por}               {return ops.unidadPor();} 
{div}               {return ops.unidadDiv();}
{numr}              {return ops.unidadNumR();}
{asig}              {return ops.unidadAsig();}
{nxt}               {return ops.unidadNxt();}
{sec}               {return ops.unidadSec();}
[^]                 {ops.error();}  