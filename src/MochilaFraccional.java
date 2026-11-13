import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Objeto implements Comparable<Objeto> {
    String nombre;
    double valor;
    double peso;
    double densidad;

    public Objeto(String nombre, double valor, double peso) {
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
        this.densidad = valor / peso;
    }

    @Override
    public int compareTo(Objeto otro) {
      
        return Double.compare(otro.densidad, this.densidad);
    }
}

class SeleccionItem {
    String nombre;
    String estado; 
    double valorObtenido;

    public SeleccionItem(String nombre, String estado, double valorObtenido) {
        this.nombre = nombre;
        this.estado = estado;
        this.valorObtenido = valorObtenido;
    }
}

public class MochilaFraccional {

    public static void resolverMochila(double capacidadMax, List<Objeto> objetos) {
      
        Collections.sort(objetos);

        double pesoActual = 0;
        double valorTotal = 0;
        List<SeleccionItem> seleccionados = new ArrayList<>();

        for (Objeto obj : objetos) {
            if (pesoActual + obj.peso <= capacidadMax) {
              
                pesoActual += obj.peso;
                valorTotal += obj.valor;
                seleccionados.add(new SeleccionItem(obj.nombre, "completo", obj.valor));
            } else {
                double espacioRestante = capacidadMax - pesoActual;
                double fraccion = espacioRestante / obj.peso;
                double valorFraccion = obj.valor * fraccion;
                
                valorTotal += valorFraccion;
                String fraccionStr = (int) espacioRestante + "/" + (int) obj.peso;
                seleccionados.add(new SeleccionItem(obj.nombre, fraccionStr + " Parte del objeto", valorFraccion));
                pesoActual += espacioRestante;
                break; 
            }
        }

        System.out.println("Objetos seleccionados:");
        for (SeleccionItem item : seleccionados) {
            if (item.estado.equals("completo")) {
                System.out.println("- Objeto " + item.nombre + " completo");
            } else {
                System.out.println("- $" + item.estado + " " + item.nombre);
            }
        }
        System.out.printf("Valor total aproximado: %.0f\n", valorTotal);
    }

    public static void main(String[] args) {
        
        System.out.println("=== EJEMPLO 1 ===");
        double capacidad1 = 50;
        List<Objeto> lista1 = new ArrayList<>();
        lista1.add(new Objeto("A", 60, 10));
        lista1.add(new Objeto("B", 100, 20));
        lista1.add(new Objeto("C", 120, 30));
        resolverMochila(capacidad1, lista1);

        System.out.println("\n=== EJEMPLO 2 ===");
     
        double capacidad2 = 25;
        List<Objeto> lista2 = new ArrayList<>();
        lista2.add(new Objeto("A", 80, 20));
        lista2.add(new Objeto("B", 100, 10));
        lista2.add(new Objeto("C", 120, 30));
        resolverMochila(capacidad2, lista2);
    }
}
