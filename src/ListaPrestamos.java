public class ListaPrestamos {

    private int numPrestamos;
    private final int MAX_PRESTAMOS = 50;
    private final Prestamo[] prestamos;

    public ListaPrestamos() {
        this.prestamos = new Prestamo[MAX_PRESTAMOS];
        this.numPrestamos = 0;
    }

    public int getNumPrestamos() {
        return numPrestamos;
    }
    public int getMAX_PRESTAMOS() {
        return MAX_PRESTAMOS;
    }

    public void addPrestamo(Prestamo prestamo) {
        this.prestamos[numPrestamos] = prestamo;
        numPrestamos++;
    }

    public Prestamo buscarPrestamo(double capital, int numeroAnios, double interesAnual) {

        for (int i = 0; i < numPrestamos; i++) {
            if (prestamos[i].getCapital() == capital && prestamos[i].getNumeroAnios() == numeroAnios && prestamos[i].getInteresAnual() == interesAnual) {
                return prestamos[i];
            }
        }

        return null;
    }

    public void imprimirTodosPrestamos(){

        for (int i = 0; i < numPrestamos; i++) {
            prestamos[i].imprimirPrestamo();
        }
    }


}
