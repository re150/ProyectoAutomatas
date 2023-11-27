package automata1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Automatas {
    public  String texto ="";
    public Automatas(String text){
        this.texto = text;
        System.out.println(text);
    }
    public static void recorrerAutomata(String input, String[][] automata) {
        String estadoActual = "A";

        for (char transicion : input.toCharArray()) {
            int fila = estadoActual.charAt(0) - 'A';
            int columna = transicion - 'a';
            estadoActual = automata[fila][columna];
            System.out.println(estadoActual);
        }
    }

    public static void main(String[] args) {
        int i = 0;
        FileReader archivo;
        BufferedReader lector;
        String datos = " ";
        String input1 = " ";
        String input = "abcbaca";

        String[][] automata = {
                {"B", "C", "A"},
                {"C", "A", "B"},
                {"B", "C", "A"}
        };


        try {
            archivo = new FileReader("D:\\M6\\Teoría_de_Autómatas\\P3\\Prueva1\\texto.txt");
            //System.out.println("archivo = " + archivo);
            if(archivo.ready()){
                lector = new BufferedReader(archivo);
                while((datos =lector.readLine()) != null ){
                    i++;
                    input1 = datos;
           //    System.out.println("datos = " + datos);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("datos = " + input1);


        recorrerAutomata(input1, automata);
    }
}
