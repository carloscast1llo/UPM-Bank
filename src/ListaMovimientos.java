public class ListaMovimientos {
    private int numMovimientos;
    private static final int MAX_MOVIMIENTOS = 50;
    private final Movimiento[] listaMovimientos;

    public ListaMovimientos() {
        this.listaMovimientos = new Movimiento[MAX_MOVIMIENTOS];
        numMovimientos = 0;
    }

    public int getMaxMovimientos() {
        return MAX_MOVIMIENTOS;
    }
    public int getNumMovimientos() {
        return numMovimientos;
    }

    public void addMovimiento(Movimiento movimiento) {
        this.listaMovimientos[numMovimientos] = movimiento;
        numMovimientos++;
    }

    public void imprimirMovimientos() {
        for (int i = 0; i < numMovimientos; i++) {
            listaMovimientos[i].imprimirMovimiento();
        }
    }
}
