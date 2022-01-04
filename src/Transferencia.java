public class Transferencia {
    private final String ibanEmisor;
    private final String ibanReceptor;
    private final double importe;

    public Transferencia(String ibanEmisor, String ibanReceptor, double importe) {
        this.ibanEmisor = ibanEmisor;
        this.ibanReceptor = ibanReceptor;
        this.importe = importe;
    }

    public void imprimirTransferenciaEmitida(){
        System.out.println("[" + ibanEmisor + "] envia " + importe + " a [" + ibanReceptor + "]");
    }

    public void imprimirTransferenciaRecibida(){
        System.out.println("[" + ibanReceptor + "] recibe " + importe + " de [" + ibanEmisor + "]");
    }


}
