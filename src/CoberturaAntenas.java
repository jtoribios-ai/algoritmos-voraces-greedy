import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solución para el problema de Cobertura de Antenas utilizando Algoritmos Voraces (Greedy).
 * Universidad La Salle - Práctica de Algoritmos
 */
public class CoberturaAntenas {

    /**
     * Método que calcula la ubicación óptima de las antenas para cubrir todas las casas.
     * @param casas Lista con las posiciones enteras de las casas.
     * @param r Rango de cobertura de cada antena.
     */
    public static void colocarAntenas(List<Integer> casas, int r) {
        if (casas == null || casas.isEmpty()) {
            System.out.println("No hay casas para cubrir.");
            return;
        }

        // 1. Ordenar las posiciones de las casas de menor a mayor (Paso previo necesario)
        Collections.sort(casas);

        List<Integer> antenas = new ArrayList<>();
        int i = 0;
        int n = casas.size();

        // 2. Aplicar la estrategia Greedy recorriendo las casas
        while (i < n) {
            // Ubicar la casa descubierta más a la izquierda
            int izquierdaCasa = casas.get(i);
            
            // Estrategia Greedy: colocar la antena en la posición óptima (casa + R) 
            // para maximizar el rango de cobertura hacia la derecha.
            int posicionAntena = izquierdaCasa + r;
            antenas.add(posicionAntena);

            // 3. Avanzar el índice ignorando todas las casas que ya quedan cubiertas por esta antena.
            // Una antena en 'posicionAntena' cubre desde (posicionAntena - r) hasta (posicionAntena + r).
            while (i < n && casas.get(i) <= posicionAntena + r) {
                i++;
            }
        }

        // 4. Mostrar los resultados detallados en consola
        System.out.println("Antenas colocadas aproximadamente en:");
        for (int pos : antenas) {
            System.out.println(pos);
        }
        System.out.println("Cantidad total: " + antenas.size());
    }

    public static void main(String[] args) {
        // --- EJEMPLO 1 del documento ---
        System.out.println("=== EJEMPLO 1 ===");
        List<Integer> casas1 = new ArrayList<>();
        for (int c : new int[]{1, 2, 7, 11, 20, 21, 30}) {
            casas1.add(c);
        }
        int radio1 = 5;
        colocarAntenas(casas1, radio1);

        // --- EJEMPLO 2 del documento ---
        System.out.println("\n=== EJEMPLO 2 ===");
        List<Integer> casas2 = new ArrayList<>();
        for (int c : new int[]{2, 4, 8, 15, 18, 22}) {
            casas2.add(c);
        }
        int radio2 = 3;
        colocarAntenas(casas2, radio2);
    }
}
