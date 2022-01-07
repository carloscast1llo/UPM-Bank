public class Prestamo {

    private final double capital;
    private final int numeroAnios;
    private final double interesAnual;

    public Prestamo(double capital, int numeroAnios, double interesAnual) {
        this.capital = capital;
        this.numeroAnios = numeroAnios;
        this.interesAnual = interesAnual;
    }

    public double getCapital() {
        return capital;
    }
    public int getNumeroAnios() {
        return numeroAnios;
    }
    public double getInteresAnual() {
        return interesAnual;
    }

    public void imprimirPrestamo(){

        System.out.println("\t\t\t\tCapital: " + capital + "€ --> " + "Numero de años: " + numeroAnios + " --> " + "Interes anual: " + interesAnual + "%");

    }

    public void imprimirTA(){

    }

}
