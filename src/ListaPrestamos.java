public class ListaPrestamos {

    private int numPrestamos;
    private final int MAX_PRESTAMOS = 50;
    private Prestamo[] prestamos;

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

    public void addPrestamo(Prestamo prestamo) {    // Añade un prestamo a la lista de prestamos
        this.prestamos[numPrestamos] = prestamo;
        numPrestamos++;
    }

    public void imprimirTodosPrestamos(){    // Imprime todos los prestamos de la lista

        for (int i = 0; i < numPrestamos; i++) {
            prestamos[i].imprimirPrestamo();
        }

    }

}
