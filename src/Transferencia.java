public class Transferencia {

    private String cuentaEmisor;
    private String cuentaReceptor;
    private double importe;

    public Transferencia(String cuentaEmisor, String cuentaReceptor, double importe) {
        this.cuentaEmisor = cuentaEmisor;
        this.cuentaReceptor = cuentaReceptor;
        this.importe = importe;
    }

    public double getImporte() {
        return importe;
    }
    public String getCuentaReceptor() {
        return cuentaReceptor;
    }

    public void imprimirTransferenciaEmitida(){     // Imprime la transferencia que se ha emitido
        System.out.println("\t\t\t\t\t[" + cuentaEmisor + "] envia " + importe + " a [" + cuentaReceptor + "]");
    }

    public void imprimirTransferenciaRecibida(){     // Imprime la transferencia que se ha recibido
        System.out.println("\t\t\t\t\t[" + cuentaReceptor + "] recibe " + importe + " de [" + cuentaEmisor + "]");
    }

}
