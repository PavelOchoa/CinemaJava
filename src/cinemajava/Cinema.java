package cinemajava;

import java.util.Random;
import java.util.Scanner;

// Clase principal
public class Cinema {
    public static void main(String[] args) {
        // Datos de prueba para película
        Pelicula pelicula = new Pelicula("The Matrix", 136, 16, "Wachowski");

        // Crear cine
        Cine cine = new Cine(pelicula, 10.0);

        // Crear lista de espectadores aleatorios
        Espectador[] espectadores = {
            new Espectador("Juan", 18, 20),
            new Espectador("María", 15, 5),
            new Espectador("Luis", 20, 50),
            new Espectador("Ana", 14, 12),
            new Espectador("Pedro", 30, 100),
            new Espectador("Antony", 21, 1000),
            new Espectador("Pavel", 19, 500)
            
        };

        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;
        
        System.out.println(pelicula.toString());

        while (seguir && Asiento.hayAsientosDisponibles()) {
            for (Espectador espectador : espectadores) {
                Asiento.asignarAsientoAleatorio(espectador,
                        cine.getPrecioEntrada());
            }

            System.out.println("¿Desea continuar? (s/n)");
            String respuesta = scanner.nextLine();
            seguir = respuesta.equalsIgnoreCase("s");

            Asiento.mostrarAsientos();
        }

        System.out.println("Fin de la simulación.");
    }
}
