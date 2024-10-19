package main.java.com.javguerra.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para leer datos por consola.
 * @author JavGuerra
 * @version 1.0
 * @since 2023-01-31
 * @see <a href="https://javguerra.github.io/blog/clase-scanner-java/">
 *         «ConsoleInput kit» Descripción en el blog del autor.
 *     </a>
 * @see <a href="https://youtu.be/HSq3rRfBmDg">
 *         Video: Entrada de datos en Java - Clase Scanner.
 *         Mega curso Java desde 0, Aula en la nube.
 *     </a>
 */
public class ConsoleInput {

    /**
     * Instancia de la clase Scanner.
     */
    private static final Scanner IN = new Scanner(System.in);

    /**
     * Solicita con un mensaje por consola, y comprueba que se introduzca,
     * un número largo, entero, positivo (>=0).
     * @param message String Pregunta del usuario.
     * @return Long Número introducido.
     * @throws InputMismatchException Si el tipo de dato introducido fue erróneo.
     */
    public static Long getLongIntPos(String message) {
        long num;
        while(true) {
            System.out.print(message);
            try {
                num = IN.nextLong();
                IN.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no reconocido.");
                IN.nextLine();
                continue;
            }
            if (num >= 0) return num;
            System.out.println("Valor fuera de rango.");
        }
    }

    /**
     * Solicita con un mensaje por consola, y comprueba que se introduzca,
     * un número largo, entero, positivo (>=0), cuyo rango esté entre
     * el valor mínimo y el valor máximo recibido, ambos inclusive.
     * @param message String Pregunta del usuario.
     * @param min Long Valor mínimo.
     * @param max Long Valor máximo.
     * @return Long Número introducido.
     * @throws InputMismatchException Si el tipo de dato introducido fue erróneo.
     */
    public static Long getLongIntPosByRange(String message, Long min, Long max) {
        long num;
        if (min > max) { num = min; min = max; max = num; }
        while(true) {
            System.out.print(message);
            try {
                num = IN.nextLong();
                IN.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no reconocido.");
                IN.nextLine();
                continue;
            }
            if (num >= min && num <= max) return num;
            System.out.println("Valor fuera de rango.");
        }
    }

    /**
     * Solicita con un mensaje por consola, y comprueba que se introduzca,
     * una palabra de, al menos, 3 caracteres.
     * @param message String Pregunta del usuario.
     * @return String Palabra introducida.
     * @throws InputMismatchException Si el tipo de dato introducido fue erróneo.
     */
    public static String getWord(String message) {
        String str;
        while(true) {
            System.out.print(message);
            try {
                str = IN.next();
                IN.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no reconocido.");
                IN.nextLine();
                continue;
            }
            str = str.trim();
            if (str.length() >= 3) return str;
            System.out.println("Cadena no válida.");
        }
    }

    /**
     * Solicita con un mensaje por consola, y comprueba que se introduzca,
     * una cadena de texto de, al menos, 3 caracteres.
     * @param message String Pregunta del usuario.
     * @return String Cadena de texto introducida.
     * @throws InputMismatchException Si el tipo de dato introducido fue erróneo.
     */
    public static String getString(String message) {
        String str;
        while(true) {
            System.out.print(message);
            try {
                str = IN.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no reconocido.");
                IN.nextLine();
                continue;
            }
            str = str.trim();
            if (str.length() >= 3) return str;
            System.out.println("Cadena no válida.");
        }
    }

    /**
     * Solicita con un mensaje por consola, y comprueba que se introduzca,
     * un carácter para confirmar ('S', 's') o ('N', 'n').
     * @param message String Pregunta del usuario.
     * @return Boolean true/Sí o false/No.
     * @throws InputMismatchException Si el tipo de dato introducido fue erróneo.
     */
    public static Boolean getYesNo(String message) {
        char chr;
        while(true) {
            System.out.print(message);
            try {
                chr = IN.next(".").trim().charAt(0);
                IN.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Tipo de dato no reconocido.");
                IN.nextLine();
                continue;
            }
            if (chr == 'S' || chr == 's') return true;
            if (chr == 'N' || chr == 'n') return false;
            System.out.println("Carácter no válido.");
        }
    }

    /**
     * Espera la pulsación de la tecla Intro.
     */
    public static void getEnter() {
        IN.nextLine();
    }

    /**
     * Limpia la consola.
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Cierra el Scanner.
     */
    public static void closeScanner() {
        IN.close();
    }

}