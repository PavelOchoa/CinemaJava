package cinemajava;

import java.util.Random;
import java.util.Scanner;

// Clase Película
class Pelicula {
    private String titulo;
    private int duracion; // duración en minutos
    private int edadMinima;
    private String director;

    public Pelicula(String titulo, int duracion, int edadMinima, String director) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.edadMinima = edadMinima;
        this.director = director;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    @Override
    public String toString() {
        return "|Título: " + titulo + "" +
                "\n|Duración: " + duracion + "mins" + "|" +
                "\n|Edad mínima: " + edadMinima + "|" +
                "\n|Director: " + director + "|";
    }
}

// Clase Espectador
class Espectador {
    private String nombre;
    private int edad;
    private double dinero;

    public Espectador(String nombre, int edad, double dinero) {
        this.nombre = nombre;
        this.edad = edad;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
}

// Clase Asiento
class Asiento {
    private static Asiento[][] matrizAsientos = new Asiento[8][9]; // Matriz estática 8x9
    private int fila;
    private char columna;
    private Espectador espectador;

    public Asiento(int fila, char columna) {
        this.fila = fila;
        this.columna = columna;
        this.espectador = null;
    }

    public static void inicializarAsientos() {
        for (int i = 0; i < matrizAsientos.length; i++) {
            for (int j = 0; j < matrizAsientos[i].length; j++) {
                matrizAsientos[i][j] = new Asiento(8 - i, (char) ('A' + j)); // Fila 1 es la última de la matriz
            }
        }
    }

    public boolean estaOcupado() {
        return espectador != null;
    }

    public void asignarEspectador(Espectador espectador) {
        this.espectador = espectador;
    }

    public String getPosicion() {
        return fila + String.valueOf(columna);
    }

    public static boolean hayAsientosDisponibles() {
        for (Asiento[] fila : matrizAsientos) {
            for (Asiento asiento : fila) {
                if (!asiento.estaOcupado()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void mostrarAsientos() {
        for (Asiento[] fila : matrizAsientos) {
            for (Asiento asiento : fila) {
                System.out.print(asiento.getPosicion() + " (" + (asiento.estaOcupado() ? "🔴" : "⭕") + ")  ");//🔴=ocupado ⭕=libre
            }
            System.out.println();
        }
    }

    public static void asignarAsientoAleatorio(Espectador espectador, double precioEntrada) {
        if (!Cine.puedeSentarse(espectador, precioEntrada)) {
            System.out.println(espectador.getNombre() + " no puede sentarse (Edad o Dinero insuficiente).");
            return;
        }

        Random random = new Random();
        while (true) {
            int fila = random.nextInt(8);
            int columna = random.nextInt(9);
            Asiento asiento = matrizAsientos[fila][columna];
            if (!asiento.estaOcupado()) {
                asiento.asignarEspectador(espectador);
                espectador.setDinero(espectador.getDinero() - precioEntrada);
                System.out.println("Espectador " + espectador.getNombre() + " asignado al asiento " + asiento.getPosicion());
                break;
            }
        }
    }
}

// Clase Cine
class Cine {
    private Pelicula pelicula;
    private double precioEntrada;

    public Cine(Pelicula pelicula, double precioEntrada) {
        this.pelicula = pelicula;
        this.precioEntrada = precioEntrada;
        Asiento.inicializarAsientos();
    }

    public static boolean puedeSentarse(Espectador espectador, double precioEntrada) {
        return espectador.getEdad() >= 16 && espectador.getDinero() >= precioEntrada;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }
}
