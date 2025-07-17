import java.util.*;


public class GeneradorPalabras {

    public static void main(String[] args) {
        char[] cadena = {'w','e','r','f','b','h','j','i','u','y','t','r','e','d','f','g','y','u','i','o','l','k','m','n','b','v','f','r','e','w','s','x','f','g','y','u','i','k','m','n','b','v','f','r','e','w','w','r','t','y','u','i','o','k','m','n','b','v','w','s','x','c','f','g','h','u','i','o','p','l','k','n','b','v','f','d','e','w','a','z','x','c','g','h','u','i','o','p','u','y','t','r','e','w','q','s','d','f','g','k','j','v','c','x'};

        GeneradorPalabras generador = new GeneradorPalabras();
        generador.buscarPalabrasDefinidas(cadena);
    }

    /**
     * Metodo que busca palabras predefinidas en el array
     * @param cadena - array de caracteres
     */
    public void buscarPalabrasDefinidas(char[] cadena) {
        // Lista de palabras que vamos a buscar - pueden agregar mas si quieren
        String[] palabrasObjetivo = {"arley", "aprueba", "la", "prueba", "de", "ada", "xd", "negro", "oro", "capital",
                "mapa", "energia", "vida", "internet", "paz", "mar", "dia", "noche", "flor"};

        System.out.println(":::::::::::::  PALABRAS ENCONTRADAS EN EL ARRAY ::::::::::::: ");
        System.out.println();

        int palabrasEncontradas = 0;

        // Iteramos sobre cada palabra
        for (String palabra : palabrasObjetivo) {
            if (puedeFormarPalabra(cadena, palabra)) {
                palabrasEncontradas++;
                System.out.println("-------- Palabra: " + palabra.toUpperCase());
                mostrarIndicesPalabra(cadena, palabra);
                System.out.println();
            }
        }
        System.out.println("::::::::::::: RESUMEN ::::::::::::: ");
        System.out.println("Total de palabras encontradas: " + palabrasEncontradas);
        System.out.println("Total de palabras buscadas: " + palabrasObjetivo.length);
    }

    /**
     * Verifica si una palabra se puede formar con los caracteres disponibles
     * Usamos un HashMap para contar las frecuencias - enfoque clasico
     * @param cadena - array de caracteres disponibles
     * @param palabra - palabra que queremos formar
     * @return true si se puede formar, false sino
     */
    public boolean puedeFormarPalabra(char[] cadena, String palabra) {

        Map<Character, Integer> disponibles = new HashMap<>();

        for (char c : cadena) {
            disponibles.put(c, disponibles.getOrDefault(c, 0) + 1);
        }

        //  verificamos si tenemos suficientes caracteres para la palabra
        for (char c : palabra.toCharArray()) {
            if (disponibles.getOrDefault(c, 0) == 0) {
                return false; // No tenemos este caracter o ya se agoto
            }
            // "Gastamos" el caracter decrementando el contador
            disponibles.put(c, disponibles.get(c) - 1);
        }

        return true;
    }

    /**
     * Muestra los indices especificos donde encontramos cada caracter
     * Util para debug y para entender como se forma la palabra
     * @param cadena - array original
     * @param palabra - palabra que estamos formando
     */
    public void mostrarIndicesPalabra(char[] cadena, String palabra) {
        System.out.print("  Caracteres usados: ");
        boolean[] usado = new boolean[cadena.length]; // Con esto buscamos no reutilizar el mismo indice

        // Para cada caracter de la palabra, buscamos donde esta en el array
        for (char c : palabra.toCharArray()) {
            for (int i = 0; i < cadena.length; i++) {
                if (cadena[i] == c && !usado[i]) {
                    System.out.print(c + "(pos " + i + ") ");
                    usado[i] = true; // Marcamos este indice como usado
                    break;
                }
            }
        }
        System.out.println();
    }
}