public class Prestamo {

    private double capital;
    private int numeroAnios;
    private double interesAnual;

    public Prestamo(double capital, int numeroAnios, double interesAnual) {
        this.capital = capital;
        this.numeroAnios = numeroAnios;
        this.interesAnual = interesAnual;
    }

    public double getCapital() {
        return capital;
    }

    public void imprimirPrestamo(){     // Imprime el prestamo

        System.out.println("\t\t\t\tCapital: " + capital + "€ --> " + "Numero de años: " + numeroAnios + " --> " + "Interes anual: " + interesAnual + "%");

    }

}
