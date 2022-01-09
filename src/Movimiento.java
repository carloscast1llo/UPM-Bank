public class Movimiento {

    private double importe;
    private Movimiento.TipoMovimiento tipoMovimiento;

    public Movimiento(Movimiento.TipoMovimiento tipoMovimiento, double importe) {
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
    }

    enum TipoMovimiento {   // Enum para los tipos de movimientos
        Deposito,
        Extraccion
    }

    public void imprimirMovimiento() {  // Imprime el movimiento
        System.out.println("\t\t\t\t" + tipoMovimiento + " --> " + importe + "€");
    }

}
