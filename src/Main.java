/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Sección 10
 * 2023
 * Autores:
 *      - 22944 Andy Fuentes

 */
import java.util.Random;
import java.util.Scanner;

public class Main {
    /**
     * Método principal
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de veces que aparece la letra 'a': ");
        int numA = scanner.nextInt();

        System.out.print("Ingrese el número de veces que aparece la letra 'b': ");
        int numB = scanner.nextInt();

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < numA; i++) {
            stringBuilder.append('a');
        }

        for (int i = 0; i < numB; i++) {
            stringBuilder.append('b');
        }

        String inputString = stringBuilder.toString();

        System.out.println("La cadena generada es: " + inputString);

        PDA pda = new PDA();

        pda.addTransition(0, 'a', '$', new char[]{'a', '$'}, 0);
        pda.addTransition(0, 'a', 'a', new char[]{'a', 'a'}, 0);
        pda.addTransition(0, 'b', 'a', new char[]{}, 1);
        pda.addTransition(1, 'b', 'a', new char[]{}, 1);

        boolean accepted = pda.accept(numA, numB);

        if (accepted) {
            System.out.println("La cadena es aceptada.");
        } else {
            System.out.println("La cadena no es aceptada.");
        }
    }
}
