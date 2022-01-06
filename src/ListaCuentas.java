public class ListaCuentas {

    private int numCuentas;
    private final int MAX_CUENTAS;
    private final Cuenta [] listaCuentas;

    public ListaCuentas(int max) {
        MAX_CUENTAS = max;
        this.listaCuentas = new Cuenta[MAX_CUENTAS];
        numCuentas = 0;
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

    public void imprimirTodosCuentas(){
        for(int i = 0; i < numCuentas; i++){
            System.out.print("\t[" + (i+1) + "] "); listaCuentas[i].imprimirCuenta();
        }
    }

}
