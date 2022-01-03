import javax.xml.transform.TransformerFactory;

public class Movimiento {
    private final double importe;
    private final Movimiento.TipoMovimiento tipoMovimiento;

    public Movimiento(Movimiento.TipoMovimiento tipoMovimiento, double importe) {
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
    }

    public double getImporte() {
        return this.importe;
    }

    public Movimiento.TipoMovimiento getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    enum TipoMovimiento {
        Deposito,
        Extraccion,
        Transferencia;

    }
}
