public class ListaTransferencias {
    private int numTransferencias;
    private final int maxTransferencias = 50;
    private final Transferencia[] transferencias;

    public int getNumTransferencias() {
        return numTransferencias;
    }

    public ListaTransferencias() {
        this.transferencias = new Transferencia[maxTransferencias];
        this.numTransferencias = 0;
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
