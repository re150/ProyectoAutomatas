package automata2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//CONSIDERACIONES:
//COMENTARIO MULTILINEA ERRONEA PRODUCE UN SOLO ERROR E IGNORA EL RESTO DEL TEXTO A ANALIZAR
//COMILLAS DE STRING FUNCIONA COMO EN UN LENGUAJE DE PROGRAMACION NORMAL
//SI NO HAY ETS AL FINAL DEL ARCHIVO EL CODIGO PETA NO SE POR QUE

//TO DO: 
//MODIFICAR EL DIAGRAMA DE ESTADOS PARA QUE QUEDE DE ACUERDO AL CODIGO
//AÑADIR FUNCIONALIDAD A LA CLASE EDITOR
//TESTEAR DIFERENTES BUGS QUE PUEDA HABER

public class Automata {
	private char[] entrada;
	private int c = 0;
	private char a;
	
	public int errores = 0;
	public int numEnt = 0;
	public int numDec = 0;
	public int opAr = 0;
	public int opLog = 0;
	public int opRel = 0;
	public int comLin = 0;
	public int comMul = 0;
	public int cadCar = 0;
	public int asign = 0;
	public int identif = 0;
	public int palabRes = 0;
	public int parentesis = 0;
	public int llaves = 0;
	
	public ArrayList<String> palabrasReservadas = 
			new ArrayList<>(Arrays.asList(
			  "if", "else", "switch", "case", "default", "for", "while"
			  , "break", "int", "string", "double", "char", "print"));

	public Automata(String texto) {
		this.entrada = texto.toCharArray();
		q0();
		
	}
	
	private void error() {
		errores++;
		
		//System.out.println("El error fue: " + a);
		
		while(c<entrada.length) {
			
			if(a=='\t'||a=='\n'||a==' ') break;
			c++;
			a = entrada[c];
			
		}
		
	}



	private void q0() {
		
		while(c<entrada.length) {
		
			a = entrada[c];
			
			switch(a) {
				case '/': q22();
				break;
				
				case '=': q15();
				break;
				
				case '&': q7();
				break;
				
				case '|': q9();
				break;
				
				case '!': q11();
				break;
				
				case '"': q18();
				break;
				
				case '-': q12();
				break;
				
				default: 
					
					if(Character.isAlphabetic(a)) {
						q1();
						
					}
					
					else if(a=='\t'||a=='\n'||a==' ') {
						
					}
					
					else if(a=='%' || a == '*' || a == '+') {
						q2();
					}
					
					else if(a=='<' || a=='>') {
						q13();
						
					}
					
					else if(Character.isDigit(a)) {
						q16();
						
					}
					
					else if(a=='(' || a == ')') {
						q20();
					}
					
					else if(a=='{' || a == '}') {
						q21();
					}
					
					else error();
					
				break;
			
			}
			
			c++;
			
			
		}
		c = 0;



		
	}

	@Override
	public String toString() {

		return "Palabras reservadas : "+  palabRes + " \n" +
				"Identificadores : " + identif + " \n" +
				"Operadores Relacionales : " +opRel + " \n" +
				"Operadores Lógicos :"+ opLog +" \n" +
				"Operadores Aritméticos :"+ opAr +" \n" +
				"Asignaciones : " +asign +" \n" +
				"Número Enteros : " + numEnt+" \n" +
				"Números Decimales : "+numDec +" \n" +
				"Cadena de caracteres :"+ cadCar+ "\n" +
				"Comentarios Multilinea: "+ comMul+"\n" +
				"Comentario de Linea : "+comLin+"\n" +
				"Paréntesis : "+parentesis+"\n" +
				"Llaves : "+llaves+" \n" +
				"Errores : " +errores;
	}
	private void q1() {//IDENTIFICADORES Y PALABRAS RESERVADAS
		a = entrada[c];
		ArrayList<Character> id = new ArrayList<>();
		StringBuilder sB = new StringBuilder();
		
		while((Character.isLetterOrDigit(a)|| a == '_')&&c<entrada.length-1) {
			id.add(a);
			c++;
			a = entrada[c];
		}
		
		for (Character caracter : id) {
            sB.append(caracter);
        }
	
		if((a=='\t'||a=='\n'||a==' ')&&palabrasReservadas.contains(sB.toString())) {
			palabRes++;
			
		}
		else if(a=='\t'||a=='\n'||a==' ') {
			identif++;
		}
		
		else error();

	}

	private void q2() {//OPERADORES ARITMETICOS
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opAr++;
		}
		else error();
		
		
	}
	
	private void q3() {//COMENTARIOS DE UNA LINEA
		
		while(c<entrada.length) {
			a = entrada[c];
			if(a == '\n') {
				comLin++;
				break;
			}
			c++;
			
		}
		
	}
	
	private void q4() {//COMENTARIOS MULTILINEA CAMBIOS EN EL DIAGRAMA NECESARIOS

		while(c<entrada.length) {
			c++;
			a = entrada[c];
			if(a=='*') {q5();
						break;}
			else if(c+1>=entrada.length) {
				error();
				break;
			}
		}

	}
	
	private void q5() {//COMENTARIOS MULTILINEA
		c++;
		a = entrada[c];
		
		if(a=='/') q6();
		else q4();
		
	}
	
	private void q6() {//COMENTARIOS MULTILINEA
		c++;
		a = entrada[c];
		if(a=='\t'||a=='\n'||a==' ') {
			comMul++;
		}
		else error();
		
	
	}
	
	private void q7() {//OPERADOR LOGICO &
		c++;
		a = entrada[c];
		
		if(a=='&') {
			q8();
		}
		else error();
		
	}
	
	private void q8() {//EVALUAR OPERADOR LOGICO & (PUEDE SER USADO GENERALMENTE PARA OTROS OPLOG)
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opLog++;
		}
		else error();
		
	}
	
	private void q9() {//OPERADOR LOGICO |
		
		c++;
		a = entrada[c];
		
		if(a=='|') {
			q10();
		}
		else error();
		
	}
	
	private void q10() {//EVALUAR OPERADOR LOGICO | (PUEDE GENERALIZARCE CON q8)
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opLog++;
		}
		else error();
	}
	
	private void q11() {//EVALUAR OPERADOR LOGICO !
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opLog++;
		}
		else if(a=='=') {
			q14();
		
		}
		else error();
		
	}
	
	private void q12() {//EVALUAR QUE HACER CON -
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opAr++;
			
		}
		
		else if(Character.isDigit(a)) {
			q16();
			
		}
		
		else error();
		
	}
	
	private void q13() {//OPERADORES RELACIONALES < >
		c++;
		a = entrada[c];
		
		if(a=='\t'||a=='\n'||a==' ') {
			opRel++;
		}
		
		else if(a=='=') q14();
		
		else error();
		
	}
	
	private void q14() {//OPERADORES RELACIONALES
		c++;
		a = entrada[c];
		if(a=='\t'||a=='\n'||a==' ') {
			opRel++;
		}
		else error();
		
	}
	
	private void q15() {//ASIGNACION
		c++;
		a = entrada[c];
		
		if(a == '=') {
			
			q14();
		}
		
		else if(a=='\t'||a=='\n'||a==' ') {
			asign++;
		}
		
		else error();
	}
	
	private void q16() {//NUMEROS ENTEROS
		a = entrada[c];
		
		while(Character.isDigit(a)&&c<entrada.length-1) {
			c++;
			a = entrada[c];
		}
		if(a=='\t'||a=='\n'||a==' ') {
			numEnt++;
			
		}
		else if(a =='.') {
			q17();
		}
		else error();
		
		
	}

	private void q17() {//NUMEROS DECIMALES
		c++;
		a = entrada[c];
		if(Character.isDigit(a)) {
			q23();
		}
		else error();


	}
	
	private void q18() {//CADENAS DE CARACTERES CAMBIO EN EL DIAGRAMA
		c++;
		a = entrada[c];
		while(c<entrada.length) {
			c++;
			a = entrada[c];
			
			if(a=='"') {
				q19();
				break;}
			
			else if(c+1>=entrada.length) {
				error();
				break;
			}
		}
		
		
	}
	
	private void q19() {//CADENAS DE CARACTERES
		c++;
		a = entrada[c];
		if(a=='\t'||a=='\n'||a==' ') {
			cadCar++;
		}
		else error();
	}
	
	private void q20() {//PARENTESIS
		parentesis++;
		c++;
		a = entrada[c];
		
		while(c<entrada.length) {
			if(a=='('||a==')') {
				parentesis++;
				c++;
				a = entrada[c];
			}
			
			else if(a=='\t'||a=='\n'||a==' ') {
				break;
			}
			else {
				error();
				break;
				}
		
		}
			
		
	}
	
	private void q21() {//LLAVES
		
		llaves++;
		c++;
		a = entrada[c];
		
		while(c<entrada.length) {
			if(a=='{'||a=='}') {
				llaves++;
				c++;
				a = entrada[c];
			}
			
			else if(a=='\t'||a=='\n'||a==' ') {
				break;
			}
			else {
				error();
				break;
				}
		
		}
		
		
	}
	
	private void q22() {//DETERMINAR QUE HACER CON /
			c++;
			a = entrada[c];
			
			if(a=='\t'||a==' ') {
				opAr++;
			}
			else if(a=='/') {
			
				q3();
				
			}
			else if(a=='*') {
				q4();
				
			}
			else error();
		
	
	}
	private void q23() {
		c++;
		a = entrada[c];
		while(Character.isDigit(a)&&c<entrada.length-1) {
			c++;
			a = entrada[c];
		}
		if(a=='\t'||a=='\n'||a==' ') {
			numDec++;

		}
        else error();

	}
}





/*boolean error = false;
ArrayList<Character> id = new ArrayList<>();
StringBuilder sB = new StringBuilder();

while ((Character.isLetterOrDigit(a)|| a == '_')&&c<entrada.length-1) {
    a = entrada[c];
    if (a == '\t' || a == '\n' || a == ' ') {
    	break; 
        
    } else {
        if (Character.isLetterOrDigit(a)|| a == '_') {
            id.add(a);
        } else {
            error = true;
            break;
        }
    	c++;
       
    }
}
if(!error) {
	for (Character caracter : id) {
        sB.append(caracter);
    }
	if(palabrasReservadas.contains(sB.toString())) {
		palabRes++;
	}
	else identif++;
	
	
}
else {
	error();
	identif--;
}*/
