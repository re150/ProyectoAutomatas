import java.util.Scanner;

public class Automatas {
    public static void main(String[] args) {
        String input = "abcbaca";
        String[][] automata = {
                {"B", "C", "A"},
                {"C", "A", "B"},
                {"B", "C", "A"}
        };

        recorrerAutomata(input, automata);
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

}
