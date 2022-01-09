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

        System.out.printf("\t\t\t\tCapital: %.2f€ --> Numero de años: %d --> Interes anual: %.2f%%\n",capital, numeroAnios, interesAnual);

    }

}
