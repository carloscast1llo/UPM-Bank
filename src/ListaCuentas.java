public class ListaCuentas {

    private int numCuentas;
    private static final int MAX_CUENTAS = 10;
    private final Cuenta [] listaCuentas;

    public ListaCuentas(int MAX_CUENTAS) {
        this.listaCuentas = new Cuenta[MAX_CUENTAS];
        this.numCuentas = 0;
    }

    public int getNumCuentas() {
        return numCuentas;
    }
    public Cuenta getCuentaPos(int pos) {
        return listaCuentas[pos];
    }

    public Cuenta buscarCuenta(String iban){
        int i = 0;
        Cuenta cuent = null;

        while(i < numCuentas){
            if(listaCuentas[i].getIban().equals(iban)){
                cuent = listaCuentas[i];
            }
            i++;
        }

        return cuent;
    }

    public void addCuenta(Cuenta cuenta){
        if(numCuentas >= MAX_CUENTAS){
            System.out.println("No se pueden añadir mas cuentas");
        }else{
            this.listaCuentas[numCuentas] = cuenta;
            numCuentas++;
        }
    }

    public void imprimirTodasCuentas(){
        for(int i = 0; i < numCuentas; i++){
            System.out.print("\t[" + (i+1) + "] "); listaCuentas[i].imprimirCuenta();
        }

    }

    public void imprimirTodasCuentaTransacciones(){
        for(int i = 0; i < numCuentas; i++){
            System.out.print("\t[" + (i+1) + "] "); listaCuentas[i].imprimirListaTransacciones();
        }

    }

}
