public class ListaMovimientos {
    private static int numMovimientos;
    private static final int MAX_MOVIMIENTOS = 50;
    private final Movimiento[] movimientos;

    public ListaMovimientos() {
        this.movimientos = new Movimiento[MAX_MOVIMIENTOS];
        numMovimientos = 0;
    }

    public int getNumMovimientos() {
        return numMovimientos;
    }

    public void añadirMovimiento(Movimiento movimiento) {
        this.movimientos[numMovimientos] = movimiento;
        numMovimientos++;
    }

    public void imprimirMovimientos() {
        for (int i = 0; i < numMovimientos; i++) {
            //movimientos[i].imprimir();
        }
    }
}
