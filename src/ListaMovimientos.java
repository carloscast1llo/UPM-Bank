public class ListaMovimientos {

    private int numMovimientos;
    private static final int MAX_MOVIMIENTOS = 50;
    private Movimiento[] listaMovimientos;

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

    public void addMovimiento(Movimiento movimiento) {    //Añade un movimiento a la lista
        this.listaMovimientos[numMovimientos] = movimiento;
        numMovimientos++;
    }

    public void imprimirTodosMovimientos() {    //Imprime todos los movimientos de la lista
        for (int i = 0; i < numMovimientos; i++) {
            listaMovimientos[i].imprimirMovimiento();
        }
    }
}
