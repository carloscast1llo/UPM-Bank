public class ListaTransferencias {

    private int numTransferencias;
    private static final int MAX_TRANSFERENCIAS = 50;
    private final Transferencia[] transferencias;

    public ListaTransferencias() {
        this.transferencias = new Transferencia[MAX_TRANSFERENCIAS];
        this.numTransferencias = 0;
    }

    public int getNumTransferencias() {
        return numTransferencias;
    }

    public Transferencia getTransferenciasPos(int pos) {
        return transferencias[pos];
    }

    public  void addTransferencia(Transferencia transferencia) {
        this.transferencias[numTransferencias] = transferencia;
        numTransferencias++;
    }

    public void imprimirTransferenciaEmitidas(){
        for (int i = 0; i < numTransferencias; i++) {
            System.out.print("");
            transferencias[i].imprimirTransferenciaEmitida();
        }
    }

    public void imprimirTransferenciaRecibidas(){
        for (int i = 0; i < numTransferencias; i++) {
            System.out.print("");
            transferencias[i].imprimirTransferenciaRecibida();
        }
    }
}
