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
        System.out.printf("\t\t\t\t\t[%s] envia %.2f a [%s]\n", cuentaEmisor, importe, cuentaReceptor);
    }

    public void imprimirTransferenciaRecibida(){     // Imprime la transferencia que se ha recibido
        System.out.printf("\t\t\t\t\t[%s] recibe %.2f de [%s]\n", cuentaReceptor, importe, cuentaEmisor);
    }

}
