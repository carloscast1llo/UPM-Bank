public class Prestamo {
    private final double capital;
    private final int numeroMeses;
    private final double imes;

    public Prestamo(double capital, int numeroMeses, double imes) {
        this.capital = capital;
        this.numeroMeses = numeroMeses;
        this.imes = imes;
    }

    public double getCapital() {
        return capital;
    }

}
