public class ListaCuentas {

    private int numCuentas;
    private static final int MAX_CUENTAS = 10;
    private Cuenta [] listaCuentas;

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

    public void addCuenta(Cuenta cuenta){   //Añade una cuenta a la lista de cuentas
        if(numCuentas >= MAX_CUENTAS){
            System.out.println("***No se pueden añadir mas cuentas***");
        }else{
            this.listaCuentas[numCuentas] = cuenta;
            numCuentas++;
        }
    }

    public Cuenta buscarCuenta(String iban){    //Busca una cuenta por su IBAN
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

    public void imprimirTodasCuentas(){   //Imprime todas las cuentas
        for(int i = 0; i < numCuentas; i++){
            System.out.print("\t[" + (i+1) + "] "); listaCuentas[i].imprimirCuenta();
        }

    }

    public void imprimirTodasCuentaTransacciones(){    //Imprime todas las cuentas con sus transacciones
        for(int i = 0; i < numCuentas; i++){
            System.out.print("\t[" + (i+1) + "] "); listaCuentas[i].imprimirListaTransacciones();
        }

    }

}
