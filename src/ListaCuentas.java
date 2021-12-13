public class ListaCuentas {

    private int numCuentas;
    private final int MAX_CUENTAS;
    private final Cuenta [] listaCuentas;

    public ListaCuentas(int max) {
        MAX_CUENTAS = max;
        this.listaCuentas = new Cuenta[MAX_CUENTAS];
        numCuentas = 0;
    }

    public Cuenta getCuenta(String iban){
        int i = 0;
        Cuenta cuent = null;

        while(i < listaCuentas.length && i < numCuentas){
            if(listaCuentas[i].getIban().equals(iban)){
                cuent = listaCuentas[i];
            }
            i++;
        }

        return cuent;
    }

    public void añadirCuenta(Cuenta cuenta){
        if(numCuentas >= MAX_CUENTAS){
            System.out.println("No se pueden añadir mas cuentas");
        }else{
            this.listaCuentas[numCuentas] = cuenta;
            numCuentas++;
        }
    }

    public void imprimir(){

    }


}
