public class Transferencia {

    private final String cuentaEmisor;
    private final String cuentaReceptor;
    private final double importe;

    public Transferencia(String cuentaEmisor, String cuentaReceptor, double importe) {
        this.cuentaEmisor = cuentaEmisor;
        this.cuentaReceptor = cuentaReceptor;
        this.importe = importe;
    }

    public void imprimirTransferenciaEmitida(){
        System.out.println("[" + cuentaEmisor + "] envia " + importe + " a [" + cuentaReceptor + "]");
    }

    public void imprimirTransferenciaRecibida(){
        System.out.println("[" + cuentaReceptor + "] recibe " + importe + " de [" + cuentaEmisor + "]");
    }

}
