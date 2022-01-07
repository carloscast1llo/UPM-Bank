public class Movimiento {

    private final double importe;
    private final Movimiento.TipoMovimiento tipoMovimiento;

    public Movimiento(Movimiento.TipoMovimiento tipoMovimiento, double importe) {
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
    }

    enum TipoMovimiento {
        Deposito,
        Extraccion
    }

    public void imprimirMovimiento() {
        System.out.println("\t\t\t\t" + tipoMovimiento + " --> " + importe + "€");
    }

}
