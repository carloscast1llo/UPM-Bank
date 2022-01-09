public class ListaTransferencias {

    private int numTransferencias;
    private static final int MAX_TRANSFERENCIAS = 50;
    private Transferencia[] transferencias;

    public ListaTransferencias() {
        this.transferencias = new Transferencia[MAX_TRANSFERENCIAS];
        this.numTransferencias = 0;
    }

    public int getNumTransferencias() {
        return numTransferencias;
    }

    public Transferencia getTransferenciasPos(int pos) {    // Devuelve la transferencia de la posición po
        return transferencias[pos];
    }

    public  void addTransferencia(Transferencia transferencia) {    // Añade una transferencia a la lista
        this.transferencias[numTransferencias] = transferencia;
        numTransferencias++;
    }

    public void imprimirTransferenciaEmitidas(){    // Imprime las transferencias emitidas
        for (int i = 0; i < numTransferencias; i++) {
            System.out.print("");
            transferencias[i].imprimirTransferenciaEmitida();
        }
    }

    public void imprimirTransferenciaRecibidas(){    // Imprime las transferencias recibidas
        for (int i = 0; i < numTransferencias; i++) {
            System.out.print("");
            transferencias[i].imprimirTransferenciaRecibida();
        }
    }
}
